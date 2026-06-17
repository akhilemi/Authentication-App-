package com.akhilprog.authapp.Controller;

import com.akhilprog.authapp.Service.UserService;
import com.akhilprog.authapp.dtos.UserDto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    // get all user api
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable ("userId")String userId){
        userService.deleteUser(userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto , @PathVariable("userId") String userId){
        return ResponseEntity.ok(userService.updateUser(userDto ,userId));
    }

    @GetMapping("{userid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
