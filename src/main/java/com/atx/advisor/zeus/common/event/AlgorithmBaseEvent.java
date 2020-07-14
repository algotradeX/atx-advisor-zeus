package com.atx.advisor.zeus.common.event;

public class AlgorithmBaseEvent<T> {

    public final T algorithmId;

    public AlgorithmBaseEvent(T algorithmId) {
        this.algorithmId = algorithmId;
    }
}
