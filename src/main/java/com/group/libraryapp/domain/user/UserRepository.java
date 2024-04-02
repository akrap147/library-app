package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//JpaRepository를 상속 받는 것만으로도 충분하다
    Optional<User> findByName(String name); // 아니 인터페이스에 설정해도 구현체에 설정안하면 무슨의미냐?
//find -> 한개만 가져온다, By뒤에 붙는 필드 이름으 SELECT 쿼리의 WHERE 문이 작성된다.
    boolean existsByName(String name);
}
