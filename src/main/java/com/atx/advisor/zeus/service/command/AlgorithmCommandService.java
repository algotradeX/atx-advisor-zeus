package com.atx.advisor.zeus.service.command;

import com.atx.advisor.zeus.common.dto.AlgorithmCreateDTO;
import com.atx.advisor.zeus.common.dto.AlgorithmUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface AlgorithmCommandService {

    CompletableFuture<String> createAlgorithm(AlgorithmCreateDTO algorithmCreateDTO);

    CompletableFuture<String> updateAlgorithm(AlgorithmUpdateDTO algorithmUpdateDTO);
}
