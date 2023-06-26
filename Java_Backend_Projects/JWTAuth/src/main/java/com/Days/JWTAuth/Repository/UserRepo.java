package com.Days.JWTAuth.Repository;

import com.Days.JWTAuth.Bussiness.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,String> {
}
