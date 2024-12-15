package com.example.exercisejparelationi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotEmpty(message = "name can not be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name ;

    @NotNull(message = "age can not be null")
    @Column(columnDefinition = "int not null")
    @Positive
//    @Size(min = 18)
    private Integer age;

    @NotEmpty(message = "major can not be null")
    @Column(columnDefinition = "varchar(50) not null")
    private String major ;

    @ManyToMany
    @JsonIgnore
    private Set<Course> course;




}
