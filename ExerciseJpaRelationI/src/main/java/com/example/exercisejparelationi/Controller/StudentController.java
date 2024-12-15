package com.example.exercisejparelationi.Controller;

import com.example.exercisejparelationi.Model.Student;
import com.example.exercisejparelationi.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Student student) {
        studentService.add(student);
        return ResponseEntity.ok().body("student added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id ,@RequestBody Student student) {
        studentService.update(id, student);
        return ResponseEntity.ok().body("student updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        studentService.delete(id);
        return ResponseEntity.ok().body("student deleted successfully");
    }

    @PostMapping("/assign/{studentId}/{courseId}")
    public ResponseEntity assign(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.ok().body("student assigned successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllStudents(){
        List list = studentService.getAllStudents();
        return ResponseEntity.ok().body(list);
    }


    @PutMapping("/ChangeMajor/{studentId}/{major}/{newMajor}")
    public ResponseEntity ChangeMajor(@PathVariable Integer studentId ,@PathVariable String major ,@PathVariable String newMajor) {
        studentService.ChangeMajor(studentId, major, newMajor);
        return ResponseEntity.ok().body("student changed successfully");
    }

    @GetMapping("/getStudentByCoursId/{courseId}")
    public ResponseEntity getStudentByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.ok().body(studentService.getStudentByCoursId(courseId));
    }
}
