package com.atx.advisor.zeus.aggregate;

import com.atx.advisor.zeus.common.command.CreateAlgorithmCommand;
import com.atx.advisor.zeus.common.command.UpdateAlgorithmCommand;
import com.atx.advisor.zeus.common.event.AlgorithmCreatedEvent;
import com.atx.advisor.zeus.common.event.AlgorithmUpdatedEvent;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

    @CommandHandler
    protected void on(UpdateAlgorithmCommand updateAlgorithmCommand){
        log.info("AlgorithmAggregate : handling UpdateAlgorithmCommand {}", updateAlgorithmCommand);
        AggregateLifecycle.apply(new AlgorithmUpdatedEvent(updateAlgorithmCommand.id, updateAlgorithmCommand.name, updateAlgorithmCommand.description, updateAlgorithmCommand.cron));
        log.info("AlgorithmAggregate : applied UpdateAlgorithmCommand {}", updateAlgorithmCommand);
    }

    @EventSourcingHandler
    protected void on(AlgorithmCreatedEvent algorithmCreatedEvent){
        log.info("AlgorithmAggregate : EventSourcingHandler listened to AlgorithmCreatedEvent {}", algorithmCreatedEvent);
        this.id = algorithmCreatedEvent.id;
        this.name = algorithmCreatedEvent.name;
        this.description = algorithmCreatedEvent.description;
        this.cron = algorithmCreatedEvent.cron;
    }

    @EventSourcingHandler
    protected void on(AlgorithmUpdatedEvent algorithmUpdatedEvent) {
        log.info("AlgorithmAggregate : EventSourcingHandler listened to AlgorithmUpdatedEvent {}", algorithmUpdatedEvent);
        this.id = algorithmUpdatedEvent.id;
        if(algorithmUpdatedEvent.name != null) {
            this.name = algorithmUpdatedEvent.name;
        }
        if(algorithmUpdatedEvent.description != null) {
            this.description = algorithmUpdatedEvent.description;
        }
        if(algorithmUpdatedEvent.cron != null) {
            this.cron = algorithmUpdatedEvent.cron;
        }
    }
}
