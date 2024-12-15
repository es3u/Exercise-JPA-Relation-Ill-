package com.example.exercisejparelationi.Service;

import com.example.exercisejparelationi.ApiException.ApiException;
import com.example.exercisejparelationi.DTOout.CourseDTO;
import com.example.exercisejparelationi.DTOout.StudentDTO;
import com.example.exercisejparelationi.DTOout.TeacherDTO;
import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Model.Student;
import com.example.exercisejparelationi.Model.Teacher;
import com.example.exercisejparelationi.Repository.CourseRepository;
import com.example.exercisejparelationi.Repository.StudentRepository;
import com.example.exercisejparelationi.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;



    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();

        for (Course course : courses) {
            List<StudentDTO> studentDTOS = new ArrayList<>();

            for (Student student : course.getStudents()) {
                StudentDTO studentDTO = new StudentDTO(student.getName() , student.getAge() , student.getMajor());
                studentDTOS.add(studentDTO);
            }


            CourseDTO courseDTO = new CourseDTO(course.getName() , studentDTOS);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    public void addCourse(Integer teacher_id ,Course course) {
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if(teacher == null) {
            throw new ApiException("teacher not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
        teacherRepository.save(teacher);
    }

    public void updateCourse(Integer course_id ,Integer teacher_id ,Course course) {
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if(teacher == null) {
            throw new ApiException("teacher not found");
        }
        Course course1 = courseRepository.findCourseById(course_id);
        if(course1 == null) {
            throw new ApiException("course not found");
        }

        course1.setName(course.getName());
        courseRepository.save(course1);
    }

    public void deleteCourse(Integer course_id ,Integer teacher_id ) {
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if(teacher == null) {
            throw new ApiException("teacher not found");
        }
        Course course1 = courseRepository.findCourseById(course_id);
        if(course1 == null) {
            throw new ApiException("course not found");
        }

        courseRepository.delete(course1);
    }



}
