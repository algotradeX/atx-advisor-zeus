package com.atx.advisor.zeus.aggregate;

import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
public class AlgorithmAggregate {

    @AggregateIdentifier
    private String id;
}
