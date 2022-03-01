package com.ing.interview.infrastructure;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarAvailabilityRestConnector {

    private static final Set<String> STOCK = new HashSet<String>() {{
        add("PEUGEOT:BLUE");
        add("FIAT:YELLOW");
        add("MERCEDES:BLACK");
    }};

    public boolean available(String color, String model) {
        String colorModel = model + ":" + color;

        return STOCK.contains(colorModel);
    }

}
