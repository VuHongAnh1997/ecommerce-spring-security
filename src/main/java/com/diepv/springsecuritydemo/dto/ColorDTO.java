package com.diepv.springsecuritydemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColorDTO {

    private Long colorId;

    private String colorName;

    private String imageUrl;
}
