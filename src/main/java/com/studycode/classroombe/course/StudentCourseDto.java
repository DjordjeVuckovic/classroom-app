package com.studycode.classroombe.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class StudentCourseDto {
    private final UUID studentId;
    private final UUID courseId;
    private final String name;
    private final String description;
    private final String department;
    private final String teacherName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer grade;
}
