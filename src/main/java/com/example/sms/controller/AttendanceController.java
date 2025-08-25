package com.example.sms.controller;

import com.example.sms.dto.AttendanceDtos.*;
import com.example.sms.entity.Attendance;
import com.example.sms.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@CrossOrigin
public class AttendanceController {
    private final AttendanceService service;

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping("/mark")
    public ResponseEntity<?> mark(@Valid @RequestBody MarkRequest req) {
        service.mark(req);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> byStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.byStudent(studentId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/class/{className}")
    public ResponseEntity<List<Attendance>> byClassDate(@PathVariable String className,
                                                        @RequestParam String date) {
        return ResponseEntity.ok(service.byClassAndDate(className, LocalDate.parse(date)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping("/summary/{studentId}")
    public ResponseEntity<SummaryResponse> summary(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.summary(studentId));
    }
}
