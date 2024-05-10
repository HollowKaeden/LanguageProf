package ru.HollowKaeden.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.HollowKaeden.entity.Course;
import ru.HollowKaeden.entity.User;


@Data
@AllArgsConstructor
public class UserCourseDTO {
    private Course course;
    private User user;
}
