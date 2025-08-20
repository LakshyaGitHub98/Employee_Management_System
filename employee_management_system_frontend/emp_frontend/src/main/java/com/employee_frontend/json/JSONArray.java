package com.employee_frontend.json;
import java.util.*;

public class JSONArray {
    private List<Object> list = new ArrayList<>();

    public JSONArray() {}

    public JSONArray(String json) {
        json = json.trim();
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1);
            List<String> items = extractItems(json);
            for (String item : items) {
                item = item.trim();
                if (item.startsWith("{") && item.endsWith("}")) {
                    list.add(new JSONObject(item));
                } else if (item.startsWith("\"") && item.endsWith("\"")) {
                    list.add(item.substring(1, item.length() - 1));
                } else if (item.matches("-?\\d+")) {
                    list.add(Integer.parseInt(item));
                } else if (item.equals("true") || item.equals("false")) {
                    list.add(Boolean.parseBoolean(item));
                } else if (item.equals("null")) {
                    list.add(null);
                } else {
                    list.add(item); // fallback
                }
            }
        }
    }

    private List<String> extractItems(String json) {
        List<String> items = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int braceLevel = 0;
        boolean inQuotes = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '"') inQuotes = !inQuotes;
            if (!inQuotes) {
                if (c == '{') braceLevel++;
                if (c == '}') braceLevel--;
            }
            current.append(c);
            if (braceLevel == 0 && !inQuotes && c == '}') {
                items.add(current.toString());
                current.setLength(0);
            }
        }
        return items;
    }

    public void put(Object value) {
        list.add(value);
    }

    public Object get(int index) {
        return list.get(index);
    }

    public int length() {
        return list.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Object val : list) {
            sb.append(val.toString()).append(",");
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 1); // remove trailing comma
        sb.append("]");
        return sb.toString();
    }
}