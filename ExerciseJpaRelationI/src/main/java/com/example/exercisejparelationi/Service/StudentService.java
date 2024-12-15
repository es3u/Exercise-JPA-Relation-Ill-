package com.example.exercisejparelationi.Service;

import com.example.exercisejparelationi.ApiException.ApiException;
import com.example.exercisejparelationi.DTOout.CourseDTO;
import com.example.exercisejparelationi.DTOout.StudentDTO;
import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Model.Student;
import com.example.exercisejparelationi.Repository.CourseRepository;
import com.example.exercisejparelationi.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;




    public List getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {
            StudentDTO s = new StudentDTO(student.getName() , student.getAge() , student.getMajor());
            studentDTOList.add(s);
        }


        return studentDTOList;
    }


    public void add(Student student) {
        studentRepository.save(student);
    }


    public void update(Integer id ,Student student) {
        Student student1 = studentRepository.findStudentById(id);

        if(student1 == null) {
            throw new ApiException("student not found");
        }

        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setMajor(student.getMajor());
        student1.setMajor(student.getMajor());
        student1.setCourse(student.getCourse());
        studentRepository.save(student1);
    }


    public void delete(Integer id) {
        Student student1 = studentRepository.findStudentById(id);
        if(student1 == null) {
            throw new ApiException("student not found");
        }
        studentRepository.delete(student1);
    }


    public void assignStudentToCourse(Integer StudentId , Integer courseId) {
        Student student1 = studentRepository.findStudentById(StudentId);
        Course course = courseRepository.findCourseById(courseId);

        if(student1 == null || course == null) {
            throw new ApiException("student not found");
        }

        student1.getCourse().add(course);
        course.getStudents().add(student1);
        studentRepository.save(student1);
    }


    public void ChangeMajor(Integer studentId , String major , String newMajor) {
        Student student1 = studentRepository.findStudentByIdAndMajor(studentId ,major);

        if (student1 == null) {
            throw new ApiException("student not found");
        }
        student1.setMajor(newMajor);
        student1.setCourse(null);
        studentRepository.save(student1);
    }


    public List getStudentByCoursId(Integer courseId) {
        List<Student> students = studentRepository.findStudentByCourseId(courseId);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO(student.getName(), student.getAge(), student.getMajor());
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }








}
