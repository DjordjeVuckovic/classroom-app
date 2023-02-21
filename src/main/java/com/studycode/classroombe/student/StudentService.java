package com.studycode.classroombe.student;

import com.studycode.classroombe.common.EmailValidator;
import com.studycode.classroombe.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class StudentService {
    private final StudentDataAccessService studentDataAccessService;
    private final EmailValidator emailValidator;
    List<Student> getAllStudents(){
        return studentDataAccessService.selectAllStudents();
    }

     void addNewStudent(Student student) {

        if(!emailValidator.test(student.getEmail())){
            throw new ApiRequestException(student.getEmail() + " is not valid");
        }
        studentDataAccessService.insertStudent(student);
    }
    private void isEmailAlreadyExists(String email){

    }
}
