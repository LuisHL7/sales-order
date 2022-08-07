package test.luis.huapaya.TechnicalTest.models;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonObjectListDeserializer extends StdDeserializer<List<String>> {

    public JsonObjectListDeserializer() {
        super(List.class);
    }

    @Override
    public List<String> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        List<String> result = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode element : node) {
                result.add(element.toString());
            }
        } else if (node.isObject()) {
            result.add(node.toString());
        } else {
            System.out.println("hi");
        }
        return result;
    }
}
