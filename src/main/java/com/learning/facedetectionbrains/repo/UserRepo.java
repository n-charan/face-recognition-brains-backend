package com.learning.facedetectionbrains.repo;

import com.learning.facedetectionbrains.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> getUserByEmailAndPassword(String email, String password);

    @Query("SELECT usr FROM User usr WHERE usr.id=:userId")
    Optional<User> getByUserId(@Param("userId") Integer userId);
}
