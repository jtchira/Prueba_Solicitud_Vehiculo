package com.ing.interview.presentation;

import com.ing.interview.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseEntity<Car> create(@RequestBody CarCommand carCommand) {
        log.info("Begin create car request");
        Car car = carService.create(carCommand);
        URI carUri = URI.create("/car/" + car.getId());
        log.info("Successful car creation");
        return  ResponseEntity.created(carUri).body(car);
    }

}
