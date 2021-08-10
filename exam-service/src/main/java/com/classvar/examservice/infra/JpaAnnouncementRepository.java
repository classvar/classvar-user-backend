package com.classvar.examservice.infra;

import com.classvar.examservice.domain.Announcement;
import com.classvar.examservice.domain.AnnouncementRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAnnouncementRepository
    extends JpaRepository<Announcement, Integer>, AnnouncementRepository {}
