package com.lap.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lap.crud.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
