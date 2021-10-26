package com.classvar.course.service;

import com.classvar.course.application.CourseQueryProcessor;
import com.classvar.course.application.dto.response.GetCourseDto;
import com.classvar.course.application.dto.response.GetExamDetailDto;
import com.classvar.course.application.dto.response.GetExamInfoDto;
import com.classvar.course.domain.Course;
import com.classvar.course.domain.CourseRepository;
import com.classvar.course.domain.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CourseQueryProcessorTest {

    //dummy date
    final Course testCourse1 = new Course("코스1");
    final Exam testExam1 = new Exam("시험1",
            LocalDate.of(2020,1,1),
            LocalTime.of(12,0,0),
            LocalTime.of(13,0,0));
    final Exam testExam2 = new Exam("시험2",
            LocalDate.of(2020,2,2),
            LocalTime.of(14,0,0),
            LocalTime.of(15,0,0));

    final Course testCourse2 = new Course("코스2");
    final Exam testExam3 = new Exam("시험3",
            LocalDate.of(2021,1,1),
            LocalTime.of(16,0,0),
            LocalTime.of(17,0,0));
    final Exam testExam4 = new Exam("시험4",
            LocalDate.of(2021,2,2),
            LocalTime.of(18,0,0),
            LocalTime.of(19,0,0));


    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseQueryProcessor courseQueryProcessor;

    @BeforeEach
    public void setup(){
        testCourse1.addExam(testExam1);
        testCourse1.addExam(testExam2);

        testCourse2.addExam(testExam3);
        testCourse2.addExam(testExam4);

        courseRepository.save(testCourse1);

        courseRepository.save(testCourse2);
    }

    @Test
    public void getCourseListTest(){
        List<GetCourseDto> result = courseQueryProcessor.getCourseList();

        assertThat(result.get(0).getName()).isEqualTo("코스1");
        assertThat(result.get(1).getName()).isEqualTo("코스2");
    }

    @Test
    public void getExamDetailWithIdTest(){
        GetExamDetailDto result = courseQueryProcessor.getExamDetailWithId(1,1);

        assertThat(result.getName()).isNotEmpty();
        assertThat(result.getExamDate()).isNotNull();
        assertThat(result.getStartTime()).isNotNull();
        assertThat(result.getEndTime()).isNotNull();
    }

    @Test
    public void getExamListTest(){
        List<GetExamInfoDto> result = courseQueryProcessor.getExamList(1);

        assertThat(result.size()).isEqualTo(2);
    }
}
