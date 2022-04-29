package com.gabriel.myframe;

import ognl.Ognl;
import ognl.OgnlException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class FuncTest {
    @Test
    public void ognlTest() throws OgnlException {
        String condition = "n != null and n != ''";
        Map<String, Object> param = new HashMap<>();
        param.put("n", 1);
        System.out.println(Ognl.getValue(condition, param));
    }

    @Test
    public void xmlTest() throws DocumentException {
        String xml = "select id,name from student " +
                "where 1 = '1'" +
                "<if test=\"name != null and name != ''\">" +
                "and name = #{name} </if>" +
                "<if test=\"idList != null and idList.size() != 0\">" +
                "and id in" +
                "<foreach item = \"item\" index = \"index\" collection = \"idList\" " +
                "open = \"(\" separator = \",\" close = \")\">" +
                "#{item} </foreach> </if>";
        xml = "<sql>" + xml + "</sql>";
//        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;
        SAXReader reader = new SAXReader(false);
        Document document = reader.read(new StringReader(xml));
        Element element = document.getRootElement();
        System.out.println();
    }
}
