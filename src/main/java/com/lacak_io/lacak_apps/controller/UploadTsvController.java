package com.lacak_io.lacak_apps.controller;

import com.lacak_io.lacak_apps.service.serviceimpl.UploadFileTsvServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class UploadTsvController {

    private final UploadFileTsvServiceImpl uploadFileTsvService;

    @PostMapping
    public String importCities() {
        uploadFileTsvService.uploadFileTsv("data/cities_canada-usa.tsv");
        return "Import sukses!";
    }

}
