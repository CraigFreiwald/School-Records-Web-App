package com.example.firstspringboottest.service;

import com.example.firstspringboottest.model.Course;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    void saveCourse(Course course);
    Course getCourseById(long id);
    void deleteCourseById(long id);
    Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sort);
}
