INSERT INTO course(course_id, name, description, department, teacher_name)
VALUES ('e32dc376-723d-47b8-aa5d-364fd76b5578','Math','lorem ipsum','Science','John Doe');
INSERT INTO student_course (
    student_id,
    course_id,
    start_date,
    end_date,
    grade
)
VALUES (
           'e32dc376-723d-47b8-aa5d-364fd76b5577',
           'e32dc376-723d-47b8-aa5d-364fd76b5578',
           (NOW() - INTERVAL '1 YEAR')::DATE,
           NOW()::DATE,
           90
       );