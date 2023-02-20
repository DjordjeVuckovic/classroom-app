package com.studycode.classroombe.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @PostMapping
    public void addNewStudent(@RequestBody CreateStudentDto student){
        System.out.println(student);
    }

}
