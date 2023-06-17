package com.Days.ToDoList.Service;

import com.Days.ToDoList.Business.Bean.ToDoBean;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoService {

    String addToDo(ToDoBean toDoBean) throws Exception;
    ToDoBean updateToDo(ToDoBean toDoBean) throws Exception;
    Boolean deleteToDo(ToDoBean toDoBean) throws Exception;
    ToDoBean getTodoById(String id) throws Exception;


    List<ToDoBean> getAllToDo() throws Exception;

}
