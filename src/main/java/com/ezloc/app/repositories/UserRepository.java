package com.ezloc.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ezloc.app.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByusername(String username);
	List<User> findByEnterprise_id(Long id);
	List<User> findByEnterprise_idAndRole_id(Long enterprise_id,Long role_id);

}
