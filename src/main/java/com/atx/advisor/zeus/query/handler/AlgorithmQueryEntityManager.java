package com.atx.advisor.zeus.query.handler;

import com.atx.advisor.zeus.aggregate.AlgorithmAggregate;
import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import com.atx.advisor.zeus.common.event.AlgorithmBaseEvent;
import com.atx.advisor.zeus.common.query.GetAlgorithmEventListQuery;
import com.atx.advisor.zeus.common.query.GetAlgorithmQuery;
import com.atx.advisor.zeus.query.repository.AlgorithmRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AlgorithmQueryEntityManager {

    private final EventStore eventStore;

    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Autowired
    @Qualifier("algorithmAggregateEventSourcingRepository")
    private EventSourcingRepository<AlgorithmAggregate> algorithmAggregateEventSourcingRepository;

    public AlgorithmQueryEntityManager(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @EventHandler
    void on(AlgorithmBaseEvent<String> event){
        persistAlgorithm(buildQueryAlgorithm(getAlgorithmFromEvent(event)));
    }

    private AlgorithmAggregate getAlgorithmFromEvent(AlgorithmBaseEvent<String> event){
        log.info("AlgorithmQueryEntityManager : getAlgorithmFromEvent {}", event);
        return algorithmAggregateEventSourcingRepository.load(event.algorithmId).getWrappedAggregate().getAggregateRoot();
    }

    private AlgorithmQueryEntity findExistingOrCreateAlgorithmQueryEntity(String algorithmId){
        log.info("AlgorithmQueryEntityManager : findExistingOrCreateAlgorithmQueryEntity {}", algorithmId);
        Optional<AlgorithmQueryEntity> optionalAlgorithmQueryEntity = algorithmRepository.findById(algorithmId);
        return optionalAlgorithmQueryEntity.orElseGet(AlgorithmQueryEntity::new);
    }

    private AlgorithmQueryEntity buildQueryAlgorithm(AlgorithmAggregate algorithmAggregate){
        log.info("AlgorithmQueryEntityManager : buildQueryAlgorithm {}", algorithmAggregate);
        AlgorithmQueryEntity algorithmQueryEntity = findExistingOrCreateAlgorithmQueryEntity(algorithmAggregate.getAlgorithmId());

        algorithmQueryEntity.setAlgorithmId(algorithmAggregate.getAlgorithmId());
        algorithmQueryEntity.setName(algorithmAggregate.getName());
        algorithmQueryEntity.setDescription(algorithmAggregate.getDescription());
        algorithmQueryEntity.setCron(algorithmAggregate.getCron());

        return algorithmQueryEntity;
    }

    private void persistAlgorithm(AlgorithmQueryEntity algorithmQueryEntity){
        log.info("AlgorithmQueryEntityManager : persistAlgorithm {}", algorithmQueryEntity);
        algorithmRepository.save(algorithmQueryEntity);
    }

    @QueryHandler
    private AlgorithmQueryEntity handle(GetAlgorithmQuery query) {
        log.info("AlgorithmQueryEntityManager : Handling GetAlgorithmQuery {}", query);
        return algorithmRepository.findAlgorithmQueryEntityByAlgorithmId(query.getAlgorithmId());
    }

    @QueryHandler
    private List<Object> handle(GetAlgorithmEventListQuery query) {
        log.info("AlgorithmQueryEntityManager : Handling GetAlgorithmEventListQuery {}", query);
        return eventStore.readEvents(query.getAlgorithmId()).asStream().collect(Collectors.toList());
    }
}
