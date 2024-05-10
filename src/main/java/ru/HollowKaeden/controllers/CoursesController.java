package ru.HollowKaeden.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class CoursesController {
    private final UserService user_service;
    private final CourseService course_service;
    private final UserCourseService user_course_service;

    @GetMapping("/courses")
    public String courses(Model model, Principal principal) {

        User user = user_service.getUserByPrincipal(principal);
        if (user == null) model.addAttribute("courses", course_service.readAll());
        else {
            List<CourseWithSingUp> found_courses = new ArrayList<CourseWithSingUp>();
            for (Course course : course_service.readAll()) {
                found_courses.add(new CourseWithSingUp(course, false));
            }
            for (UserCourse userCourse : user_course_service.getUserCourses(user)) {
                for (CourseWithSingUp course : found_courses) {
                    if (userCourse.getCourse().getId() == course.getId()) {
                        course.setAdded(true);
                    }
                }
            }
            model.addAttribute("courses", found_courses);
        }

        return "courses";
    }

    @PostMapping("/courses")
    public ResponseEntity<UserCourse> create(@RequestParam Long course_id,
                                             Model model, Principal principal) {
        Course course = course_service.getByID(course_id);
        User user = user_service.getUserByPrincipal(principal);
        UserCourseDTO dto = new UserCourseDTO(course, user);
        user_course_service.addUserCourse(dto);
        return new ResponseEntity<>(user_course_service.addUserCourse(dto), HttpStatus.OK);
    }

    @DeleteMapping("/courses")
    public void delete(@RequestParam Long delete_course_id, Model model, Principal principal) {
        User user = user_service.getUserByPrincipal(principal);
        Course course = course_service.getByID(delete_course_id);
        user_course_service.deleteUserCourse(user, course);
    }
}
