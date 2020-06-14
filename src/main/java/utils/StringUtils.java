package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Objects;

@Slf4j
public class StringUtils {
    private static final Gson PRETTY_GSON = buildGson();

    private static Gson buildGson() {
        return new GsonBuilder()
            .setExclusionStrategies(new JsonIgnoreExclusionStrategy())
            .setPrettyPrinting()
            .create();
    }

    public static boolean isEmpty(String target) {
        return Objects.isNull(target) || target.length() <= 0;
    }

    public static boolean isNotEmpty(String target) {
        return !isEmpty(target);
    }

    public static <T> String toPrettyJson(T obj) {
        if (obj instanceof String) {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(String.valueOf(obj)).getAsJsonObject();
            String prettyJson = PRETTY_GSON.toJson(jsonObj);
            return prettyJson;
        }

        return PRETTY_GSON.toJson(obj);
    }

    public static int toInt(String str) {
        if (isEmpty(str)) {
            return 0;
        }

        try {
            return new BigInteger(str).intValue();
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }

        return 0;
    }

    public static String getOrDefault(String target) {
        return getOrDefault(target, "");
    }

    public static String getOrDefault(String target, String defaultValue) {
        return isNotEmpty(target) ? target : defaultValue;
    }
}