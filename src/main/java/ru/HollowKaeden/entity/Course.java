package ru.HollowKaeden.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Builder
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(unique = true)
    private String name;
    private String image;
    private String description;
    private Integer price;
    private String date;
}
