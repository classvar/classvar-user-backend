//package com.classvar.course.service;
//
//import com.classvar.course.application.CourseCommandExecutor;
//import com.classvar.course.application.dto.request.CreateOrUpdateCourseDto;
//import com.classvar.course.application.dto.request.CreateOrUpdateExamDto;
//import com.classvar.course.domain.Course;
//import com.classvar.course.domain.CourseRepository;
//import com.classvar.course.domain.Exam;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//public class CourseCommandExecutorTest {
//
//    @Autowired
//    CourseCommandExecutor courseCommandExecutor;
//
//    @Autowired
//    CourseRepository courseRepository;
//
//    final CreateOrUpdateCourseDto createOrUpdateCourseDto = new CreateOrUpdateCourseDto("테스트 코스");
//    final CreateOrUpdateExamDto createOrUpdateExamDto =
//            new CreateOrUpdateExamDto(
//                    "테스트 시험",
//                    LocalDate.of(2020,10,26),
//                    LocalTime.of(15,0,0),
//                    LocalTime.of(17,0,0));
//
//    static final LocalDate examDate = LocalDate.of(2021,10,26);
//    static final LocalTime startTime = LocalTime.of(12, 0, 0);
//    static final LocalTime endTime = LocalTime.of(15,0,0);
//
//    long courseId, examId;
//
//    @Transactional
//    @BeforeEach
//    public void setup() {
//        Course course = new Course("코스");
//
//        Exam exam = new Exam("시험", examDate, startTime,endTime);
//        exam.setCourse(course);
//        course.getExams().add(exam);
//
//        this.courseId = courseRepository.save(course).getId();
//
//        Course toTest = courseRepository.findCourseWithExamsById(this.courseId).get();
//
//        this.examId = toTest.getExams().stream().findFirst().get().getId();
//    }
//
//    @Transactional
//    @Test
//    public void createCourseTest(){
//        long testCourseId = courseCommandExecutor.createCourse(createOrUpdateCourseDto);
//
//        Course result = courseRepository.findCourseById(testCourseId).get();
//
//        assertThat(result.getId()).isNotEqualTo(this.courseId);
//        assertThat(result.getName()).isEqualTo("테스트 코스");
//    }
//
//    @Transactional
//    @Test
//    public void updateCourseTest(){
//        courseCommandExecutor.updateCourse(this.courseId,createOrUpdateCourseDto);
//
//        Course result = courseRepository.findCourseById(this.courseId).get();
//
//        assertThat(result.getName()).isEqualTo("테스트 코스");
//    }
//
//    @Transactional
//    @Test
//    public void deleteCourseTest(){
//        courseCommandExecutor.deleteCourse(this.courseId);
//
//        assertThat(courseRepository.findCourseById(this.courseId).isPresent()).isFalse();
//    }
//
//    @Transactional
//    @Test
//    public void createExamTest(){
//        courseCommandExecutor.createExamToCourse(this.courseId,createOrUpdateExamDto);
//
//        assertThat(courseRepository.findAllExamWithCourseId(this.courseId).size()).isEqualTo(2);
//    }
//
//    @Transactional
//    @Test
//    public void updateExamTest(){
//        courseCommandExecutor.updateExamToCourse(this.courseId,this.examId, createOrUpdateExamDto);
//
//        Exam result = courseRepository.findAllExamWithCourseId(this.courseId).stream().findFirst().get();
//
//        assertThat(result.getName()).isEqualTo("테스트 시험");
//        assertThat(result.getExamDate()).isAfterOrEqualTo(LocalDate.of(2020,10,26));
//        assertThat(result.getStartTime()).isAfterOrEqualTo(LocalTime.of(15,0,0));
//        assertThat(result.getEndTime()).isAfterOrEqualTo(LocalTime.of(17,0,0));
//    }
//
//    @Transactional
//    @Test
//    public void deleteExamTest(){
//        courseCommandExecutor.deleteExamToCourse(this.courseId,this.examId);
//
//        assertThat(courseRepository.findAllExamWithCourseId(this.courseId).size()).isEqualTo(0);
//    }
//}
