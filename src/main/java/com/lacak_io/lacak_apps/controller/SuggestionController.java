package com.lacak_io.lacak_apps.controller;


import com.lacak_io.lacak_apps.model.SuggestionResponse;
import com.lacak_io.lacak_apps.service.serviceimpl.SuggestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class SuggestionController {

    private final SuggestionServiceImpl suggestionService;

    @GetMapping
    public Map<String, Object> getSuggestions(
            @RequestParam String q,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    ) {
        List<SuggestionResponse> suggestions = suggestionService.suggestionResponses(q, latitude, longitude);
        Map<String, Object> response = new HashMap<>();
        response.put("suggestions", suggestions);
        return response;
    }
}
