package com.studycode.classroombe.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class Course {
    private final UUID id;
    private final String name;
    private final String description;
    private final String department;
    private final String teacherName;
}
