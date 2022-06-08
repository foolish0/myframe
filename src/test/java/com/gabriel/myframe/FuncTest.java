package com.gabriel.myframe;

import ognl.Ognl;
import ognl.OgnlException;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.*;

public class FuncTest {
    @Test
    public void ognlTest() throws OgnlException {
        String condition = "n != null and n != ''";
        Map<String, Object> param = new HashMap<>();
        param.put("n", 1);
        System.out.println(Ognl.getValue(condition, param));
    }

    @Test
    public void xmlTest() throws DocumentException, OgnlException {
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
        System.out.println(xml);
        Map<String, Object> param = new HashMap<>();
        param.put("name", "Gabriel");
        List<String> idList = Arrays.asList("2", "3");
        param.put("idList", idList);
        SAXReader reader = new SAXReader(false);
        Document document = reader.read(new StringReader(xml));
        Element element = document.getRootElement();
        StringBuilder sql = new StringBuilder();
        List<Node> content = element.content();
        for (Object ele : content) {
            if (ele instanceof Text) {
                Text text = (Text) ele;
                sql.append(text.getText());
            } else if (ele instanceof Element){
                Element e = (Element) ele;
                if ("if".equals(e.getQName().getName())) {
                    Attribute test = e.attribute("test");
                    if ("true".equals(Ognl.getValue(test.getValue(), param).toString())) {
                        Text text = (Text) e.content().get(0);
                        sql.append(text.getText());
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
        System.out.println(sql);
    }
}
