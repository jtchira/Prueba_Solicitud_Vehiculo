package com.ing.interview.infrastructure;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InsuranceRestConnector {

    private static final Map<String, Integer> KYC = new HashMap<String, Integer>() {{
        put("PEUGEOT", 18);
        put("FIAT", 20);
        put("MERCEDES", 50);
    }};

    public boolean isEligible(int age, String model) {
        return KYC.get(model) <= age;
    }

}
