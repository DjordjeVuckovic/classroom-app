package com.studycode.classroombe.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    @NotBlank
    private  String firstName;
    @NotBlank
    private  String lastName;
    @NotBlank
    private  String email;
    @NotNull
    private  String gender;
    public boolean isValidGender(){
        return gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE");
    }
}
