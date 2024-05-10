package ru.HollowKaeden.controllers;

import lombok.AllArgsConstructor;
import ru.HollowKaeden.DTO.UserDTO;
import ru.HollowKaeden.entity.User;
import ru.HollowKaeden.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<User> create(@RequestParam String email, @RequestParam String username,
                                          @RequestParam String password, @RequestParam String tel, Model model) {
        UserDTO dto = new UserDTO(username, password, email, tel, "USER");
        return new ResponseEntity<>(userService.addUser(dto), HttpStatus.OK);
    }
}
