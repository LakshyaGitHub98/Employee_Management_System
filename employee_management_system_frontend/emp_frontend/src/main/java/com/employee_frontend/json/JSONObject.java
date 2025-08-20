package com.employee_frontend.json;
import java.util.*;
import java.util.regex.*;

public class JSONObject {
    private Map<String, Object> map = new LinkedHashMap<>();

    public JSONObject() {}

    public JSONObject(String json) {
        // Fixed regex to correctly capture quoted strings like "name1"
        Pattern pattern = Pattern.compile("\"(.*?)\"\\s*:\\s*(\"[^\"]*\"|\\d+|true|false|null)");
        Matcher matcher = pattern.matcher(json);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            map.put(key, parseValue(value));
        }
    }

    private Object parseValue(String value) {
        if (value.equals("null")) return null;
        if (value.equals("true")) return true;
        if (value.equals("false")) return false;
        if (value.matches("\\d+")) return Integer.parseInt(value);
        if (value.startsWith("\"")) return value.substring(1, value.length() - 1);
        return value;
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public String getString(String key) {
        return (String) map.get(key);
    }

    public int getInt(String key) {
        return (Integer) map.get(key);
    }

    public boolean getBoolean(String key) {
        return (Boolean) map.get(key);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (String key : map.keySet()) {
            sb.append("\"").append(key).append("\":");
            Object val = map.get(key);
            if (val instanceof String) sb.append("\"").append(val).append("\"");
            else sb.append(val);
            sb.append(",");
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 1); // remove trailing comma
        sb.append("}");
        return sb.toString();
    }
}