package com.studycode.classroombe.student;

import com.studycode.classroombe.course.StudentCourse;
import com.studycode.classroombe.course.StudentCourseDto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class StudentDataAccessService {
    private final JdbcTemplate jdbcTemplate;
     List<Student> selectAllStudents(){
        String sql = ""+
                "select student_id," +
                "first_name," +
                "last_name," +
                "email," +
                "gender " +
                "from student";
        return jdbcTemplate.query(sql, getStudentsFromDb());
    }
    boolean isEmailTaken(String email){
        String sql = ""+
                "select  exists(" +
                "select 1 " +
                "from student " +
                "where email = ?"+
                ")";
        Boolean emailTaken = jdbcTemplate.queryForObject(sql, new Object[]{email}, Boolean.class);
        return emailTaken != null ? emailTaken : false;
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
                    .id(studentId)
                    .firstName(fistName)
                    .lastName(lastName)
                    .email(email)
                    .gender(genderConv)
                    .build();
        };
    }

     int insertStudent(Student student) {
        String sql = "" +
                "insert into student(" +
                "student_id," +
                "first_name," +
                "last_name," +
                "email," +
                "gender)" +
                " values (uuid_generate_v4(),?,?,?,?::gender)";
        return jdbcTemplate.update(
                sql,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name()
                );
    }
    private RowMapper<StudentCourseDto> mapStudentCourseFromDb() {
        return (resultSet, i) ->
                new StudentCourseDto(
                        UUID.fromString(resultSet.getString("student_id")),
                        UUID.fromString(resultSet.getString("course_id")),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"),
                        resultSet.getString("teacher_name"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("end_date").toLocalDate(),
                        Optional.ofNullable(resultSet.getString("grade"))
                                .map(Integer::parseInt)
                                .orElse(null)
                );
    }

    List<StudentCourseDto> selectAllStudentCourses(UUID studentId) {
        String sql = "" +
                "SELECT " +
                " student.student_id, " +
                " course.course_id, " +
                " course.name, " +
                " course.description," +
                " course.department," +
                " course.teacher_name," +
                " student_course.start_date, " +
                " student_course.end_date, " +
                " student_course.grade " +
                "FROM student " +
                "JOIN student_course USING (student_id) " +
                "JOIN course         USING (course_id) " +
                "WHERE student.student_id = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{studentId},
                mapStudentCourseFromDb()
        );
    }
}
