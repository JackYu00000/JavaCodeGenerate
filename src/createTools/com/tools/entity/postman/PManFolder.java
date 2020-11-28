package com.tools.entity.postman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanbing on 2015/11/27.
 */
public class PManFolder  extends PManBase{

    static String lastUpdateBy = "58836";

    Long lastRevision;

    List<String> order = new ArrayList<String>();

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public Long getLastRevision() {
        return lastRevision;
    }

    public void setLastRevision(Long lastRevision) {
        this.lastRevision = lastRevision;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }
}
