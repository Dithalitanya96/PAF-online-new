package com.example.ecom.controller;

import com.example.ecom.payload.PayListResponse;
import com.example.ecom.payload.PaymentResponse;
import com.example.ecom.repository.PaymentRepository;
import com.example.ecom.repository.UserRepository;
import com.example.ecom.service.PaymentService;
import com.example.ecom.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentService paymentService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PayListResponse<PaymentResponse> getPayments(@RequestParam(value = "in") String in,
                                                        @RequestParam(value = "to") String to) {
        return paymentService.getAllPayments(in, to);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public PaymentResponse pay(@RequestParam(value = "product_id") Long productId,@RequestParam(value = "quantity") int quantity) {
        return paymentService.Pay(productId, quantity);
    }
}
