package model;

import utils.HttpParser;
import utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestMessage {

    private static final String CONTENT_LENGTH_KEY = "Content-Length";

    private final RequestLine requestLine;

    private RequestHeaders requestHeaders;

    private String body;

    public HttpRequestMessage(List<String> httpMessageData) throws IOException {
        this(httpMessageData, null);
    }

    public HttpRequestMessage(List<String> httpMessageData, BufferedReader bufferedReader) throws IOException {
        if (!(httpMessageData instanceof ArrayList)) {
            httpMessageData = new ArrayList<>(httpMessageData);
        }

        if (httpMessageData.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.requestLine = HttpParser.parseRequestLine(httpMessageData.remove(0));
        if (httpMessageData.isEmpty()) {
            return;
        }

        this.requestHeaders = new RequestHeaders(httpMessageData);
        this.body = this.parseBody(bufferedReader, requestHeaders);
    }

    private String parseBody(BufferedReader bufferedReader, RequestHeaders requestHeaders) throws IOException {
        if (bufferedReader == null) {
            return null;
        }

        String contentLengthValue = requestHeaders
                .getRequestHeaders()
                .get(CONTENT_LENGTH_KEY);
        int contentLength = this.getContentLength(contentLengthValue);
        if (contentLength == 0) {
            return null;
        }

        return IOUtils.readData(bufferedReader, contentLength);
    }

    private int getContentLength(String contentLengthValue) {
        if (contentLengthValue != null) {
            return Integer.parseInt(contentLengthValue);
        }

        return 0;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public RequestHeaders getRequestHeaders() {
        return requestHeaders;
    }

    public String getBody() {
        return body;
    }

    public String toStringHttpMessage() {
        StringBuilder value = new StringBuilder();
        value.append("[" + "\n");
        value.append(this.requestLine.getInfo());
        value.append(this.requestHeaders.getInfo());
        value.append("\n");
        value.append(body);
        value.append("\n");
        value.append("]");

        return value.toString();
    }

}
