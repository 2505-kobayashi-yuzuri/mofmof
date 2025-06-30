package com.example.mofmof.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter

public class Tasks {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String content;

    @Column
    private int status;

    @Column(name = "limit_date")
    private Date limitDate;

    @Column(name = "created_date", insertable = false, updatable = false)
    //タイムスタンプ型に直す　DBとentityの型を合わせるため
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date", insertable = false, updatable = true)
    private Date updatedDate;

}
