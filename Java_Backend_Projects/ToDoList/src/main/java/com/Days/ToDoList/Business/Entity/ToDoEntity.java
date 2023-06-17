package com.Days.ToDoList.Business.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="todo")
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column(name="todostring")
    private String toDoString;

    @Temporal(TemporalType.DATE)
    @Column(name="insertdate")
    private Date insertDate;

    @Column(name="isdeleted")
    private Boolean isDeleted;
    @Column(name="iscompleted")
    private Boolean isCompleted;
    @Column(name="completeddate")
    private Date completedDate;

}
