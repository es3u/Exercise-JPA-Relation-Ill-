package com.example.exercisejparelationi.DTOout;

import com.example.exercisejparelationi.Model.Address;
import com.example.exercisejparelationi.Model.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class TeacherDTO {


    private String name;

    private Integer age;

    private String email;

    private List<AddressDTOout> addresses;

   private List<CourseDTO> courses;





}