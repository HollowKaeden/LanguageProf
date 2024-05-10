package ru.HollowKaeden.controllers;

import lombok.AllArgsConstructor;
import ru.HollowKaeden.DTO.CourseDTO;
import ru.HollowKaeden.entity.Course;
import ru.HollowKaeden.entity.User;
import ru.HollowKaeden.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.HollowKaeden.service.UserService;

import java.util.List;
import java.util.Optional;


@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService user_service;
    private final CourseService course_service;

    @GetMapping("/admin")
    public String admin(@RequestParam(required = false) String username, Model model) {
        if (username == null) {
            model.addAttribute("email", "example@example.com");
            model.addAttribute("telnum", "+7(000)000-00-00");
        }
        else {
            Optional<User> user = user_service.findByName(username);
            if (user.isPresent())
            {
                model.addAttribute("email", user.get().getEmail());
                model.addAttribute("telnum", user.get().getTel_num());
            }
            else {
                model.addAttribute("email", "Пользователь не найден");
                model.addAttribute("telnum", "Пользователь не найден");
            }
        }
        return "admin";
    }

    @PostMapping(value = "/admin")
    public ResponseEntity<Course> create(@RequestParam String courseName,
                                         @RequestParam String courseImage,
                                         @RequestParam String courseDescription,
                                         @RequestParam Integer coursePrice,
                                         @RequestParam String courseDate,
                                         Model model) {
        CourseDTO dto = new CourseDTO(courseName, courseImage, courseDescription, coursePrice, courseDate);
        return new ResponseEntity<>(course_service.addCourse(dto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin")
    public void delete(@RequestParam String courseDelete, Model model) {
        course_service.deleteByName(courseDelete);
    }
}