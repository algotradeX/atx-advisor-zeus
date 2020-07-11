package com.atx.advisor.zeus.common.dto;

import lombok.Data;

@Data
public class AlgorithmCreateDTO {

    private String name;

    private String description;

    private String cron;
}
