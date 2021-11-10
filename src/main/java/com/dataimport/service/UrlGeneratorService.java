package com.dataimport.service;

import org.springframework.stereotype.Service;

@Service
public class UrlGeneratorService {

    public String generate(String clientGuid) {

        String url = "";

        switch (clientGuid) {
            case "111":
                url = "https://run.mocky.io/v3/55dd0824-6dc5-4745-bda6-a4cfe86437d1";
                break;
            case "222":
                url = "https://run.mocky.io/v3/ff235cca-3035-49fa-a402-6c5de736a334";
                break;
            case "333":
                url = "https://run.mocky.io/v3/45216aee-280a-4710-b8ea-f6d3724a43bb";
                break;
            case "444":
                url = "https://run.mocky.io/v3/bb02d2e0-57d3-48ce-8715-edd94553869c";
                break;
        }

        return url;
    }
}
