package com.atx.advisor.zeus.service.query;

import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface AlgorithmQueryService {

    CompletableFuture<List<Object>> listEventsForAlgorithm(String algorithmId);

    CompletableFuture<AlgorithmQueryEntity> getAlgorithmById(String algorithmId);
}
