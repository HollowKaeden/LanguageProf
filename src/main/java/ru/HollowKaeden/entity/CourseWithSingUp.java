package ru.HollowKaeden.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseWithSingUp extends Course{
    private boolean added;

    public CourseWithSingUp(Course course, boolean added) {
        setId(course.getId());
        setName(course.getName());
        setImage(course.getImage());
        setDescription(course.getDescription());
        setDate(course.getDate());
        setPrice(course.getPrice());
        setAdded(added);
    }
}