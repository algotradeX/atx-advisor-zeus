package com.atx.advisor.zeus.config;

import com.atx.advisor.zeus.aggregate.AlgorithmAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AxonConfig {

    @Bean
    EventSourcingRepository<AlgorithmAggregate> algorithmAggregateEventSourcingRepository(EventStore eventStore){
        log.info("AxonConfig : Creating EventSourcingRepository algorithmAggregateEventSourcingRepository . . .");
        EventSourcingRepository<AlgorithmAggregate> repository;
        repository = EventSourcingRepository.builder(AlgorithmAggregate.class).eventStore(eventStore).build();
        log.info("AxonConfig : Created EventSourcingRepository algorithmAggregateEventSourcingRepository");
        return repository;
    }

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.usingTrackingEventProcessors();
    }

}
