package com.lacak_io.lacak_apps.service.serviceimpl;

import com.lacak_io.lacak_apps.entity.Location;
import com.lacak_io.lacak_apps.model.SuggestionResponse;
import com.lacak_io.lacak_apps.repository.LocationRepository;
import com.lacak_io.lacak_apps.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

    private final LocationRepository locationRepository;

    @Override
    public List<SuggestionResponse> suggestionResponses(String query, Double latitude, Double longitude) {

       List<Location> locationList = locationRepository.findTop10ByNameContainingIgnoreCaseOrderByPopulationDesc(query);


        return locationList.stream().map(location -> {
            double score = calculateScore(location, query, latitude, longitude);
            return new SuggestionResponse(location.getName(), location.getLat(), location.getLon(), score);
        })
                .sorted((SuggestionResponse o1, SuggestionResponse o2) -> Double.compare(o2.getScore(), o1.getScore()))
                .collect(Collectors.toList());
    }

    private Double calculateScore(Location location, String query, Double latitude, Double longitude) {
        // TODO: implement score calculation logic

        double baseScore = location.getName().toLowerCase().startsWith(query.toLowerCase()) ? 0.7 : 0.5;
        double populationScore = Math.log10(location.getPopulation() + 1) / 10.0;

        if (latitude != null && longitude != null) {
            double distanceactor = 1 / (1 + calculateDistance(location.getLat(), location.getLon(), latitude, longitude));
            return Math.min(1.0, baseScore + populationScore * 0.2 + distanceactor * 0.5);
        }
        return Math.min(1.0, baseScore + populationScore * 0.3);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // TODO: implement distance calculation logic
        final int R = 6371; // Radius of the earth
        double latDiff = Math.toRadians(lat2 - lat1);
        double lonDiff = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return R * distance;
    }

}
