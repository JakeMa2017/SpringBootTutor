package com.example.demo.repository;

import com.example.demo.modal.Course;
import com.example.demo.modal.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CourseRepository {
    List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        Course javaOne = Course.builder()
                .className("Java I")
                .instructor(new Instructor("Steve", "Jobs", "Phd", "TownHall201"))
                .startDate(new Date("8/1/2018"))
                .endDate(new Date("12/24/2018"))
                .timeFrame("8am-10am")
                .build();

        courses.add(javaOne);
    }


    public List<Course> findAllClasses(){
        //链接数据库
        //返回数据库的信息
        return  courses;
    }

    public List<Course> findAllCourse(String searchByCourseName){

        return new ArrayList<Course>();
    }

    public List<Course> findCourseByName(String courseName) {
        if(courseName.equals("Java_I")) {
            return courses;
        }

        return new ArrayList<Course>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean deleteCourse(String courseName) {
        for (int i = 0; i < courses.size(); i++) {
            if (courseName.equals(courses.get(i).getClassName())) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateCourse(Course course) {
        boolean update = deleteCourse(course.getClassName());
        if(update) {
            courses.add(course);
            return true;
        } else {
            return false;
        }
    }

}
