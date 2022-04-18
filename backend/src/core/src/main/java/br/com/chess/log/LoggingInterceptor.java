package br.com.chess.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(
            HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex) throws IOException {
        traceRequest(req, reqBody);

        return ex.execute(req, reqBody);
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        if (request != null) {
            log.debug("request URI : {}", request.getURI());
            log.debug("request method : {}", request.getMethod());
            log.debug("request headers: ");
            request.getHeaders();
            for (Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
                showHeaders(entry);
            }
            if (getRequestBody(body) != null) {
                log.debug("request body : {}", getRequestBody(body));
            } else {
                log.debug("Sem body");
            }

        }
    }

    private void showHeaders(Entry<String, List<String>> entry) {
        StringBuilder sb = new StringBuilder(entry.getKey());
        sb.append(": ");
        for (String value : entry.getValue()) {
            sb.append("Authorization".equalsIgnoreCase(entry.getKey()) ? "XXXXXXXXXX" : value).append("; ");
        }

        log.debug("{}", sb);
    }

    private String getRequestBody(byte[] body) {
        if (body != null && body.length > 0) {
            return (new String(body, StandardCharsets.UTF_8));
        } else {
            return null;
        }
    }

}