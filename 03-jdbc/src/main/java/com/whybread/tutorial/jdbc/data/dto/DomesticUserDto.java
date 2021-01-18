package com.whybread.tutorial.jdbc.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Lombok auto-generates constructor with all argments.
@AllArgsConstructor
// Lombok auto-generates getter methods for each argment.
@Getter
// Lombok auto-generates setter methods for each argment.
@Setter
// A data transfer object for domestic-user.
public class DomesticUserDto {
    private int seq;
    private String name;
    private String country;
}