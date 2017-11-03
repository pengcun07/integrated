package com.pursue.corner.integrated.rw;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 支持读写分离的数据源
 */
public class RwDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceRwContext.get();
    }
}
