package com.atx.advisor.zeus.service.command;

import com.atx.advisor.zeus.common.command.CreateAlgorithmCommand;
import com.atx.advisor.zeus.common.command.UpdateAlgorithmCommand;
import com.atx.advisor.zeus.common.dto.AlgorithmCreateDTO;
import com.atx.advisor.zeus.common.dto.AlgorithmUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AlgorithmCommandServiceImpl implements AlgorithmCommandService {

    private final CommandGateway commandGateway;

    public AlgorithmCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAlgorithm(AlgorithmCreateDTO algorithmCreateDTO) {
        log.info("AlgorithmCommandServiceImpl : starting createAlgorithm operation {}", algorithmCreateDTO);
        return commandGateway.send(new CreateAlgorithmCommand(algorithmCreateDTO.getAlgorithmId(), algorithmCreateDTO.getName(), algorithmCreateDTO.getDescription(), algorithmCreateDTO.getCron()));
    }

    public CompletableFuture<String> updateAlgorithm(AlgorithmUpdateDTO algorithmUpdateDTO) {
        log.info("AlgorithmCommandServiceImpl : starting updateAlgorithm operation {}", algorithmUpdateDTO);
        return commandGateway.send(new UpdateAlgorithmCommand(algorithmUpdateDTO.getAlgorithmId(), algorithmUpdateDTO.getName(), algorithmUpdateDTO.getDescription(), algorithmUpdateDTO.getCron()));
    }
}
