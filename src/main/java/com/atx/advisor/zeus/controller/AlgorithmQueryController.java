package com.atx.advisor.zeus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "/algorithm")
@Api(value = "Endpoint for Algorithm Queries", tags = "Algorithm Queries")
@Slf4j
public class AlgorithmQueryController {

    @ApiOperation(value = "API to GET algorithm", notes = "Get algorithm information", tags = { "Algorithm" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns algorithm model", response = String.class )
    })
    @GetMapping("/{algorithmId}")
    public String getAlgorithm(@PathVariable(value = "algorithmId") String algorithmId) {
        log.info("AlgorithmCommandController : GET /algorithm/{}", algorithmId);
        return algorithmId;
    }

}
