package com.example.firstspringboottest.service;


import com.example.firstspringboottest.model.Student;
import com.example.firstspringboottest.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceImplTest {
    @Autowired
    private StudentRepository repository;
    @Test
    void getAllStudents() {
        List<Student> items = repository.findAll();
        assertEquals(1, items.size());
    }

    @Test
    public void testFindOne() {
        Student student = repository.findById(1L).get();

        assertEquals("Craig Freiwald", student.getStudName());
    }
}
