package ru.HollowKaeden.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CourseDTO {
    private String name;
    private String image;
    private String description;
    private Integer price;
    private String date;
}
