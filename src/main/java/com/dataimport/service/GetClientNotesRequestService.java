package com.dataimport.service;

import com.dataimport.entity.ClientData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class GetClientNotesRequestService {

    public ClientData send(Request request) throws IOException {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(responseBody.string(), ClientData.class);
    }
}
