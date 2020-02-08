package com.braalex.brewery.service;

import com.braalex.brewery.dto.CustomerSignInRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public String signUp(final CustomerSignInRequest request) {
        return "{\"customerId\":1}";
    }

    public String signIn(final CustomerSignInRequest request) {
        return "{\"customerId\":1}";
    }

}
