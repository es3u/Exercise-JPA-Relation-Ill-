package com.example.exercisejparelationi.Service;

import com.example.exercisejparelationi.ApiException.ApiException;
import com.example.exercisejparelationi.DTO.AddressDTO;
import com.example.exercisejparelationi.DTOout.*;
import com.example.exercisejparelationi.Model.Address;
import com.example.exercisejparelationi.Model.Course;
import com.example.exercisejparelationi.Model.Student;
import com.example.exercisejparelationi.Model.Teacher;
import com.example.exercisejparelationi.Repository.AddressRepository;
import com.example.exercisejparelationi.Repository.CourseRepository;
import com.example.exercisejparelationi.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;

        public List<TeacherDTO> getAllTeachers() {
            List<Teacher> teachers = teacherRepository.findAll();
            List<TeacherDTO> teacherDTOS = new ArrayList<>();

            for (Teacher teacher : teachers) {
                List<AddressDTOout> addressDTOS = new ArrayList<>();
                for (Address address : addressRepository.findAll()) {
                    AddressDTOout addressDTOout = new AddressDTOout(address.getArea() , address.getStreet() , address.getBuildingNumber());
                    addressDTOS.add(addressDTOout);
                }
                List<CourseDTO> courseDTOS = new ArrayList<>();
                for (Course course : courseRepository.findAll()) {
                    List<StudentDTO> studentDTOS = new ArrayList<>();

                    for (Student student : course.getStudents()) {
                        StudentDTO studentDTO = new StudentDTO(student.getName() , student.getAge() , student.getMajor());
                        studentDTOS.add(studentDTO);
                    }


                    CourseDTO courseDTO = new CourseDTO(course.getName() , studentDTOS);
                    courseDTOS.add(courseDTO);
                }
                TeacherDTO teacherDTO = new TeacherDTO(teacher.getName() , teacher.getAge(), teacher.getEmail() ,addressDTOS,courseDTOS);
                teacherDTOS.add(teacherDTO);

            }
            return teacherDTOS;
        }






        public void addTeacher(Teacher teacher) {
            teacherRepository.save(teacher);
        }
        public void updateTeacher(Integer id ,Teacher teacher) {
            Teacher teacher1 = teacherRepository.findTeacherById(id);
            if(teacher1 == null) {
                throw new ApiException("teacher not found");
            }
            teacher1.setName(teacher.getName());
            teacher1.setEmail(teacher.getEmail());
            teacher1.setAge(teacher.getAge());
            teacher1.setSalary(teacher.getSalary());
            teacherRepository.save(teacher1);
        }

        public void deleteTeacher(Integer id) {
            Teacher teacher1 = teacherRepository.findTeacherById(id);
            Address address = addressRepository.findAddressById(id) ;
            Course course = courseRepository.findCourseById(id) ;
//            if(address == null) {
//                throw new ApiException("address not found");
//            }
            if(teacher1 == null) {
                throw new ApiException("teacher not found");

            }
            teacher1.setAddress(null);
            teacher1.setCourses(null);
            teacherRepository.delete(teacher1);
            addressRepository.delete(address);
            courseRepository.delete(course);

        }

        public Teacher getTeacherById(Integer id) {
            Teacher teacher =  teacherRepository.findTeacherById(id);

            if (teacher == null) {
                throw new ApiException("teacher not found");
            }
            return teacher;
        }

        public TeacherDTO2 getTeacherByCoursId(Integer coursId) {
            Teacher teacher = teacherRepository.findTeacherByCoursesId(coursId);
            if (teacher == null) {
                throw new ApiException("teacher not found");
            }
            TeacherDTO2 teacherDTO2 = new TeacherDTO2(teacher.getName());
            return teacherDTO2;
        }
}
