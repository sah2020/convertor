package me.akmaljon.convertor.controller;

import me.akmaljon.convertor.payload.ApiResponse;
import me.akmaljon.convertor.payload.ExchangeReqDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/exchange")
public interface ExchangeController {
    @PostMapping
    ApiResponse exchange(@RequestBody ExchangeReqDTO exchangeReqDTO) throws IOException;

}
