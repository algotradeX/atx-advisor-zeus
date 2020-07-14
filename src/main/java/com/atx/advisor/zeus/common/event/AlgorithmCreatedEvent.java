package com.atx.advisor.zeus.common.event;

public class AlgorithmCreatedEvent extends AlgorithmBaseEvent<String> {

    public final String name;

    public final String description;

    public final String cron;

    public AlgorithmCreatedEvent(String algorithmId, String name, String description, String cron) {
        super(algorithmId);
        this.name = name;
        this.description = description;
        this.cron = cron;
    }
}
