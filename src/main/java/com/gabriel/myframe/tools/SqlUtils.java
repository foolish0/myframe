package com.gabriel.myframe.tools;

import com.gabriel.myframe.common.SqlInfo;
import com.google.common.collect.Lists;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * sql处理工具类
 * @author lizhenjiang
 */
public class SqlUtils {
    private static final SqlParser.Config CONFIG = SqlParser.config()
            .withLex(Lex.JAVA)
            .withCaseSensitive(true);

    public static SqlInfo getSqlInfo(String sql) throws SqlParseException {
        SqlParser parser = SqlParser.create(sql, CONFIG);
        SqlNode sqlNode = parser.parseQuery();
        String sqlNodeString = sqlNode.toString();
        System.out.println(sqlNodeString);
        List<String> fieldList = Lists.newArrayList();
        List<String> tableList = Lists.newLinkedList();
        handlerSql(sqlNode, fieldList, tableList);
        SqlInfo sqlInfo = new SqlInfo(fieldList, tableList);
        System.out.println(sqlInfo);
        return sqlInfo;
    }

    private static void handlerSql(SqlNode sqlNode, List<String> fieldList, List<String> tableList) {
        SqlKind kind = sqlNode.getKind();
        switch (kind) {
            case IDENTIFIER:
                // 最终表名
                SqlIdentifier sqlIdentifier = (SqlIdentifier) sqlNode;
                tableList.add(sqlIdentifier.toString());
                System.out.println("### table_name ###" + sqlIdentifier);
                break;
            case SELECT:
                handlerSelect(sqlNode, fieldList, tableList);
                break;
            case UNION:
                SqlBasicCall sqlBasicCall = (SqlBasicCall) sqlNode;
                sqlBasicCall.getOperandList().forEach(node -> {
                    handlerSql(node, fieldList, tableList);
                });
                break;
            case ORDER_BY:
                handlerOrderBy(sqlNode, fieldList, tableList);
                break;
            case AS:
                SqlBasicCall basicCall = (SqlBasicCall) sqlNode;
                SqlNode selectNode = basicCall.getOperandList().get(0);
                handlerSql(selectNode, fieldList, tableList);
                break;
            default:
                break;
        }
    }

    private static void handlerOrderBy(SqlNode sqlNode, List<String> fieldList, List<String> tableList) {
        SqlOrderBy sqlOrderBy = (SqlOrderBy) sqlNode;
        SqlNode query = sqlOrderBy.query;
        handlerSql(query, fieldList, tableList);
        SqlNodeList orderList = sqlOrderBy.orderList;
        handlerField(orderList, fieldList);
    }

    private static void handlerSelect(SqlNode sqlNode, List<String> fieldList, List<String> tableList) {
        SqlSelect sqlSelect = (SqlSelect) sqlNode;
        SqlNodeList selectList = sqlSelect.getSelectList();
        selectList.getList().forEach(select -> {
            if (select != null) {
                handlerField(select, fieldList);
            }
        });

        handlerFrom(sqlSelect.getFrom(), fieldList, tableList);

        if (sqlSelect.hasWhere()) {
            handlerField(sqlSelect.getWhere(), fieldList);
        }

        if (sqlSelect.hasOrderBy()) {
            handlerField(sqlSelect.getOrderList(), fieldList);
        }

        SqlNodeList group = sqlSelect.getGroup();
        if (group != null) {
            group.forEach(groupNode -> {
                handlerField(groupNode, fieldList);
            });
        }
    }

    private static void handlerFrom(SqlNode from, List<String> fieldList, List<String> tableList) {
        SqlKind kind = from.getKind();
        switch (kind) {
            case IDENTIFIER:
                // 最终表名
                SqlIdentifier sqlIdentifier = (SqlIdentifier) from;
                tableList.add(sqlIdentifier.toString());
                System.out.println("### table_name ###" + sqlIdentifier);
                break;
            case AS:
                SqlBasicCall sqlBasicCall = (SqlBasicCall) from;
                SqlNode selectNode = sqlBasicCall.getOperandList().get(0);
                handlerSql(selectNode, fieldList, tableList);
                break;
            case JOIN:
                SqlJoin sqlJoin = (SqlJoin) from;
                SqlNode left = sqlJoin.getLeft();
                handlerSql(left, fieldList, tableList);
                SqlNode right = sqlJoin.getRight();
                handlerSql(right, fieldList, tableList);
                SqlNode condition = sqlJoin.getCondition();
                if (condition != null) {
                    handlerField(condition, fieldList);
                }
                break;
            case SELECT:
                handlerSql(from, fieldList, tableList);
                break;
            default:
                break;
        }
    }

    private static void handlerField(SqlNode field, List<String> fieldList) {
        SqlKind kind = field.getKind();
        switch (kind) {
            case AS:
                @Nullable SqlNode[] operandsAs = ((SqlBasicCall) field).operands;
                SqlNode leftAs = operandsAs[0];
                if (leftAs != null) {
                    handlerField(leftAs, fieldList);
                }
                break;
            case IDENTIFIER:
                // 当前为子节点
                SqlIdentifier sqlIdentifier = (SqlIdentifier) field;
                System.out.println("### field ###" + sqlIdentifier);
                fieldList.add(sqlIdentifier.toString());
                break;
            default:
                if (field instanceof SqlBasicCall) {
                    @Nullable SqlNode[] nodes = ((SqlBasicCall) field).operands;
                    for (SqlNode node : nodes) {
                        if (node != null) {
                            handlerField(node, fieldList);
                        }
                    }
                }
                if (field instanceof SqlNodeList) {
                    ((SqlNodeList) field).getList().forEach(sqlNode -> {
                        if (sqlNode != null) {
                            handlerField(sqlNode, fieldList);
                        }
                    });
                }
                break;
        }
    }
}
