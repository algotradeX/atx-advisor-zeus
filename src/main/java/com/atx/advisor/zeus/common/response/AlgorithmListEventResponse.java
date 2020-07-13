package com.atx.advisor.zeus.common.response;

import lombok.Data;

import java.util.List;

@Data
public class AlgorithmListEventResponse {

    private String algorithmId;

    private List<Object> eventsList;

    public AlgorithmListEventResponse(String algorithmId, List<Object> eventsList) {
        this.algorithmId = algorithmId;
        this.eventsList = eventsList;
    }
}
