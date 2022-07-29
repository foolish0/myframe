package com.gabriel.myframe;

import com.alibaba.fastjson.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ArrayNode {
    List<JsonNode> nodes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayNode arrayNode = (ArrayNode) o;
        List<JsonNode> other = arrayNode.getNodes();
        if (other.size() != this.nodes.size()) {
            return false;
        }
        for (JsonNode node : this.nodes) {
            other.remove(node);
        }
        return other.size() == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }
}
