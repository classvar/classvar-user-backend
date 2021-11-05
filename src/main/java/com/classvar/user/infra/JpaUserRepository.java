package com.classvar.user.infra;

import com.classvar.user.domain.User;
import com.classvar.user.domain.UserRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
  @Query("SELECT u FROM User u WHERE u.email IN :emails")
  List<User> findAllByEmails(List<String> emails);
}
