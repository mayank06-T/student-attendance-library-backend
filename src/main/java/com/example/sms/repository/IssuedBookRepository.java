package com.example.sms.repository;

import com.example.sms.entity.IssuedBook;
import com.example.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {
    List<IssuedBook> findByStudentAndReturnDateIsNull(Student student);
    List<IssuedBook> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);
}
