package com.lacak_io.lacak_apps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 5000)
    private String name;

    @Column(length = 5000)
    private String ascii;

    @Column(name = "alt_name", length = 5000)
    private String altName;
    private Double lat;
    private Double lon;

    @Column(name = "feat_class", length = 5000)
    private String featClass;

    @Column(name = "feat_code", length = 5000)
    private String featCode;

    @Column(length = 5000)
    private String country;

    @Column(length = 5000)
    private String cc2;

    @Column(length = 5000)
    private String admin1;

    @Column(length = 5000)
    private String admin2;

    @Column(length = 5000)
    private String admin3;

    @Column(length = 5000)
    private String admin4;
    private Long population;
    private Integer elevation;
    private Integer dem;

    @Column(length = 5000)
    private String tz;

    @Column(name = "modified_at", length = 5000)
    private String modifiedAt;
}

