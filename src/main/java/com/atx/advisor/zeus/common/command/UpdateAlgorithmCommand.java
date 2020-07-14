package com.atx.advisor.zeus.common.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateAlgorithmCommand {

    @TargetAggregateIdentifier
    public final String algorithmId;

    public final String name;

    public final String description;

    public final String cron;

    public UpdateAlgorithmCommand(String algorithmId, String name, String description, String cron) {
        this.algorithmId = algorithmId;
        this.name = name;
        this.description = description;
        this.cron = cron;
    }
}
