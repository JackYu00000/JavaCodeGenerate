package com.tools.entity.postman;

/**
 * Created by hanbing on 2015/11/27.
 */
public class PManBase {
    String id;
    String name;
    String description;

    /**
     * 用户id，用于同步使用
     */
    static String owner="58836";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

}
