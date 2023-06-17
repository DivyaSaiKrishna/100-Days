package com.Days.ToDoList.Business.Bean;

import lombok.Data;

import java.util.Date;

@Data
public class ToDoBean {

    private String id;
    private String toDoString;
    private Date insertDate;
    private Boolean isDeleted;
    private Boolean isCompleted;
    private Date completedDate;


}