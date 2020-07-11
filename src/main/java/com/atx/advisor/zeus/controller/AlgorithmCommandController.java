package com.atx.advisor.zeus.controller;

import com.atx.advisor.zeus.common.dto.AlgorithmCreateDTO;
import com.atx.advisor.zeus.service.command.AlgorithmCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Controller
@RestController
@RequestMapping(value = "/algorithm")
@Api(value = "Endpoint for Algorithm Commands", tags = "Algorithm Commands")
@Slf4j
public class AlgorithmCommandController {

    private final AlgorithmCommandService algorithmCommandService;

    @Autowired
    public AlgorithmCommandController(AlgorithmCommandService algorithmCommandService) {
        this.algorithmCommandService = algorithmCommandService;
    }

    @ApiOperation(value = "API to GET algorithm", notes = "Get algorithm information", tags = { "Algorithm" })
    @PostMapping(value = "/create")
    public CompletableFuture<String> createAlgorithm(@RequestBody AlgorithmCreateDTO algorithmCreateDTO) {
        log.info("AlgorithmCommandController : POST /algorithm/create {}", algorithmCreateDTO);
        return algorithmCommandService.createAlgorithm(algorithmCreateDTO);
    }

}
