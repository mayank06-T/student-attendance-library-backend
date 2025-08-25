package com.example.sms.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LibraryDtos {
    public record IssueRequest(
            @NotNull Long bookId,
            @NotNull Long studentId,
            @NotNull LocalDate dueDate
    ){}

    public record ReturnRequest(
            @NotNull Long issueId,
            LocalDate returnDate
    ){}
}
