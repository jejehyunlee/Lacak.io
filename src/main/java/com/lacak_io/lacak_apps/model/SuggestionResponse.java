package com.lacak_io.lacak_apps.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionResponse {

    private String name;
    private double latitude;
    private double longitude;
    private double score;


}
