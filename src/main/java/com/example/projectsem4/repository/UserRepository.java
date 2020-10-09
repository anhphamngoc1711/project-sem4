package com.example.projectsem4.repository;

import com.example.projectsem4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

   @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.password = :password")
    public User getUserByPassword(@Param("password") String password);

    User findByUsernameEquals(String name);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    public User checkUserLogin(@Param("username") String username,@Param("password") String password);


}