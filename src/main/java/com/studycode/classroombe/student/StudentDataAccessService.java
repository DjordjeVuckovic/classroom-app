package com.studycode.classroombe.student;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class StudentDataAccessService {
    private final JdbcTemplate jdbcTemplate;
    public List<Student> selectAllStudents(){
        String sql = ""+
                "select student_id," +
                "first_name," +
                "last_name," +
                "email," +
                "gender " +
                "from student";
        return jdbcTemplate.query(sql, getStudentsFromDb());
    }

    private  RowMapper<Student> getStudentsFromDb() {
        return (rs, i) -> {
            String studentIdString = rs.getString("student_id");
            UUID studentId = UUID.fromString(studentIdString);
            String fistName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String gender = rs.getString("gender").toUpperCase();
            var genderConv = Gender.valueOf(gender);
            return Student.builder()
                    .studentId(studentId)
                    .firstName(fistName)
                    .lastName(lastName)
                    .email(email)
                    .gender(genderConv)
                    .build();
        };
    }
}
