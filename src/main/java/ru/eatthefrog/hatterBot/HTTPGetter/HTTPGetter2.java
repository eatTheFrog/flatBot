package ru.eatthefrog.hatterBot.HTTPGetter;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetter2 implements HTTPGetterable {
    public String doUnsafeRequest(String url_string) throws IOException{
        URL url = new URL(url_string);
        String output = "";
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        //System.out.println(String.format("Response code: %d ", responseCode));
        if (responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();
            while ((inputline = in.readLine()) != null){
                response.append(inputline);
            }
            in.close();
            output = response.toString();
        }
        return output;
    }

    @Override
    public String doRequest(String url_string) {
        String output = "";
        try {
            output = doUnsafeRequest(url_string);
        } catch(IOException e) {
        }
        return output;
    }
}
