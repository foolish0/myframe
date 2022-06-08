package com.gabriel.myframe.common;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * sql 包含信息
 *
 * @author lizhenjiang
 */
@Data
public class SqlInfo {
    private final List<String> fieldList;
    private final List<String> tableList;

    public SqlInfo(List<String> fieldList, List<String> tableList) {
        this.fieldList = fieldList;
        this.tableList = tableList;
    }
}
