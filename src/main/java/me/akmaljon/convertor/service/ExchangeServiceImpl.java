package me.akmaljon.convertor.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.akmaljon.convertor.entity.Currency;
import me.akmaljon.convertor.entity.Exchange;
import me.akmaljon.convertor.payload.ApiResponse;
import me.akmaljon.convertor.payload.ExchangeReqDTO;
import me.akmaljon.convertor.payload.ExchangeResDTO;
import me.akmaljon.convertor.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    ExchangeRepository exchangeRepository;

    @Override
    public ApiResponse exchange(ExchangeReqDTO exchangeReqDTO) throws IOException {
        Double amount = exchangeReqDTO.getAmount();
        String fromCurrency = exchangeReqDTO.getFromCurrency();
        String to = exchangeReqDTO.getToCurrency();

        Currency currency = getApi();
        Double euroToCNY = currency.getRates().getCNY();
        Double euroToGBP = currency.getRates().getGBP();
        Double euroToUSD = currency.getRates().getUSD();
        Double euroToUZS = currency.getRates().getUZS();

        ExchangeResDTO exchangeResDTO = new ExchangeResDTO();

        if (fromCurrency.equals(to)) exchangeResDTO.setResult(amount);

        if (fromCurrency.equals("EUR") && to.equals("CNY")) exchangeResDTO.setResult(amount * euroToCNY);
        if (fromCurrency.equals("EUR") && to.equals("GBP")) exchangeResDTO.setResult(amount * euroToGBP);
        if (fromCurrency.equals("EUR") && to.equals("USD")) exchangeResDTO.setResult(amount * euroToUSD);
        if (fromCurrency.equals("EUR") && to.equals("UZS")) exchangeResDTO.setResult(amount * euroToUZS);

        if (fromCurrency.equals("USD") && to.equals("EUR")) exchangeResDTO.setResult(amount / euroToUSD);
        if (fromCurrency.equals("USD") && to.equals("CNY")) exchangeResDTO.setResult(amount * euroToCNY / euroToUSD);
        if (fromCurrency.equals("USD") && to.equals("GBP")) exchangeResDTO.setResult(amount * euroToGBP / euroToUSD);
        if (fromCurrency.equals("USD") && to.equals("UZS")) exchangeResDTO.setResult(amount * euroToUZS / euroToUSD);

        if (fromCurrency.equals("CNY") && to.equals("EUR")) exchangeResDTO.setResult(amount / euroToCNY);
        if (fromCurrency.equals("CNY") && to.equals("USD")) exchangeResDTO.setResult(amount * euroToUSD / euroToCNY);
        if (fromCurrency.equals("CNY") && to.equals("GBP")) exchangeResDTO.setResult(amount * euroToGBP / euroToCNY);
        if (fromCurrency.equals("CNY") && to.equals("UZS")) exchangeResDTO.setResult(amount * euroToUZS / euroToCNY);

        if (fromCurrency.equals("GBP") && to.equals("EUR")) exchangeResDTO.setResult(amount / euroToGBP);
        if (fromCurrency.equals("GBP") && to.equals("CNY")) exchangeResDTO.setResult(amount * euroToCNY / euroToGBP);
        if (fromCurrency.equals("GBP") && to.equals("USD")) exchangeResDTO.setResult(amount * euroToUSD / euroToGBP);
        if (fromCurrency.equals("GBP") && to.equals("UZS")) exchangeResDTO.setResult(amount * euroToUZS / euroToGBP);

        if (fromCurrency.equals("UZS") && to.equals("EUR")) exchangeResDTO.setResult(amount / euroToUZS);
        if (fromCurrency.equals("UZS") && to.equals("CNY")) exchangeResDTO.setResult(amount * euroToCNY / euroToUZS);
        if (fromCurrency.equals("UZS") && to.equals("GBP")) exchangeResDTO.setResult(amount * euroToGBP / euroToUZS);
        if (fromCurrency.equals("UZS") && to.equals("USD")) exchangeResDTO.setResult(amount * euroToUSD / euroToUZS);

        Exchange exchange = new Exchange();
        exchange.setAmount(exchangeReqDTO.getAmount());
        exchange.setFromCurrency(exchangeReqDTO.getFromCurrency());
        exchange.setToCurrency(exchangeReqDTO.getToCurrency());
        exchange.setResult(exchangeResDTO.getResult());
        exchangeRepository.save(exchange);

        return new ApiResponse("success", true, exchangeResDTO);
    }

    Currency getApi() throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("HH:mm:ss.SSS'Z'")
                .create();

        URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=bb4bbbff2fd9200551a9813ba6f1e9c0&symbols=gbp,usd,cny,uzs");
        URLConnection connection = url.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        Type type = new TypeToken<Currency>() {
        }.getType();
        Currency currencies = gson.fromJson(reader, type);
        return currencies;
    }
}
