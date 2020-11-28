package com.tools.entity.postman;

/**
 * Created by Administrator on 2015/11/27.
 */
public class PManRequestFormEntity {
    /**
     * form 提交时的参数名
     */
    String key;
    /**
     * form 提交时的参数值
     */
    String value;
    /**
     * form 提交时的参数类型：文字、文件
     */
    String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
