package com.gabriel.myframe;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
public class JsonNode {
    String a;
    String b;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonNode node = (JsonNode) o;
        Map<String, String> o1 = JSON.parseObject(JSON.toJSONString(this), new TypeReference<>() {
        });
        Map<String, String> o2 = JSON.parseObject(JSON.toJSONString(node), new TypeReference<>() {
        });
        for (String key : o1.keySet()) {
            if (!o1.get(key).equals(o2.get(key))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
