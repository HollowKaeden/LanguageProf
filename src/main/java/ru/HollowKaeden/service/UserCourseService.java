package ru.HollowKaeden.service;

import lombok.AllArgsConstructor;
import ru.HollowKaeden.DTO.UserCourseDTO;
import ru.HollowKaeden.entity.Course;
import ru.HollowKaeden.entity.User;
import ru.HollowKaeden.entity.UserCourse;
import ru.HollowKaeden.repository.UserCourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class UserCourseService {

    private final UserCourseRepository repository;

    public UserCourse addUserCourse(UserCourseDTO dto) {
        UserCourse user_course = UserCourse.builder()
                .user(dto.getUser())
                .course(dto.getCourse())
                .build();
        return repository.save(user_course);
    }

    public List<UserCourse> readAll() {
        return repository.findAll();
    }

    public UserCourse update(UserCourse user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<UserCourse> getUserCourses(User user) {
        return repository.findByUserId(user.getId());
    }

    public void deleteUserCourse(User user, Course course)
    {
        List<UserCourse> userCourses = repository.findAll();
        for (UserCourse userCourse : userCourses)
        {
            if (userCourse.getCourse().getId() == course.getId() &&
                    Objects.equals(userCourse.getUser().getId(), user.getId()))
            {
                repository.deleteById(userCourse.getId());
                return;
            }
        }
    }
}