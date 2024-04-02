package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;


    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(),request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(),user.getAge()))
                .collect(Collectors.toList());

    }
    @Transactional
    public void updateUser(UserUpdateRequest request){
        //SELECT * FROM USER ID = ?;
        //Optional<User> -> 로 반환이된다. Null을 감싸주는 것이지.
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalAccessError::new);

        user.updateName(request.getName());
    }
    @Transactional
    public void deleteUser(String name){
//        User user = userRepository.findByName(name); // 왜 나와?   -> 1번 방법
//        if (user ==null){
//            throw new IllegalArgumentException();
//        }
//        userRepository.delete(user); //주어진 데이터를
        User user = userRepository.findByName(name). // -> 2번 방법
                orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
//        if (!userRepository.existByName(name)){    // -> 3번 방법
//            throw new IllegalArgumentException();
//        }
//        User user = userRepository.findByName(name);
//        userRepository.delete(user);

    }


}
