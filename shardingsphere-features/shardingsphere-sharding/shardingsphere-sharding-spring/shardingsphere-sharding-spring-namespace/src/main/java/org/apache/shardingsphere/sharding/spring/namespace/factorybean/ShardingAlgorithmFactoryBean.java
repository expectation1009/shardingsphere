/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sharding.spring.namespace.factorybean;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.shardingsphere.infra.spi.ShardingSphereServiceLoader;
import org.apache.shardingsphere.infra.spi.type.TypedSPIRegistry;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

/**
 * Sharding algorithm factory bean.
 */
public final class ShardingAlgorithmFactoryBean implements FactoryBean<ShardingAlgorithm> {

    static {
        ShardingSphereServiceLoader.register(ShardingAlgorithm.class);
    }

    private String type;

    private Properties props;

    public ShardingAlgorithmFactoryBean(final String type, final Properties props) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(type), "The type of shardingAlgorithm is required.");
        this.type = type;
        this.props = props;
    }

    @Override
    public ShardingAlgorithm getObject() {
        return TypedSPIRegistry.getRegisteredService(ShardingAlgorithm.class, type, props);
    }

    @Override
    public Class<?> getObjectType() {
        return ShardingAlgorithm.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
