package com.ezloc.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ezloc.app.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByusername(String username);
	List<User> findByenterprise_id(Long id);

}
