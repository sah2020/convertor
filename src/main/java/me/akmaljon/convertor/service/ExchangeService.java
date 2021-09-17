package me.akmaljon.convertor.service;

import me.akmaljon.convertor.payload.ApiResponse;
import me.akmaljon.convertor.payload.ExchangeReqDTO;

import java.io.IOException;

public interface ExchangeService {
    ApiResponse exchange(ExchangeReqDTO exchangeReqDTO) throws IOException;


}
