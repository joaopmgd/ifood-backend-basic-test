package com.ifood.controllers;

import com.ifood.domain.AboutInformation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/about")
public class About {

    /**
     * About will return HTTP Status OK if it is up and running, just like a health check
     * Since the openweathermap does not have a health check, the status will be only of this current service status
     */
    @GetMapping
    public ResponseEntity<AboutInformation> About() {
        return ResponseEntity.status(HttpStatus.OK).body(new AboutInformation());
    }
}
