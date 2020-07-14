package com.atx.advisor.zeus.service.query;

import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import com.atx.advisor.zeus.common.query.GetAlgorithmEventListQuery;
import com.atx.advisor.zeus.common.query.GetAlgorithmQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AlgorithmQueryServiceImpl implements AlgorithmQueryService {

    private final QueryGateway queryGateway;

    @Autowired
    public AlgorithmQueryServiceImpl(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public CompletableFuture<List<Object>> listEventsForAlgorithm(String algorithmId) {
        log.info("AlgorithmQueryServiceImpl : querying listEventsForAlgorithm {}", algorithmId);
        return queryGateway.query(new GetAlgorithmEventListQuery(algorithmId), ResponseTypes.multipleInstancesOf(Object.class));
    }

    public CompletableFuture<AlgorithmQueryEntity> getAlgorithmById(String algorithmId) {
        log.info("AlgorithmQueryServiceImpl : querying getAlgorithmById {}", algorithmId);
        return queryGateway.query(new GetAlgorithmQuery(algorithmId), AlgorithmQueryEntity.class);
    }
}
