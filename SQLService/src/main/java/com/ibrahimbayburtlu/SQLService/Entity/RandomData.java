package com.ibrahimbayburtlu.SQLService.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "RandomData")
@Data
public class RandomData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "random_integer")
    private int randomInteger;

    @Column(name = "md5_last_two_characters")
    private String md5HCharacters;

}
