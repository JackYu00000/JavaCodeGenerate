package com.tools.jdbc;


public class JdbcServiceFactory {

    /**
     * 获取jdbc service
     * @param dataSource 数据源
     * @return jdbc service
     */
    public static JdbcService getJdbcService(DataSource dataSource){
        if(dataSource.getJdbcType() == JdbcType.MYSQL){
            return new MySQLJdbcService(dataSource);
        }else if(dataSource.getJdbcType() == JdbcType.ORACLE){
            return new OracleJdbcService(dataSource);
        }
        return null;
    }

}