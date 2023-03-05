create table if not exists course(
    course_id uuid not null primary key,
    name varchar(255) not null unique,
    description text not null,
    department varchar(255),
    teacher_name varchar(100)
);

create table if not exists student_course(
  student_id  uuid not null references student(student_id),
  course_id   uuid not null references  course(course_id),
  start_data date not null,
  end_date date not null,
  grade integer check (grade >=0 and grade <= 100),
  unique (student_id, course_id)
);
