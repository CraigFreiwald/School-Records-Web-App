package com.example.firstspringboottest.controller;

import com.example.firstspringboottest.model.Course;
import com.example.firstspringboottest.model.Student;
import com.example.firstspringboottest.repository.CourseRepository;
import com.example.firstspringboottest.repository.StudentRepository;
import com.example.firstspringboottest.service.CourseService;
import com.example.firstspringboottest.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/studentList")
    public String viewStudentPage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());

        return "student_list";
    }

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        //create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        //save student to database
        studentService.saveStudent(student);
        return "redirect:/studentList";
    }

    @RequestMapping(value = "/get/{sid}")
    public String getStudentId(@PathVariable("sid") Long sid, Model model){
        Optional<Student> findStudentId = studentRepository.findById(sid);

        model.addAttribute("title", "Data Student");
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("student", findStudentId);

        log.info("getStudentId() : "+sid);
        return "redirect:/studentList";
    }

    @GetMapping("/showStudFormForUpdate/{sid}")
    public String showStudFormForUpdate(@PathVariable(value = "sid") long sid, Model model) {
        // get student from the service
        Student student = studentService.getStudentById(sid);
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        //set student as a model attribute to pre-populate the form
        model.addAttribute("student", student);

        return "update_student";
    }

    @GetMapping("/deleteStudent/{sid}")
    public String deleteStudent(@PathVariable (value = "sid") long sid) {
        // call delete student method
        this.studentService.deleteStudentById(sid);
        return "redirect:/studentList";
    }

}
