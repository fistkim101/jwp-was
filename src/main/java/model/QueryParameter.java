package model;

import java.util.Map;

public class QueryParameter {

    private Map<String, String> parameters;

    public QueryParameter(Map<String, String> parameters) {
        if (!parameters.isEmpty()) {
            this.parameters = parameters;
        }
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getInfo() {
        StringBuilder data = new StringBuilder();
        this.parameters.forEach((key, value) -> {
            data.append(String.format("key : %s, value : %s", key, value));
            data.append("\n");
        });

        return data.toString();
    }

}
