package edu.skillbox.rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.skillbox.rest_api.entity.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    @Modifying
    @Query("UPDATE User u SET u.isDeleted=true WHERE u=:user")
    public void delete(@Param("user") User user);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted=true WHERE u.id=:id")
    public void deleteById(@Param("id") UUID id);
}
