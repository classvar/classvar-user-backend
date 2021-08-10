package com.classvar.examservice.domain;

import java.util.Optional;

public interface AnnouncementRepository {
  Optional<Announcement> findById(Integer id);
}
