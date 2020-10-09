package ru.eatthefrog.hatterBot;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HTTPGetter {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public String doRequest(String url_string) {
        HttpGet httpGet = new HttpGet(url_string);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpClient.execute(httpGet);
            return EntityUtils.toString(response1.getEntity());
        } catch(IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                assert response1 != null;
                response1.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}