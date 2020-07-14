package com.atx.advisor.zeus.common.dto;

import lombok.Data;

@Data
public class AlgorithmUpdateDTO {

    private String algorithmId;

    private String name;

    private String description;

    private String cron;
}
