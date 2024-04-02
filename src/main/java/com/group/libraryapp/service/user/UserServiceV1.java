package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {
    private final UserJdbcRepository userRepository;
    public UserServiceV1(UserJdbcRepository userRepository){
        this.userRepository = userRepository;
    }
    public void saveUser(String name, long age){
        userRepository.saveUser(name, age);
    }
    public List<UserResponse> getUsers(){
        return userRepository.getUsers();
    }
    public void updateUser(UserUpdateRequest request) { //@RequestBody가 없다. 그냥 전달하는 객체만 받으면 되기 때문이다.
         // id가
        if (userRepository.isUserNotExist( request.getId())){
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(request.getName(), request.getId());
    }
    public void deleteUser(String name){
        String readsql = "SELECT * FROM user WHERE name = ?";
        if (userRepository.isUserNotExist( name)){
            throw new IllegalArgumentException();
        }

        userRepository.DeleteUserName(name);
    }
}
