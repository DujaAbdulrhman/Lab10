package com.example.lab10.Controller;


import com.example.lab10.ApiResponse.Api;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity adduser(@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.adduser(user);
        return ResponseEntity.status(200).body(new Api("User added successfully"));
    }

    @GetMapping("/getall")
    public ResponseEntity getallusers(){
        return ResponseEntity.status(200).body(userService.getusers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateuser(@PathVariable int id,@Valid @RequestBody User user,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean updated=userService.updateuser(id, user);
        if (updated){
            return ResponseEntity.status(200).body(new Api("User updated successfully"));
        }else return ResponseEntity.status(400).body("not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteuser(@PathVariable int id){
        Boolean deleted=userService.deleteuser(id);
        if (deleted){
            return ResponseEntity.status(200).body(new Api("User deleted successfully"));
        }else  return ResponseEntity.status(400).body(new Api("User not found"));
    }

}
