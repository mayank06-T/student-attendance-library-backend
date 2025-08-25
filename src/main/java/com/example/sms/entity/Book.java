package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String author;
    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(nullable = false)
    private Integer quantity;
    private String category;
}
