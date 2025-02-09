package com.lacak_io.lacak_apps.service;

import com.lacak_io.lacak_apps.model.SuggestionResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SuggestionService {

    List<SuggestionResponse> suggestionResponses(String query, Double latitude, Double longitude);

}
