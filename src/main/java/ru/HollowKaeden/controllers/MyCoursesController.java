package ru.HollowKaeden.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.HollowKaeden.DTO.CourseDTO;
import ru.HollowKaeden.DTO.UserCourseDTO;
import ru.HollowKaeden.entity.Course;
import ru.HollowKaeden.entity.CourseWithSingUp;
import ru.HollowKaeden.entity.User;
import ru.HollowKaeden.entity.UserCourse;
import ru.HollowKaeden.service.CourseService;
import ru.HollowKaeden.service.UserCourseService;
import ru.HollowKaeden.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class MyCoursesController {
    private final UserService user_service;
    private final CourseService course_service;
    private final UserCourseService user_course_service;

    @GetMapping("/my_courses")
    public String courses(Model model, Principal principal) {
        User user = user_service.getUserByPrincipal(principal);
        List<UserCourse> userCourses = user_course_service.getUserCourses(user);
        List<CourseWithSingUp> found_courses = new ArrayList<CourseWithSingUp>();
        for (UserCourse userCourse : userCourses)
        {
            found_courses.add(new CourseWithSingUp(userCourse.getCourse(), true));
        }
        model.addAttribute("courses", found_courses);

        return "my_courses";
    }

    @PostMapping("/my_courses")
    public ResponseEntity<UserCourse> create(@RequestParam Long course_id,
                                             Model model, Principal principal) {
        Course course = course_service.getByID(course_id);
        User user = user_service.getUserByPrincipal(principal);
        UserCourseDTO dto = new UserCourseDTO(course, user);
        user_course_service.addUserCourse(dto);
        return new ResponseEntity<>(user_course_service.addUserCourse(dto), HttpStatus.OK);
    }
}
