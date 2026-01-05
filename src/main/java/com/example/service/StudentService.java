package com.example.service;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AuditService auditService;

    public StudentService(StudentRepository studentRepository, AuditService auditService) {
        this.studentRepository = studentRepository;
        this.auditService = auditService;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public Student save(Student student) {
        boolean isNew = student.getId() == null;

        Student saved = studentRepository.save(student);

        auditService.logChange(
                "Student",
                saved.getId(),
                isNew ? "INSERT" : "UPDATE",
                "Name=" + saved.getName()
                        + ", Group=" + saved.getGroupName()
                        + ", BirthDate=" + saved.getBirthDate()
        );

        return saved;
    }

    @Transactional
    public void deleteById(Integer id) {
        studentRepository.findById(id).ifPresent(student -> {
            studentRepository.delete(student);

            auditService.logChange(
                    "Student",
                    id,
                    "DELETE",
                    "Deleted student: Name=" + student.getName()
            );
        });
    }
}