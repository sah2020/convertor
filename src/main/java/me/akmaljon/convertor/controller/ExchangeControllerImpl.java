package me.akmaljon.convertor.controller;

import me.akmaljon.convertor.payload.ApiResponse;
import me.akmaljon.convertor.payload.ExchangeReqDTO;
import me.akmaljon.convertor.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExchangeControllerImpl implements ExchangeController {
    @Autowired
    ExchangeService exchangeService;

    @Override
    public ApiResponse exchange(ExchangeReqDTO exchangeReqDTO) throws IOException {
        return exchangeService.exchange(exchangeReqDTO);
    }
}
