package com.studycode.classroombe.student;

import com.studycode.classroombe.course.StudentCourseDto;
import com.studycode.classroombe.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
                .build();
        studentService.addNewStudent(student);
        URI location = uriBuilder.path("/students/{id}").buildAndExpand(student.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping(path="{studentId}/course")
    public List<StudentCourseDto> getAllStudentCourses(@PathVariable("studentId") String studentId){
        return studentService.getAllStudentCourses(studentId);
    }

}
