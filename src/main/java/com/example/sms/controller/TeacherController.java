package com.example.sms.controller;

import com.example.sms.entity.Teacher;
import com.example.sms.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@CrossOrigin
public class TeacherController {
    private final TeacherService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Teacher> create(@Valid @RequestBody Teacher t) {
        return ResponseEntity.ok(service.create(t));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping
    public ResponseEntity<List<Teacher>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @Valid @RequestBody Teacher t) {
        return ResponseEntity.ok(service.update(id, t));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
