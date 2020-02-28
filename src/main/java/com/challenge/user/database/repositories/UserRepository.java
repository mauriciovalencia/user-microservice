package com.challenge.user.database.repositories;

import com.challenge.user.database.entitites.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query("SELECT CASE WHEN u.email IS NULL THEN 'false' ELSE 'true' END " +
            "AS isEmailExist FROM UserEntity u WHERE u.email = :email")
    String isEmailExist(@Param("email") String email);
}
