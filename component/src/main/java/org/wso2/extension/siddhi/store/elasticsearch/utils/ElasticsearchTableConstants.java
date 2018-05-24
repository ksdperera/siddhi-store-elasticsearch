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

package org.wso2.extension.siddhi.store.elasticsearch.utils;

/**
 * This class contains the constants values required by the elasticsearch table implementation
 */
public class ElasticsearchTableConstants {

    public static final String ANNOTATION_ELEMENT_HOSTNAME = "host";
    public static final String ANNOTATION_ELEMENT_PORT = "port";
    public static final String ANNOTATION_ELEMENT_SCHEME = "scheme";
    public static final String ANNOTATION_ELEMENT_USER = "user";
    public static final String ANNOTATION_ELEMENT_PASSWORD = "password";
    public static final String ANNOTATION_ELEMENT_INDEX_NAME = "index.name";
    public static final String ANNOTATION_ELEMENT_INDEX_NUMBER_OF_SHARDS = "index.number.of.shards";
    public static final String ANNOTATION_ELEMENT_INDEX_NUMBER_OF_REPLICAS = "index.number.of.replicas";
    public static final String ANNOTATION_ELEMENT_INDEX_ALIAS = "index.alias";

    public static final String DEFAULT_HOSTNAME = "localhost";
    public static final int DEFAULT_PORT = 9200;
    public static final int DEFAULT_NUMBER_OF_SHARDS = 3;
    public static final int DEFAULT_NUMBER_OF_REPLICAS = 2;
    public static final String DEFAULT_SCHEME = "http";
    public static final String DEFAULT_USER_NAME = "elastic";
    public static final String DEFAULT_PASSWORD = "changeme";

    public static final String SETTING_INDEX_NUMBER_OF_SHARDS = "index.number_of_shards";
    public static final String SETTING_INDEX_NUMBER_OF_REPLICAS = "index.number_of_replicas";


    public static final String MAPPING_PROPERTIES_ELEMENT = "properties";
    public static final String MAPPING_TYPE_ELEMENT = "type";

    public static final String ELASTICSEARCH_INDEX_TYPE = "_doc";
}
