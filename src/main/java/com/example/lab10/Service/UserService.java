package com.example.lab10.Service;


import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean adduser(@Valid User user){
        userRepository.save(user);
        return true;
    }

    public List<User> getusers(){
        return userRepository.findAll();
    }

    public boolean updateuser(int id,@Valid User user){
        User oldone=userRepository.getReferenceById(id);
        if (oldone==null){
            return false;
        }
        oldone.setName(user.getName());
        oldone.setEmail(user.getEmail());
        oldone.setAge(user.getAge());
        oldone.setRole(user.getRole());

        userRepository.save(oldone);
        return true;
    }


    public  boolean deleteuser(int id){
        User oldone=userRepository.getReferenceById(id);

        if (oldone==null){
            return false;
        }
        userRepository.delete(oldone);
        return true;
    }
}
