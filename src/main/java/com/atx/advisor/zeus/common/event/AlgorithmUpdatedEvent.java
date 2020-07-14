package com.atx.advisor.zeus.common.event;

public class AlgorithmUpdatedEvent extends AlgorithmBaseEvent<String> {

    public final String name;

    public final String description;

    public final String cron;

    public AlgorithmUpdatedEvent(String algorithmId, String name, String description, String cron) {
        super(algorithmId);
        this.name = name;
        this.description = description;
        this.cron = cron;
    }
}
