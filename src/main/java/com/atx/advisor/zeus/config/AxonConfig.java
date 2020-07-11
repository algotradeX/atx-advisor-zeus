package com.atx.advisor.zeus.config;

import com.atx.advisor.zeus.aggregate.AlgorithmAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean EventSourcingRepository<AlgorithmAggregate> accountAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<AlgorithmAggregate> repository;
        repository = EventSourcingRepository.builder(AlgorithmAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}
