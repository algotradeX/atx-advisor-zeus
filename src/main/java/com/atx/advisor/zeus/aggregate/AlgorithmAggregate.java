package com.atx.advisor.zeus.aggregate;

import com.atx.advisor.zeus.common.command.CreateAlgorithmCommand;
import com.atx.advisor.zeus.common.event.AlgorithmCreatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
@NoArgsConstructor
@Slf4j
public class AlgorithmAggregate {

    @AggregateIdentifier
    private String id;

    private String name;

    private String description;

    private String cron;

    @CommandHandler
    public AlgorithmAggregate(CreateAlgorithmCommand createAlgorithmCommand){
        log.info("AlgorithmAggregate : handling CreateAlgorithmCommand {}", createAlgorithmCommand);
        AggregateLifecycle.apply(new AlgorithmCreatedEvent(createAlgorithmCommand.id, createAlgorithmCommand.name, createAlgorithmCommand.description, createAlgorithmCommand.cron));
        log.info("AlgorithmAggregate : applied AlgorithmCreatedEvent {}", createAlgorithmCommand);
    }

    @EventSourcingHandler
    protected void on(AlgorithmCreatedEvent algorithmCreatedEvent){
        log.info("AlgorithmAggregate : EventSourcingHandler listened to AlgorithmCreatedEvent {}", algorithmCreatedEvent);
        this.id = algorithmCreatedEvent.id;
        this.name = algorithmCreatedEvent.name;
        this.description = algorithmCreatedEvent.description;
        this.cron = algorithmCreatedEvent.cron;
    }

}
