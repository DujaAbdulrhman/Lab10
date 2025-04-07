package com.example.lab10.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.data.annotation.Reference;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=4 ) //@Pattern(regexp = "/^[A-Z]+$/i")
    @Column(columnDefinition = "varchar (20) not null")
    private String name;

    @Email @Column(unique = true)
    private String email;

    @NotNull
    @Column(columnDefinition = "Integer  not null" ,name = "age")
    private Integer age;

    private Integer user_id;
   @Pattern(regexp = "JOB_SEEKER|EMPLOYER", message = "Role must be JOB_SEEKER or EMPLOYER")
   @Column(nullable = false, length = 10)
   private String role;





}
