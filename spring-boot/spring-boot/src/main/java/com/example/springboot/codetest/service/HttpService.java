package com.example.springboot.codetest.service;

import com.example.springboot.codetest.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HttpService {

    public String sendGetRequest(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(new HttpGet(url));
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            client.close();
            return result;
        } catch (Exception e) {
            System.out.println("Exception sending request to "+url);
            e.printStackTrace();
            return null;
        } finally {
            client.close();
        }
    }
}
