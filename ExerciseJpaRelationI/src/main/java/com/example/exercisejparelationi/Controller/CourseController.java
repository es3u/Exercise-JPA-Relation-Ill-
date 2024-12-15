package com.example.exercisejparelationi.Controller;

import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/findAllCourses")
    public ResponseEntity getAllCourses() {
        List list = courseService.getAllCourses();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/addCourse/{teacher_id}")
    public ResponseEntity addCourse(@PathVariable Integer teacher_id , @RequestBody@Valid Course course) {
        courseService.addCourse(teacher_id , course);
        return ResponseEntity.ok().body("course added successfully");
    }
    @PutMapping("/updateCourse/{course_id}/{teacher_id}")
    public ResponseEntity updateCourse(@PathVariable Integer course_id ,@PathVariable Integer teacher_id ,@RequestBody@Valid Course course) {
        courseService.updateCourse(course_id , teacher_id , course);
        return ResponseEntity.ok().body("course updated successfully");
    }
    @DeleteMapping("/deleteCourse/{course_id}/{teacher_id}")
    public ResponseEntity deleteCourse(@PathVariable Integer course_id ,@PathVariable Integer teacher_id) {
        courseService.deleteCourse(course_id , teacher_id);
        return ResponseEntity.ok().body("course deleted successfully");
    }



}
