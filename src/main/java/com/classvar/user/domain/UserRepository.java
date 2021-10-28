package com.classvar.user.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    void delete(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
}
