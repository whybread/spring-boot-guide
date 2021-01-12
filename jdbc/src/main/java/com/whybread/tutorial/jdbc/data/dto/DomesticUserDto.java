package com.whybread.tutorial.jdbc.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DomesticUserDto {
    private int seq;
    private String name;
    private String country;
}