package ru.HollowKaeden.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.HollowKaeden.DTO.UserDTO;
import ru.HollowKaeden.entity.Course;
import ru.HollowKaeden.entity.User;
import ru.HollowKaeden.entity.UserCourse;
import ru.HollowKaeden.service.CourseService;
import ru.HollowKaeden.service.UserCourseService;
import ru.HollowKaeden.service.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostmanController {
    private final UserService userService;
    private final CourseService courseService;
    private final UserCourseService userCourseService;

    @GetMapping(value = "/postman/users")
    public ResponseEntity<List<User>> getUsers(Model model) {
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/postman/courses")
    public ResponseEntity<List<Course>> getCourses(Model model) {
        return new ResponseEntity<>(courseService.readAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/postman/user_courses")
    public ResponseEntity<List<UserCourse>> getUserCourses(Model model) {
        return new ResponseEntity<>(userCourseService.readAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/postman/post_user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(userService.addUser(dto), HttpStatus.OK);
    }
}
