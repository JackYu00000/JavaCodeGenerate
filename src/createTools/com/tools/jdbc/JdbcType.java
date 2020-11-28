package com.tools.jdbc;

public enum JdbcType {
    MYSQL("MYSQL","MYSQL"),
    DM("DM","DM"),
    ORACLE("ORACLE","ORACLE");

    /**
     * 数值
     */
    public String enumVal;
    /**
     * 显示内容
     */
    public String enumShow;

    /**
     * 获取数值
     * @return
     */
    public String getEnumVal() {
        return enumVal;
    }
    /**
     * 设定数值
     * @param enumVal
     */
    public void setEnumVal(String enumVal) {
        this.enumVal = enumVal;
    }
    /**
     * 获取显示内容
     * @return
     */
    public String getEnumShow() {
        return enumShow;
    }
    /**
     * 设定显示内容
     * @param enumShow
     */
    public void setEnumShow(String enumShow) {
        this.enumShow = enumShow;
    }

    private JdbcType(String eVal,String eShow){
        this.enumVal = eVal;
        this.enumShow = eShow;
    }

}
