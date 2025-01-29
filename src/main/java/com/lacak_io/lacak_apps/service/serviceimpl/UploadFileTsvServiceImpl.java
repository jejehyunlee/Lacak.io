package com.lacak_io.lacak_apps.service.serviceimpl;

import com.lacak_io.lacak_apps.entity.Location;
import com.lacak_io.lacak_apps.repository.LocationRepository;
import com.lacak_io.lacak_apps.service.UploadFileTsvService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadFileTsvServiceImpl implements UploadFileTsvService {

    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public void uploadFileTsv(String filePath) {
        List<Location> locationList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // Flag untuk mengecek header

            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // Lewati header di baris pertama
                    isFirstLine = false;
                    continue;
                }

                String[] fields = line.split("\t"); // Pisahkan berdasarkan TAB

                if (fields.length < 19) continue; // Pastikan ada cukup kolom

                try {
                    Location location = new Location();
                    location.setName(fields[1]);
                    location.setAscii(fields[2]);
                    location.setAltName(fields[3]);
                    location.setLat(parseDoubleSafe(fields[4])); // Gunakan metode aman
                    location.setLon(parseDoubleSafe(fields[5]));
                    location.setFeatClass(fields[6]);
                    location.setFeatCode(fields[7]);
                    location.setCountry(fields[8]);
                    location.setCc2(fields[9]);
                    location.setAdmin1(fields[10]);
                    location.setAdmin2(fields[11]);
                    location.setAdmin3(fields[12]);
                    location.setAdmin4(fields[13]);
                    location.setPopulation(parseLongSafe(fields[14]));
                    location.setElevation(parseIntegerSafe(fields[15]));
                    location.setDem(parseIntegerSafe(fields[16]));
                    location.setTz(fields[17]);
                    location.setModifiedAt(fields[18]);

                    locationList.add(location);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid row: " + line);
                }
            }

            locationRepository.saveAll(locationList); // Simpan ke database

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // Nilai default jika parsing gagal
        }
    }

    private int parseIntegerSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private long parseLongSafe(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }


}

