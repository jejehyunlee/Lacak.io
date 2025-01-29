package com.lacak_io.lacak_apps.repository;

import com.lacak_io.lacak_apps.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findTop10ByNameContainingIgnoreCaseOrderByPopulationDesc(String name);
}
