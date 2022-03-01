package com.ing.interview.service;

import com.ing.interview.infrastructure.CarAvailabilityRestConnector;
import com.ing.interview.infrastructure.CarRepository;
import java.time.LocalDate;

import com.ing.interview.infrastructure.ColorPickerRestConnector;
import com.ing.interview.infrastructure.InsuranceRestConnector;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CarService {

    @Autowired
    private CarAvailabilityRestConnector availabilityService;

    @Autowired
    private ColorPickerRestConnector colorPickerService;

    @Autowired
    private InsuranceRestConnector insuranceService;

    @Autowired
    private CarRepository carRepository;

    public Car create(CarCommand carCommand){

        Model.valueOf(carCommand.getModel());
        String color = getColor(carCommand);
        checkAvailability(color, carCommand);
        checkInsurance(carCommand);

        final Car car = Car.builder()
            .color(color)
            .model(carCommand.getModel())
            .orderDate(LocalDate.now())
            .build();

        carRepository.save(car);

        return car;
    }

    private String getColor(CarCommand carCommand){
        String color;
        if(StringUtils.isBlank(carCommand.getColor())){
            color = colorPickerService.pickColor(carCommand.getModel())
                    .orElseThrow(() ->  new NotDefaultColorException(
                            String.format("Not default color for model %s",
                                    carCommand.getModel())
                    ));
        }
        else{
            color = carCommand.getColor();
        }
        return color;
    }

    private void checkAvailability(String color, CarCommand carCommand){
        if(!availabilityService.available(color, carCommand.getModel())){
            throw new StockNotAvailableException(
                    String.format("Stock not available for color %s and model %s",
                            carCommand.getColor(),
                            carCommand.getModel()));
        }
    }

    private void checkInsurance(CarCommand carCommand){
        if(!insuranceService.isEligible(carCommand.getAge(), carCommand.getModel())){
            throw new NotEligibleInsuranceException(
                    String.format("Insurance not eligible for age %d and model %s",
                            carCommand.getAge(),
                            carCommand.getModel()));
        }
    }

}
