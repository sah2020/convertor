package me.akmaljon.convertor.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @SerializedName("success")
    private boolean success;

    @SerializedName("timestamp")
    private Long timestamp;

    @SerializedName("base")
    private String base;

    @SerializedName("date")
    private Date date;

    @SerializedName("rates")
    private Values rates;
}
