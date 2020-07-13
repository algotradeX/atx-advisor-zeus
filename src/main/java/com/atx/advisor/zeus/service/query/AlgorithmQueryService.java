package com.atx.advisor.zeus.service.query;

import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlgorithmQueryService {

    List<Object> listEventsForAlgorithm(String algorithmId);

    AlgorithmQueryEntity getAlgorithmById(String algorithmId);
}
