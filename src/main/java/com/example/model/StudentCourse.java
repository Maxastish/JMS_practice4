package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_courses")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private Integer mark; // может быть null

    public StudentCourse() {}

    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.id = new StudentCourseId(student.getId(), course.getId());
    }

    // геттеры/сеттеры
    public StudentCourseId getId() { return id; }
    public void setId(StudentCourseId id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) {
        this.student = student;
        if (student != null) this.id.setStudentId(student.getId());
    }

    public Course getCourse() { return course; }
    public void setCourse(Course course) {
        this.course = course;
        if (course != null) this.id.setCourseId(course.getId());
    }

    public Integer getMark() { return mark; }
    public void setMark(Integer mark) { this.mark = mark; }
}