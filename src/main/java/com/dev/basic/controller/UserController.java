package com.dev.basic.controller;
import com.dev.basic.dto.FindProfileDto;
import com.dev.basic.dto.UserDto;
import com.dev.basic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }
    @PostMapping("/find")
    public FindProfileDto find(@RequestBody FindProfileDto findProfileDto){
        return userService.findProfile(findProfileDto);
    }
}
