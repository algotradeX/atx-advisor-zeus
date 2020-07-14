package com.atx.advisor.zeus.controller;

import com.atx.advisor.zeus.common.entity.AlgorithmQueryEntity;
import com.atx.advisor.zeus.common.response.AlgorithmListEventResponse;
import com.atx.advisor.zeus.service.query.AlgorithmQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RestController
@RequestMapping(value = "/algorithm")
@Api(value = "Endpoint for Algorithm Queries", tags = "Algorithm Queries")
@Slf4j
public class AlgorithmQueryController {

    private final AlgorithmQueryService algorithmQueryService;

    @Autowired
    public AlgorithmQueryController(AlgorithmQueryService algorithmQueryService) {
        this.algorithmQueryService = algorithmQueryService;
    }

    @ApiOperation(value = "API to GET algorithm entity", notes = "Get algorithm information", tags = { "Algorithm" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns AlgorithmQueryEntity model", response = AlgorithmQueryEntity.class )
    })
    @GetMapping("/{algorithmId}/entity")
    public CompletableFuture<AlgorithmQueryEntity> getAlgorithmById(@PathVariable(value = "algorithmId") String algorithmId) {
        log.info("AlgorithmQueryController : GET /algorithm/{}/entity", algorithmId);
        return algorithmQueryService.getAlgorithmById(algorithmId);
    }

    @ApiOperation(value = "API to GET algorithm events list", notes = "Get algorithm events", tags = { "Algorithm" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns AlgorithmQueryEntity model", response = AlgorithmListEventResponse.class )
    })
    @GetMapping("/{algorithmId}/events")
    public CompletableFuture<List<Object>> getAllEventsForAlgorithm(@PathVariable(value = "algorithmId") String algorithmId) {
        log.info("AlgorithmQueryController : GET /algorithm/{}/events", algorithmId);
        return algorithmQueryService.listEventsForAlgorithm(algorithmId);
    }

}
