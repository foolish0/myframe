package com.gabriel.myframe;

import com.gabriel.myframe.common.SqlInfo;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

public class BeanTest {
    @Test
    public void beanTest() {
        SqlInfo sqlInfo = new SqlInfo(Lists.newArrayList(), Lists.newLinkedList());
        System.out.println(sqlInfo);
    }
}
