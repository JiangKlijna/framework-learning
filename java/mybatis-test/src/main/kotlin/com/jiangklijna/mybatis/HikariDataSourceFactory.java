package com.jiangklijna.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class HikariDataSourceFactory implements DataSourceFactory {

    private Properties properties;
    private HikariDataSource dataSource;

    public void setDataSource(HikariDataSource ds) {
        if (dataSource != null) dataSource.close();
        this.dataSource = ds;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void setProperties(Properties props) {
        this.properties = props;
        setDataSource(new HikariDataSource(new HikariConfig(props)));
    }

    public Properties getProperties() {
        return properties;
    }
}
