package com.example.sms.service;

import com.example.sms.dto.AttendanceDtos.*;
import com.example.sms.entity.Attendance;
import com.example.sms.entity.Student;
import com.example.sms.model.AttendanceStatus;
import com.example.sms.repository.AttendanceRepository;
import com.example.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public void mark(MarkRequest req) {
        for (MarkItem item : req.entries()) {
            Student student = studentRepository.findById(item.studentId()).orElseThrow();
            if (attendanceRepository.existsByStudentAndDate(student, req.date())) continue;
            Attendance att = Attendance.builder()
                    .student(student)
                    .date(req.date())
                    .status(item.status())
                    .className(req.className())
                    .build();
            attendanceRepository.save(att);
        }
    }

    public List<Attendance> byStudent(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow();
        return attendanceRepository.findByStudent(s);
    }

    public List<Attendance> byClassAndDate(String className, LocalDate date) {
        return attendanceRepository.findByClassNameAndDate(className, date);
    }

    public SummaryResponse summary(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow();
        YearMonth ym = YearMonth.now();
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();
        List<Attendance> list = attendanceRepository.findByStudentAndDateBetween(s, start, end);
        int total = list.size();
        int present = (int) list.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT).count();
        double pct = total == 0 ? 0 : (present * 100.0 / total);
        return new SummaryResponse(pct, total, present);
    }
}
