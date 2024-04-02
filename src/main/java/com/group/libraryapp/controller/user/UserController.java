package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
     // 선언만 해줬다.  java database collector ;p
    private final UserServiceV2 userService ;
    public UserController(UserServiceV2 userService){
        this.userService = userService;
//        this.userService = new UserService(jdbcTemplate); // 생성자에서 새로운 객체를 만들었다. -> U
         // 생성자에서 새로운 객체를 만들었다. -> U
    }// spring이 알아서 jdbctemplate을 넣어준다네;
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    } // 구문 전체는 List가 되고 리스트의 각 인덱스에는 UserResponse(id,name,age)가 들어가게 된다.
    //그렇기에 UserResponse class는 생성자가 필요하고 만들어진 객체는 리스트에 들어가는 것

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }
}


