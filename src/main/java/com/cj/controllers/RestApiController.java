package com.cj.controllers;

import com.cj.service.DatabaseMetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    private DatabaseMetaDataService databaseMetaDataService;

    @Autowired
    public RestApiController(DatabaseMetaDataService databaseMetaDataService) {
        this.databaseMetaDataService = databaseMetaDataService;
    }

    @GetMapping("/hi")
    public String sayHi() {
        return "hi!";
    }

    @GetMapping(value = "/schemas")
    public ResponseEntity<String> getSchemas() {
        try {
            String schemasJson = databaseMetaDataService.getSchemas();
            return new ResponseEntity<>(schemasJson, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
