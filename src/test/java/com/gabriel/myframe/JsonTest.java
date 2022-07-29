package com.gabriel.myframe;

import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String n1 = "{\"a\":1,\"b\":2}";
        String n2 = "{\"a\":2,\"b\":3}";
        JsonNode node1 = JSONObject.parseObject(n1, JsonNode.class);
        JsonNode node2 = JSONObject.parseObject(n2, JsonNode.class);

        List<JsonNode> obj_a = Lists.newArrayList(node1, node2);
        ArrayNode a = new ArrayNode(obj_a);

        String n3 = "{\"b\":2,\"a\":1}";
        String n4 = "{\"b\":3,\"a\":2}";
        JsonNode node3 = JSONObject.parseObject(n3, JsonNode.class);
        JsonNode node4 = JSONObject.parseObject(n4, JsonNode.class);
        List<JsonNode> obj_b = Lists.newArrayList(node3, node4);
        ArrayNode b = new ArrayNode(obj_b);

        System.out.println(a);
        System.out.println(b);

        Assert.assertEquals(a, b);
    }
}


