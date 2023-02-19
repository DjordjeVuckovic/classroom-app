package com.studycode.classroombe.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StudentService {
    private final StudentDataAccessService studentDataAccessService;
    public List<Student> getAllStudents(){
        return studentDataAccessService.selectAllStudents();
    }
}
