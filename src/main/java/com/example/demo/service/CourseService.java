package com.example.demo.service;


import com.example.demo.modal.Course;
import com.example.demo.modal.Instructor;
import com.example.demo.modal.dto.CourseDto;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Component
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourses(){

        return courseRepository.findAllClasses();
    }

    public List<Course> searchByCourseName(String input){

        return courseRepository.findCourseByName(input);
    }

    private Course buildCourse(CourseDto course) {
        Course newCourse = Course.builder()
                .className(course.getClassName())
                .instructor(new Instructor(course.getInstructor().getFirstName(), course.getInstructor().getLastName(), course.getInstructor().getTitle(), course.getInstructor().getOffice()))
                .startDate(new Date(String.valueOf(course.getStartDate())))
                .endDate(new Date(String.valueOf(course.getEndDate())))
                .timeFrame(course.getTimeFrame())
                .build();
        return newCourse;
    }

    public void addCourse(CourseDto course) throws Exception{
        Course newCourse = buildCourse(course);
        courseRepository.addCourse(newCourse);
    }

    public String updateCourse(CourseDto course) {
        Course newCourse = buildCourse(course);
        if (courseRepository.updateCourse(newCourse)) {
            return "Update successfully";
        } else {
            return "Update failed";
        }

    }

    public String deleteCourse(String courseName) {
        boolean delete = courseRepository.deleteCourse(courseName);
        if (delete) {
            return "Delete successfully";
        } else {
            return "Delete failed";
        }
    }

}
