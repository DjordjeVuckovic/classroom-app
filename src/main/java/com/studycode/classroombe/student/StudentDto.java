package com.studycode.classroombe.student;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class StudentDto {
    private  UUID studentId;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  Gender gender;
}
