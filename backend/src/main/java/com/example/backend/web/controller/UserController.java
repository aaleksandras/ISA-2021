package com.example.backend.web.controller;

import com.example.backend.email.EmailSender;
import com.example.backend.model.user.User;
import com.example.backend.service.IUserService;
import com.example.backend.web.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    EmailSender sender;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody CreateUserDto dto) {
        User user = userService.createUser(dto);
        try{
            if(user != null){
                sender.sendVerificationEmail(user.getEmail(),user.getId().toString());
            }
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping("/activateAccount/{id}")
    public void activateAccount(@PathVariable String id, HttpServletResponse httpServletResponse){
        User user = this.userService.updateStatus(UUID.fromString(id));
        httpServletResponse.setHeader("Location", "http://localhost:4200/login");
        httpServletResponse.setStatus(302);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CreateUserDto dto){
        User user = userService.updateUser(UUID.fromString(id),dto);
        return new ResponseEntity<>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
