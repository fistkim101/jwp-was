package model;

import service.RequestService;
import types.HttpStatus;
import utils.FileIoUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class ClientResponse {
    private final HttpStatus responseHttpStatus;
    private HttpHeaders responseHeaders;
    private byte[] bytesBody;

    public ClientResponse(HttpStatus responseHttpStatus) {
        this.responseHttpStatus = responseHttpStatus;
    }

    public ClientResponse(HttpStatus responseHttpStatus, HttpHeaders responseHeaders) {
        this.responseHttpStatus = responseHttpStatus;
        this.responseHeaders = responseHeaders;
    }

    public ClientResponse(HttpStatus responseHttpStatus, HttpHeaders responseHeaders, Object body) throws IOException {
        this.responseHttpStatus = responseHttpStatus;
        this.responseHeaders = responseHeaders;
        this.bytesBody = RequestService.bodyToBytes(body);
    }

    public HttpStatus getResponseHttpStatus() {
        return responseHttpStatus;
    }

    public HttpHeaders getResponseHeaders() {
        return responseHeaders;
    }

    public byte[] getBytesBody() {
        return bytesBody;
    }

    public void setFileBody(UrlPath urlPath, boolean isRequestForTemplate) throws IOException, URISyntaxException {
        this.bytesBody = FileIoUtils.loadFileFromClasspath(urlPath.getPath(), isRequestForTemplate);
    }

}
