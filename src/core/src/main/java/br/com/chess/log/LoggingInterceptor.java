package br.com.chess.log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(
      HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex) throws IOException {
    	traceRequest(req, reqBody);
        ClientHttpResponse response = ex.execute(req, reqBody);

        return response;
    }
    
    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug(String.format("request URI : %s", request.getURI()));
        log.debug(String.format("request method : %s", request.getMethod()));
        log.debug("request headers: ");
        request.getHeaders();
        for (Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
            showHeaders(entry);
        }

        log.debug(String.format("request body : %s", getRequestBody(body)));

    }
    
    private void showHeaders(Entry<String, List<String>> entry) {
        StringBuilder sb = new StringBuilder(entry.getKey());
        sb.append(": ");
        for (String value : entry.getValue()) {
            sb.append("Authorization".equalsIgnoreCase(entry.getKey())? "XXXXXXXXXX" : value).append("; ");
        }

        log.debug(sb.toString());
    }
    
    private String getRequestBody(byte[] body) throws UnsupportedEncodingException {
        if (body != null && body.length > 0) {
            return (new String(body, StandardCharsets.UTF_8));
        } else {
            return null;
        }
    }

}