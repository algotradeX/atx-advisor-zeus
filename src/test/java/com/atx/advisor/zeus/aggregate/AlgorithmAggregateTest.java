package com.atx.advisor.zeus.aggregate;

import com.atx.advisor.zeus.common.command.CreateAlgorithmCommand;
import com.atx.advisor.zeus.common.command.UpdateAlgorithmCommand;
import com.atx.advisor.zeus.common.event.AlgorithmCreatedEvent;
import com.atx.advisor.zeus.common.event.AlgorithmUpdatedEvent;
import org.axonframework.eventsourcing.eventstore.EventStoreException;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlgorithmAggregateTest {

    private FixtureConfiguration<AlgorithmAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(AlgorithmAggregate.class);
    }

    @Test
    void testCreateAlgorithmCommand() {
        fixture.givenNoPriorActivity()
                .when(new CreateAlgorithmCommand("id", "name", "desc", "cron"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AlgorithmCreatedEvent("id", "name", "desc", "cron"));
    }

    @Test
    void testDuplicateCreateAlgorithmCommand() {
        fixture.givenCommands(new CreateAlgorithmCommand("id", "name", "desc", "cron"))
                .when(new CreateAlgorithmCommand("id", "name", "desc", "cron"))
                .expectException(EventStoreException.class);
    }

    @Test
    void testUpdateAlgorithmCommand() {
        fixture.givenCommands(new CreateAlgorithmCommand("id", "name", "desc", "cron"))
                .when(new UpdateAlgorithmCommand("id", "name", "desc", "cron"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AlgorithmUpdatedEvent("id", "name", "desc", "cron"));
    }

    @Test
    void testOnlyUpdateAlgorithmCommand() {
        fixture.givenNoPriorActivity()
                .when(new UpdateAlgorithmCommand("id", "name", "desc", "cron"))
                .expectException(AggregateNotFoundException.class);
    }

    @AfterEach
    void tearDown() {}
}