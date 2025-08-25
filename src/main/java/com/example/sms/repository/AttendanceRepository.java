package com.example.sms.repository;

import com.example.sms.entity.Attendance;
import com.example.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudent(Student student);
    List<Attendance> findByStudentAndDateBetween(Student student, LocalDate start, LocalDate end);
    List<Attendance> findByClassNameAndDate(String className, LocalDate date);
    boolean existsByStudentAndDate(Student student, LocalDate date);
}
