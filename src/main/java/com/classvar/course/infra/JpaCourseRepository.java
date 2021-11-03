package com.classvar.course.infra;

import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Long>, CourseRepository {
}
