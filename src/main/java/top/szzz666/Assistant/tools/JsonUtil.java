package top.szzz666.Assistant.tools;

import com.google.gson.*;

import java.util.Map;

public class JsonUtil {
    private static final Gson gson = new Gson();

    /**
     * 将 JSON 字符串解析为 JsonObject
     */
    public static JsonObject parseToJsonObject(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    /**
     * 将 JSON 字符串解析为 JsonArray
     */
    public static JsonArray parseToJsonArray(String json) {
        return JsonParser.parseString(json).getAsJsonArray();
    }

    /**
     * 从 JsonObject 中获取字符串字段值
     */
    public static String getString(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsString();
    }

    /**
     * 从 JsonObject 中获取整数字段值
     */
    public static int getInt(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsInt();
    }

    /**
     * 从 JsonObject 中获取布尔字段值
     */
    public static boolean getBoolean(JsonObject jsonObject, String key) {
        return jsonObject.get(key).getAsBoolean();
    }

    /**
     * 从 JsonObject 中获取嵌套的 JsonObject
     */
    public static JsonObject getJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonObject(key);
    }

    /**
     * 从 JsonObject 中获取嵌套的 JsonArray
     */
    public static JsonArray getJsonArray(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonArray(key);
    }

    /**
     * 将 Java 对象转换为 JSON 字符串
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将 JSON 字符串转换为 Java 对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 创建一个新的 JsonObject
     */
    public static JsonObject createJsonObject() {
        return new JsonObject();
    }

    /**
     * 向 JsonObject 中添加字段
     */
    public static void addProperty(JsonObject jsonObject, String key, Object value) {
        if (value instanceof String) {
            jsonObject.addProperty(key, (String) value);
        } else if (value instanceof Number) {
            jsonObject.addProperty(key, (Number) value);
        } else if (value instanceof Boolean) {
            jsonObject.addProperty(key, (Boolean) value);
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + value.getClass());
        }
    }

    /**
     * 将 Map 转换为 JsonObject
     */
    public static JsonObject mapToJsonObject(Map<String, Object> map) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            addProperty(jsonObject, entry.getKey(), entry.getValue());
        }
        return jsonObject;
    }
    public static String getStringValue(String json, String key) {
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement valueElement = jsonObject.get(key);

        if (valueElement == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }

        if (!valueElement.isJsonPrimitive() || !valueElement.getAsJsonPrimitive().isString()) {
            throw new IllegalArgumentException("Value for key \"" + key + "\" is not a string");
        }

        return valueElement.getAsString();
    }
    public static int getIntValue(String json, String key) {
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement valueElement = jsonObject.get(key);

        if (valueElement == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }

        if (!valueElement.isJsonPrimitive() || !valueElement.getAsJsonPrimitive().isNumber()) {
            throw new IllegalArgumentException("Value for key \"" + key + "\" is not a number");
        }

        return valueElement.getAsInt();
    }
    public static boolean getBooleanValue(String json, String key) {
        JsonElement jsonElement = JsonParser.parseString(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement valueElement = jsonObject.get(key);

        if (valueElement == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }

        if (!valueElement.isJsonPrimitive() || !valueElement.getAsJsonPrimitive().isBoolean()) {
            throw new IllegalArgumentException("Value for key \"" + key + "\" is not a boolean");
        }

        return valueElement.getAsBoolean();
    }
}
