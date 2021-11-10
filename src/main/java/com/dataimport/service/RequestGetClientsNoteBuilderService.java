package com.dataimport.service;

import lombok.RequiredArgsConstructor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestGetClientsNoteBuilderService {

    private final UrlGeneratorService urlGeneratorService;

    public Request buildRequest(String agency, String clientGuid) {

        RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("agency", agency)
                .addFormDataPart("from_date", "2019-09-18")
                .addFormDataPart("to_date", "2021-09-18")
                .addFormDataPart("client_guid", clientGuid)
                .build();

        return new Request.Builder()
                .url(urlGeneratorService.generate(clientGuid))
                .post(req)
                .build();
    }
}
