package ru.eatthefrog.hatterBot.HTTPGetter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class HTTPGetter1 implements HTTPGetterable {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public String doRequest(String url_string) {
        HttpGet httpGet = new HttpGet(url_string);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity());
        } catch(IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                assert response != null;
                response.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}