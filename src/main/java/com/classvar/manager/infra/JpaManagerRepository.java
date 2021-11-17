package com.classvar.manager.infra;

import com.classvar.manager.domain.Manager;
import com.classvar.manager.domain.ManagerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaManagerRepository extends JpaRepository<Manager, Long>, ManagerRepository {
}
