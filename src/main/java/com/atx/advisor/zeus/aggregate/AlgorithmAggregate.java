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
    private String algorithmId;

    private String name;

    private String description;

    private String cron;

    @CommandHandler
    public AlgorithmAggregate(CreateAlgorithmCommand command){
        log.info("AlgorithmAggregate : handling CreateAlgorithmCommand {}", command);
        AggregateLifecycle.apply(new AlgorithmCreatedEvent(command.algorithmId, command.name, command.description, command.cron));
        log.info("AlgorithmAggregate : applied AlgorithmCreatedEvent {}", command);
    }

    @CommandHandler
    protected void on(UpdateAlgorithmCommand command){
        log.info("AlgorithmAggregate : handling UpdateAlgorithmCommand {}", command);
        // TODO: check if any change happened
        AggregateLifecycle.apply(new AlgorithmUpdatedEvent(command.algorithmId, command.name, command.description, command.cron));
        log.info("AlgorithmAggregate : applied UpdateAlgorithmCommand {}", command);
    }

    @EventSourcingHandler
    protected void on(AlgorithmCreatedEvent event){
        log.info("AlgorithmAggregate : EventSourcingHandler listened to AlgorithmCreatedEvent {}", event);
        this.algorithmId = event.algorithmId;
        this.name = event.name;
        this.description = event.description;
        this.cron = event.cron;
    }

    @EventSourcingHandler
    protected void on(AlgorithmUpdatedEvent event) {
        log.info("AlgorithmAggregate : EventSourcingHandler listened to AlgorithmUpdatedEvent {}", event);
        this.algorithmId = event.algorithmId;
        if(event.name != null) {
            this.name = event.name;
        }
        if(event.description != null) {
            this.description = event.description;
        }
        if(event.cron != null) {
            this.cron = event.cron;
        }
    }
}
