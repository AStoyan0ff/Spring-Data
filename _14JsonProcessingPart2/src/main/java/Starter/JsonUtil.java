package Starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.InputStream;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public <T> T fromJson(InputStream stream, Class<T> clazz) throws Exception {
        return objectMapper.readValue(stream, clazz);
    }
}
