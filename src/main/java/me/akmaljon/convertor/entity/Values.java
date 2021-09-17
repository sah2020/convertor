package me.akmaljon.convertor.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Values {
    @SerializedName("GBP")
    private Double GBP;

    @SerializedName("USD")
    private Double USD;

    @SerializedName("CNY")
    private Double CNY;

    @SerializedName("UZS")
    private Double UZS;

}
