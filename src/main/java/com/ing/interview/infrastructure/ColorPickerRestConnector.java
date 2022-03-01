package com.ing.interview.infrastructure;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ColorPickerRestConnector {

    private static final Map<String, String> MAP = new HashMap<String, String>() {{
        put("PEUGEOT", "BLUE");
        put("FIAT", "YELLOW");
        put("MERCEDES", "GREY");
    }};

    public Optional<String> pickColor(String model) {
        return Optional.ofNullable(MAP.get(model));
    }

}
