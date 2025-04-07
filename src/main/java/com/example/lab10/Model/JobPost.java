package com.example.lab10.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.text.DateFormat;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor @Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, message = "Title should be at least 4 characters")
    @Column(nullable = false, length = 255)
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Pattern(regexp = "(?i).*\\b(location)\\b.*\\b(salary)\\b.*", message = "Description must include the words 'location' and 'salary'")
    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime postingDate;
}
