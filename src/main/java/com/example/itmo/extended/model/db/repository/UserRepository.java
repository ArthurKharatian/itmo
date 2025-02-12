package com.example.itmo.extended.model.db.repository;

import com.example.itmo.extended.model.db.entity.Car;
import com.example.itmo.extended.model.db.entity.User;
import com.example.itmo.extended.model.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    default User getUser(Long id) {
//        return findById(id).orElse(new User());
//    }

    User getUserByAgeAndEmailAndStatus(Integer age, String email, UserStatus status);

    @Query("select  u from User u where u.lastName like %:filter% or u.firstName like %:filter%")
    Page<User> findAllFiltered(Pageable pageRequest, @Param("filter") String filter);
}
