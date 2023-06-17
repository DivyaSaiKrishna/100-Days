package com.Days.ToDoList.DAO;

import com.Days.ToDoList.Business.Bean.ToDoBean;
import com.Days.ToDoList.Business.Entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoDAO extends JpaRepository<ToDoEntity,String> {

    @Query("SELECT e FROM ToDoEntity e WHERE e.isDeleted = false ")
    List<ToDoEntity> findAllToDo();

}
