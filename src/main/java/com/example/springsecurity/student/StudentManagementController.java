package com.example.springsecurity.student;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("management/api/v1/students")
@Log4j2
public class StudentManagementController {
    private List<Student> STUDENTS = Arrays.asList(
            new Student(1, "kim"),
            new Student(2, "seongil"),
            new Student(3, "shiroed1211"),
            new Student(4, "test")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        log.info("Get all students");
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        log.info("register new student : {}",student);
        STUDENTS.add(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer id) {
        log.info("delete student : {}",id);
        STUDENTS = STUDENTS.stream()
                .filter(student -> !student.getStudentId().equals(id))
                .collect(Collectors.toList());
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer id, @RequestBody Student student) {
        log.info("update student : {}",id);
        int cnt = 0;
        for (Student st : STUDENTS) {
            if (st.getStudentId().equals(id)) {
                break;
            }
            cnt += 1;
        }
        STUDENTS.set(cnt, student);
    }
}
