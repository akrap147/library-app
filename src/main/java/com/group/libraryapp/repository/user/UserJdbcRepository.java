package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveUser(String name, long age){
        String sql = "INSERT INTO user (name, age) VALUES (?,?)";
        jdbcTemplate.update(sql, name, age);
    }
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id,name,age);
        });
    }
    public boolean isUserNotExist( long id) {
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    } // Repository 까지 내려오게 된다면 request에 있는 모든 필드가 쓰이는 것보다는 하나만 받는게 이득이다.
    public void updateUserName( String name ,long id){
        String sql = "UPDATE user SET name = ? WHERE id = ? ";
        jdbcTemplate.update(sql,name, id );
    }
    public boolean isUserNotExist(String name){
        String readsql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0, name ).isEmpty();
    }
    public void DeleteUserName(String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql,name);
    }

}