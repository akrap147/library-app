package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {
    private String name; // null 불가능
    private Integer age; //int -> null 을 표현할 수 없다.

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
