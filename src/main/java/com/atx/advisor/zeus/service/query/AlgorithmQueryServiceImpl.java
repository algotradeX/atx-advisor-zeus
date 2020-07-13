package com.atx.advisor.zeus.service.query;

import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import com.atx.advisor.zeus.query.repository.AlgorithmRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AlgorithmQueryServiceImpl implements AlgorithmQueryService {

    private final EventStore eventStore;

    private final AlgorithmRepository algorithmRepository;

    @Autowired
    public AlgorithmQueryServiceImpl(EventStore eventStore, AlgorithmRepository algorithmRepository) {
        this.eventStore = eventStore;
        this.algorithmRepository = algorithmRepository;
    }

    @Override
    public List<Object> listEventsForAlgorithm(String algorithmId) {
        log.info("AlgorithmQueryServiceImpl : querying listEventsForAlgorithm {}", algorithmId);
        return eventStore.readEvents(algorithmId).asStream().map(Message::getPayload).collect(Collectors.toList());
    }

    @Override
    public AlgorithmQueryEntity getAlgorithmById(String algorithmId) {
        log.info("AlgorithmQueryServiceImpl : querying getAlgorithmById {}", algorithmId);
        Optional<AlgorithmQueryEntity> optionalAlgorithmQueryEntity = algorithmRepository.findById(algorithmId);
        return optionalAlgorithmQueryEntity.orElseGet(AlgorithmQueryEntity::new);
    }
}
