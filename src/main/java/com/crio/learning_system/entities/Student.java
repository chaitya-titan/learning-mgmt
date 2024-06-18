package com.crio.learning_system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"subjects", "exams"})
@Data
@Entity
@Table(name = "Student")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends BaseEntity{
    private String studentName;

    @ManyToMany
    private Set<Subject> subjects;

    @ManyToMany
    private Set<Exam> exams;
}
