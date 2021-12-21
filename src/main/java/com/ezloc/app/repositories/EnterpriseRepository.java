package com.ezloc.app.repositories;

import com.ezloc.app.entities.Enterprise;
import com.ezloc.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
