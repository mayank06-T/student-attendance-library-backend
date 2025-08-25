package com.example.sms.dto;

import com.example.sms.model.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class AttendanceDtos {
    public record MarkItem(
            @NotNull Long studentId,
            @NotNull AttendanceStatus status
    ){}

    public record MarkRequest(
            @NotBlank String className,
            @NotNull LocalDate date,
            @NotNull List<MarkItem> entries
    ){}

    public record SummaryResponse(double percentage, int totalDays, int presentDays){}
}
