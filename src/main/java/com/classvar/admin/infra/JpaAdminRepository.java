package com.classvar.admin.infra;

import com.classvar.admin.domain.Admin;
import com.classvar.admin.domain.AdminRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAdminRepository extends JpaRepository<Admin, Long>, AdminRepository {
  @Query("SELECT a FROM Admin a WHERE a.email IN :emails")
  List<Admin> findAllByEmails(List<String> emails);
}
