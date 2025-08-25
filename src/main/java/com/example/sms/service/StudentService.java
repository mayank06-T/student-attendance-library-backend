package com.example.sms.service;

import com.example.sms.entity.Student;
import com.example.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student create(Student s) { return studentRepository.save(s); }
    public Page<Student> list(int page, int size) { return studentRepository.findAll(PageRequest.of(page, size)); }
    public Student get(Long id) { return studentRepository.findById(id).orElseThrow(); }
    public Student update(Long id, Student s) {
        Student existing = get(id);
        existing.setName(s.getName());
        existing.setRollNo(s.getRollNo());
        existing.setClassName(s.getClassName());
        existing.setSection(s.getSection());
        return studentRepository.save(existing);
    }
    public void delete(Long id) { studentRepository.deleteById(id); }
    public List<Student> byClassName(String className) { return studentRepository.findByClassName(className); }
}
