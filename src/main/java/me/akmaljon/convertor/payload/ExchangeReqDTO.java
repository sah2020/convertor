package me.akmaljon.convertor.payload;

import lombok.Data;

@Data
public class ExchangeReqDTO {
    private Double amount;
    private String fromCurrency;
    private String toCurrency;
}
