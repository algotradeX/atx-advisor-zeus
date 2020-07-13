package com.atx.advisor.zeus.common.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AlgorithmQueryEntities")
@Data
public class AlgorithmQueryEntity {

    @Id
    private String id;

    private String name;

    private String description;

    private String cron;
}
