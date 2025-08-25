package com.example.sms.service;

import com.example.sms.entity.Book;
import com.example.sms.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repo;
    public Book create(Book b) { return repo.save(b); }
    public Page<Book> list(int page, int size) { return repo.findAll(PageRequest.of(page, size)); }
    public Book get(Long id) { return repo.findById(id).orElseThrow(); }
    public Book update(Long id, Book b) {
        Book e = get(id);
        e.setTitle(b.getTitle()); e.setAuthor(b.getAuthor()); e.setIsbn(b.getIsbn());
        e.setQuantity(b.getQuantity()); e.setCategory(b.getCategory());
        return repo.save(e);
    }
    public void delete(Long id) { repo.deleteById(id); }
}
