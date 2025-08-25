package com.example.sms.service;

import com.example.sms.dto.LibraryDtos.*;
import com.example.sms.entity.Book;
import com.example.sms.entity.IssuedBook;
import com.example.sms.entity.Student;
import com.example.sms.repository.BookRepository;
import com.example.sms.repository.IssuedBookRepository;
import com.example.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final IssuedBookRepository issuedBookRepository;

    public IssuedBook issue(IssueRequest req) {
        Book book = bookRepository.findById(req.bookId()).orElseThrow();
        if (book.getQuantity() <= 0) throw new IllegalArgumentException("No copies available");
        Student student = studentRepository.findById(req.studentId()).orElseThrow();
        IssuedBook ib = IssuedBook.builder()
                .book(book).student(student)
                .issueDate(LocalDate.now())
                .dueDate(req.dueDate())
                .build();
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        return issuedBookRepository.save(ib);
    }

    public IssuedBook returnBook(ReturnRequest req) {
        IssuedBook ib = issuedBookRepository.findById(req.issueId()).orElseThrow();
        if (ib.getReturnDate() != null) return ib;
        LocalDate ret = req.returnDate() == null ? LocalDate.now() : req.returnDate();
        ib.setReturnDate(ret);
        long overdue = Math.max(0, ChronoUnit.DAYS.between(ib.getDueDate(), ret));
        if (overdue > 0) {
            ib.setFineAmount(BigDecimal.valueOf(overdue * 5)); // 5 currency units per day
        }
        // increase stock
        Book book = ib.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        return issuedBookRepository.save(ib);
    }

    public List<IssuedBook> studentIssued(Long studentId) {
        Student s = studentRepository.findById(studentId).orElseThrow();
        return issuedBookRepository.findByStudentAndReturnDateIsNull(s);
    }

    public List<IssuedBook> overdue() {
        return issuedBookRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now());
    }
}
