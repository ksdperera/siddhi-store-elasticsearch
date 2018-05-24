/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.extension.siddhi.store.elasticsearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.wso2.extension.siddhi.store.elasticsearch.exceptions.ElasticsearchServiceException;
import org.wso2.siddhi.core.table.record.RecordIterator;
import org.wso2.siddhi.query.api.definition.Attribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the iterator which streams a set elasticsearch documents
 */
public class ElasticsearchRecordIterator implements RecordIterator<Object[]> {

    private List<Attribute> attributes;
    private Iterator<SearchHit> elasticsearchHitsIterator;

    public ElasticsearchRecordIterator(String queryString, RestHighLevelClient restHighLevelClient,
                                       List<Attribute> attributes)
            throws ElasticsearchServiceException {
        this.attributes = attributes;
        QueryBuilder queryBuilder = getQueryBuilder(queryString);
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
            elasticsearchHitsIterator = searchResponse.getHits().iterator();
        } catch (IOException e) {
            throw new ElasticsearchServiceException("Error while performing search the query: '" + queryString + "'",
                    e);
        }
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public boolean hasNext() {
        synchronized (this) {
            return elasticsearchHitsIterator != null && elasticsearchHitsIterator.hasNext();
        }
    }

    @Override
    public Object[] next() {
        synchronized (this) {
            List<Object> fieldValues = new ArrayList<>();
            if (hasNext() && elasticsearchHitsIterator != null) {
                SearchHit searchHit = elasticsearchHitsIterator.next();
                for (Attribute attribute : attributes) {
                    Object fieldValue = searchHit.getSourceAsMap().get(attribute.getName());
                    fieldValues.add(fieldValue);
                }
            }
            return fieldValues.toArray();
        }
    }

    public SearchHit nextSearchHit() {
        synchronized (this) {
            if (hasNext() && elasticsearchHitsIterator != null) {
                return elasticsearchHitsIterator.next();
            } else {
                return null;
            }
        }
    }

    private static QueryBuilder getQueryBuilder(String queryString) {
        QueryStringQueryBuilder queryBuilder = QueryBuilders.queryStringQuery(queryString);
        return queryBuilder;
    }
}
