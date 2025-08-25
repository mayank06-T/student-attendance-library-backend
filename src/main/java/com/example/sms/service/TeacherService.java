package com.example.sms.service;

import com.example.sms.entity.Teacher;
import com.example.sms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository repo;
    public Teacher create(Teacher t) { return repo.save(t); }
    public List<Teacher> list() { return repo.findAll(); }
    public Teacher get(Long id) { return repo.findById(id).orElseThrow(); }
    public Teacher update(Long id, Teacher t) {
        Teacher e = get(id);
        e.setName(t.getName()); e.setEmployeeId(t.getEmployeeId()); e.setSubject(t.getSubject());
        return repo.save(e);
    }
    public void delete(Long id) { repo.deleteById(id); }
}
