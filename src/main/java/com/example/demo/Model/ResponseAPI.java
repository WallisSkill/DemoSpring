package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder(value = {"status", "message", "data"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPI
{
    private String status;
    private String message;
    private Object data;

}
