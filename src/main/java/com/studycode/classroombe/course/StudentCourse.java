package com.studycode.classroombe.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class StudentCourse {
    private final UUID studentId;
    private final UUID courseId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer grade;

}
