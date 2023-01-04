package de.neuefische.movedbapi;

import lombok.Data;

@Data
public class Movie {
    private String id;
    private String title;
    private String posterUrl;
    private int year;
}
