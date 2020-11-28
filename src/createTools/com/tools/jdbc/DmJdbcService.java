package com.tools.jdbc;

/**
 * 达梦数据库
 */
public class DmJdbcService extends AbstractJdbcService {

    public DmJdbcService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String loadDriverClass() {

        return "dm.jdbc.driver.DmDriver";
    }

    /**
     * 注意：oracle用户名称须大写
     */
    @Override
    protected String schemaPattern() {
        //oracle的schemaPattern为用户名
        return getDataSource().getJdbcUser().toUpperCase();
    }
}