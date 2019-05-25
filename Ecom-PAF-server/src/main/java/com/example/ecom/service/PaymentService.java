package com.example.ecom.service;

import com.example.ecom.exception.BadRequestException;
import com.example.ecom.model.Payment;
import com.example.ecom.payload.PayListResponse;
import com.example.ecom.payload.PaymentResponse;
import com.example.ecom.repository.PaymentRepository;
import com.example.ecom.repository.UserRepository;
import com.example.ecom.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    public PayListResponse<PaymentResponse> getAllPayments(String in, String to) {

        validatePageNumberAndSize(in, to);

        List<Payment> payments = paymentRepository.findAll();

        if(payments.size() == 0){
            return new PayListResponse<>(null,0);
        }

        return new PayListResponse<>(payments, payments.size());

    }

    private void validatePageNumberAndSize(String in, String to) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date_in = format.parse(in);

            //if not valid, it will throw ParseException
            Date date_to = format.parse(to);



        } catch (ParseException e) {
            e.printStackTrace();
            throw new BadRequestException("Date must be in \"yyyy-MM-dd\" format.");
        }

    }



    public PaymentResponse Pay(Long productId, int quantity) {
        return new PaymentResponse();
    }
}
