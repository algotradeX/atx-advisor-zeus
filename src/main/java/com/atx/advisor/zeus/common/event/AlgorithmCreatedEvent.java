package com.atx.advisor.zeus.common.event;

public class AlgorithmCreatedEvent extends BaseEvent<String> {

    public final String name;

    public final String description;

    public final String cron;

    public AlgorithmCreatedEvent(String id, String name, String description, String cron) {
        super(id);
        this.name = name;
        this.description = description;
        this.cron = cron;
    }
}
