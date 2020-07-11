package com.atx.advisor.zeus.query.repository;

import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmRepository extends CrudRepository<AlgorithmQueryEntity, String> {
}