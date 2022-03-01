package com.ing.interview.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ing.interview.infrastructure.CarAvailabilityRestConnector;
import com.ing.interview.infrastructure.CarRepository;


import com.ing.interview.infrastructure.ColorPickerRestConnector;
import com.ing.interview.infrastructure.InsuranceRestConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class CarServiceTest {

    private CarService sut;
    private CarRepository carRepository;
    private CarAvailabilityRestConnector availabilityService;
    private ColorPickerRestConnector colorPickerService;
    private InsuranceRestConnector insuranceService;

    @BeforeEach
    public void setUp() {
        carRepository = mock(CarRepository.class);
        availabilityService = mock(CarAvailabilityRestConnector.class);
        colorPickerService = mock(ColorPickerRestConnector.class);
        insuranceService = mock(InsuranceRestConnector.class);
        sut = new CarService(availabilityService, colorPickerService, insuranceService , carRepository);
    }

    @Test
    void createCar(){
        // given
        CarCommand carCommand = CarCommand.builder()
            .age(16)
            .color("PINK")
            .model("FIAT")
            .build();

        // when
        when(colorPickerService.pickColor(carCommand.getModel())).thenReturn(Optional.ofNullable(carCommand.getColor()));
        when(availabilityService.available(carCommand.getColor(), carCommand.getModel())).thenReturn(true);
        when(insuranceService.isEligible(carCommand.getAge(), carCommand.getModel())).thenReturn(true);
        final Car result = sut.create(carCommand);

        // then
        assertEquals("PINK", result.getColor());
        assertEquals("FIAT", result.getModel());
    }

    @Test
    void createCarNoStock(){
        // given
        CarCommand carCommand = CarCommand.builder()
                .age(20)
                .color("RED")
                .model("FIAT")
                .build();

        // when
        when(colorPickerService.pickColor(carCommand.getModel())).thenReturn(Optional.ofNullable(carCommand.getColor()));
        when(availabilityService.available(carCommand.getColor(), carCommand.getModel())).thenReturn(false);
        when(insuranceService.isEligible(carCommand.getAge(), carCommand.getModel())).thenReturn(true);

        // then
        assertThrows(StockNotAvailableException.class ,()-> sut.create(carCommand));
    }

    @Test
    void createCarNoInsurance(){
        // given
        CarCommand carCommand = CarCommand.builder()
                .age(12)
                .color("YELLOW")
                .model("FIAT")
                .build();

        // when
        when(colorPickerService.pickColor(carCommand.getModel())).thenReturn(Optional.ofNullable(carCommand.getColor()));
        when(availabilityService.available(carCommand.getColor(), carCommand.getModel())).thenReturn(true);
        when(insuranceService.isEligible(carCommand.getAge(), carCommand.getModel())).thenReturn(false);

        // then
        assertThrows(NotEligibleInsuranceException.class ,()-> sut.create(carCommand));
    }

    @Test
    void createCarDefaultColor(){
        // given
        CarCommand carCommand = CarCommand.builder()
                .age(20)
                .model("FIAT")
                .build();

        // when
        String color = "YELLOW";
        when(colorPickerService.pickColor(carCommand.getModel())).thenReturn(Optional.ofNullable(color));
        when(availabilityService.available(color, carCommand.getModel())).thenReturn(true);
        when(insuranceService.isEligible(carCommand.getAge(), carCommand.getModel())).thenReturn(true);

        final Car result = sut.create(carCommand);

        // then
        assertEquals(color, result.getColor());
        assertEquals("FIAT", result.getModel());
    }

    @Test
    void createCarUnsupportedModel(){
        // given
        CarCommand carCommand = CarCommand.builder()
                .age(20)
                .color("YELLOW")
                .model("FIA")
                .build();
        // then
        assertThrows(IllegalArgumentException.class ,()-> sut.create(carCommand));
    }

}