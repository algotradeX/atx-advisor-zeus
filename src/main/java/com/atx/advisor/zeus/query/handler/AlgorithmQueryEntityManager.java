package com.atx.advisor.zeus.query.handler;

import com.atx.advisor.zeus.aggregate.AlgorithmAggregate;
import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import com.atx.advisor.zeus.common.event.BaseEvent;
import com.atx.advisor.zeus.query.repository.AlgorithmRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class AlgorithmQueryEntityManager {

    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Autowired
    @Qualifier("algorithmAggregateEventSourcingRepository")
    private EventSourcingRepository<AlgorithmAggregate> algorithmAggregateEventSourcingRepository;

    @EventHandler
    void on(BaseEvent event){
        persistAlgorithm(buildQueryAlgorithm(getAlgorithmFromEvent(event)));
    }

    private AlgorithmAggregate getAlgorithmFromEvent(BaseEvent event){
        log.info("AlgorithmQueryEntityManager : getAlgorithmFromEvent {}", event);
        return algorithmAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private AlgorithmQueryEntity findExistingOrCreateAlgorithmQueryEntity(String id){
        log.info("AlgorithmQueryEntityManager : findExistingOrCreateAlgorithmQueryEntity {}", id);
        Optional<AlgorithmQueryEntity> optionalAlgorithmQueryEntity = algorithmRepository.findById(id);
        return optionalAlgorithmQueryEntity.orElseGet(AlgorithmQueryEntity::new);
    }

    private AlgorithmQueryEntity buildQueryAlgorithm(AlgorithmAggregate algorithmAggregate){
        log.info("AlgorithmQueryEntityManager : buildQueryAlgorithm {}", algorithmAggregate);
        AlgorithmQueryEntity algorithmQueryEntity = findExistingOrCreateAlgorithmQueryEntity(algorithmAggregate.getId());

        algorithmQueryEntity.setId(algorithmAggregate.getId());
        algorithmQueryEntity.setName(algorithmAggregate.getName());
        algorithmQueryEntity.setDescription(algorithmAggregate.getDescription());
        algorithmQueryEntity.setCron(algorithmAggregate.getCron());

        return algorithmQueryEntity;
    }

    private void persistAlgorithm(AlgorithmQueryEntity algorithmQueryEntity){
        log.info("AlgorithmQueryEntityManager : persistAlgorithm {}", algorithmQueryEntity);
        algorithmRepository.save(algorithmQueryEntity);
    }
}
