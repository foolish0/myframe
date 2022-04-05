package com.gabriel.myframe.common;

import java.util.List;

/**
 * sql 包含信息
 *
 * @author lizhenjiang
 */
public class SqlInfo {
    private final List<String> fieldList;
    private final List<String> tableList;

    public SqlInfo(List<String> fieldList, List<String> tableList) {
        this.fieldList = fieldList;
        this.tableList = tableList;
    }

    public List<String> getFieldList() {
        return this.fieldList;
    }

    public List<String> getTableList() {
        return this.tableList;
    }

    @Override
    public String toString() {
        return "SqlInfo{" +
                "fieldList=" + fieldList +
                ", tableList=" + tableList +
                '}';
    }
}
