package com.tools.jdbc;


import com.tools.entity.DBTable;

import java.util.List;

public interface JdbcService {
    /**
     * 测试数据库的连通性
     * @return true表示成功
     */
    public boolean test();

    /**
     * 列出所有的表和视图
     * @return 所有的表和视图
     */
    public List<String> listAllTables();

    /**
     * 列出所有包含某些字段的表和视图
     * @param fields 字段
     * @return 所有包含某些字段的表和视图
     */
    public List<String> listAllTablesFields(String... fields);

    /**
     * 列出所有的字段
     * @param tableName 表名或视图名
     * @return 所有的字段
     */
    public List<String> listAllFields(String tableName);

    public List<DBTable> getTablesAndViews();
}