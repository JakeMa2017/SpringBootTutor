package com.example.demo.controller;

import com.example.demo.modal.Course;
import com.example.demo.modal.dto.CourseDto;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

// single function interface 看关于这个的documentation来学习如何写@
@RestController
@RequestMapping
public class CourseController {
    @Autowired // IOC 控制反转
    CourseService courseService; // Singleton
    //依赖注入 Dependency Injection

    @GetMapping(path = "/", produces = "application/json") // 如果你用get的方法来call我这个地址
    public HttpEntity findAllCourses(){
        List<Course> allCourses = courseService.findAllCourses(); //我就帮你用这个方法来处理这个请求

        return new ResponseEntity<>(allCourses,HttpStatus.OK); //我返回结果给你
    }


    @GetMapping(path = "/look-up/{inputString}", produces = "application/json")
    public HttpEntity<Course> searchCourse(@PathVariable("inputString") String inputString) {

        List<Course> findedCourse = courseService.searchByCourseName(inputString);

        return new ResponseEntity(findedCourse, HttpStatus.OK);
    }

    @PostMapping(path = "/add/addCourse", produces = "text/plain")
    public ResponseEntity<String> addCourse(@RequestBody @NotNull CourseDto course) {
        try {
            courseService.addCourse(course);
            return new ResponseEntity<>("Added '" + course.getClassName() + "'", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(path = "/update/updateCourse", produces = "text/plain")
    public HttpEntity<Course> updateCourse(@RequestBody @NotNull CourseDto course) {
        try {
            String s = courseService.updateCourse(course);
            return new ResponseEntity(s, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(path = "/delete/deleteCourse/{inputString}", produces = "text/plain")
    public ResponseEntity<String> deleteCourse(@PathVariable("inputString") String inputString) {
        try {
            String s = courseService.deleteCourse(inputString);
            return new ResponseEntity<>(s, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
