package com.example.sms.controller;

import com.example.sms.dto.LibraryDtos.*;
import com.example.sms.entity.IssuedBook;
import com.example.sms.service.LibraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@CrossOrigin
public class LibraryController {
    private final LibraryService service;

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping("/issue")
    public ResponseEntity<IssuedBook> issue(@Valid @RequestBody IssueRequest req) {
        return ResponseEntity.ok(service.issue(req));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping("/return")
    public ResponseEntity<IssuedBook> returnBook(@Valid @RequestBody ReturnRequest req) {
        return ResponseEntity.ok(service.returnBook(req));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<IssuedBook>> studentIssued(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.studentIssued(studentId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping("/overdue")
    public ResponseEntity<List<IssuedBook>> overdue() {
        return ResponseEntity.ok(service.overdue());
    }
}
