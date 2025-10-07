package com.javbre.controller;

import com.javbre.dto.UserCreateRequest;
import com.javbre.service.AppService;
import com.javbre.utilities.HeadersUtilities;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${controller.properties.base-path}")
public class AppController {

    private final AppService appService;
    private final HeadersUtilities headersUtilities;

    public AppController(AppService appService, HeadersUtilities headersUtilities) {
        this.appService = appService;
        this.headersUtilities = headersUtilities;
    }

    @PostMapping("user/create")
    @Operation(description = "Permite crear un nuevo usuario")
    public ResponseEntity<String> create(@RequestHeader HttpHeaders headers,
                                         @Valid @RequestBody UserCreateRequest req) {
        headersUtilities.validateHeaders(headers);
        String messageResponse = appService.create(req);
        return ResponseEntity.ok(messageResponse);
    }
}

