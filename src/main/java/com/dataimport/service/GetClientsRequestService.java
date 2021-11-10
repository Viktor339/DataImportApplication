package com.dataimport.service;

import com.dataimport.entity.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetClientsRequestService {

    @Value("${request.config.url}")
    private String url;

    public List<Client> send() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(responseBody.string(), new TypeReference<List<Client>>() {
        });
    }
}
