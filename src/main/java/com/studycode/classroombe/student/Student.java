package com.studycode.classroombe.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
@AllArgsConstructor
@Getter
@Builder
public class Student {
    private final UUID id;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final String email;
    @NotNull
    private final Gender gender;
}
