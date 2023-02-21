package com.studycode.classroombe.student;

import com.studycode.classroombe.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PostMapping
    public ResponseEntity<?> addNewStudent(@RequestBody @Valid CreateStudentDto studentDto, UriComponentsBuilder uriBuilder){
        if(!studentDto.isValidGender()){
            throw new ApiRequestException("Not valid gender!");
        }
        var student = Student.builder()
                .email(studentDto.getEmail())
                .lastName(studentDto.getLastName())
                .firstName(studentDto.getLastName())
                .gender(Gender.valueOf(studentDto.getGender().toUpperCase()))
                .studentId(UUID.randomUUID())
                .build();
        studentService.addNewStudent(student);
        URI location = uriBuilder.path("/users/{id}").buildAndExpand(student.getStudentId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
