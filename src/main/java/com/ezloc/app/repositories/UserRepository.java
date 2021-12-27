package com.ezloc.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ezloc.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByusername(String username);

}
