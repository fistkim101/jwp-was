package http.request;

import java.util.Map;
import java.util.Objects;

public class RequestLine {

    private final RequestMethod requestMethod;
    private final Protocol protocol;

    public RequestLine(final RequestMethod requestMethod, final Protocol protocol) {
        this.requestMethod = requestMethod;
        this.protocol = protocol;
    }

    public String getPath() {
        return requestMethod.getPath();
    }

    public String getMethodName() {
        return requestMethod.getMethodName();
    }

    public String getProtocolName() {
        return protocol.getProtocol();
    }

    public String getProtocolVersion() {
        return protocol.getVersion();
    }

    public Map<String, String> getParams() {
        return requestMethod.getRequestParameters();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RequestLine that = (RequestLine) o;
        return Objects.equals(requestMethod, that.requestMethod) &&
                Objects.equals(protocol, that.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestMethod, protocol);
    }
}