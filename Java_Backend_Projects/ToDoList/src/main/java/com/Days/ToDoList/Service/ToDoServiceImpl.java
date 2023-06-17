package com.Days.ToDoList.Service;

import com.Days.ToDoList.Business.Bean.ToDoBean;
import com.Days.ToDoList.Business.Entity.ToDoEntity;
import com.Days.ToDoList.DAO.ToDoDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    private ToDoDAO toDoDAO;

    @Override
    public String addToDo(ToDoBean toDoBean) throws Exception {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoBean.setInsertDate(new Date());
        BeanUtils.copyProperties(toDoBean, toDoEntity);
        //System.out.println("entity:"+toDoEntity.toString());
        return toDoDAO.save(toDoEntity).getId();
    }

    @Override
    public ToDoBean updateToDo(ToDoBean toDoBean) throws Exception {
        try {
            ToDoBean toDoBean1 = null;
            ToDoEntity toDoEntity = toDoDAO.getById(toDoBean.getId());
            if(toDoEntity==null){
                System.out.println("Invalid to do");
            }else{
                BeanUtils.copyProperties(toDoBean,toDoEntity);
                toDoDAO.save(toDoEntity);
                BeanUtils.copyProperties(toDoEntity,toDoBean1);
                return toDoBean1;
            }
        }catch (Exception exception){
            throw exception;
        }
        return null;
    }


    @Override
    public Boolean deleteToDo(ToDoBean toDoBean) throws Exception {
            try {
                ToDoEntity toDoEntity = toDoDAO.getById(toDoBean.getId());

                if(toDoEntity!=null){
                    toDoBean.setIsDeleted(true);
                    BeanUtils.copyProperties(toDoBean,toDoEntity);
                    toDoDAO.save(toDoEntity);
                }
                return true;
            }catch (Exception exception){
                throw new Exception();
            }
    }

    @Override
    public ToDoBean getTodoById(String id) throws Exception {
        Optional<ToDoEntity> toDoEntity = toDoDAO.findById(id);
        ToDoBean toDoBean = null;
        BeanUtils.copyProperties(toDoEntity,toDoBean);
        return toDoBean;
    }

    @Override
    public List<ToDoBean> getAllToDo() throws Exception {
        List<ToDoBean> toDoBeanList = new ArrayList<ToDoBean>();
        List<ToDoEntity> listEntity = toDoDAO.findAllToDo();
        System.out.println(listEntity.toString());
        for(ToDoEntity toDoEntity :listEntity){
            ToDoBean toDoBean = new ToDoBean();
            BeanUtils.copyProperties(toDoEntity,toDoBean);
            toDoBeanList.add(toDoBean);
        }

        return toDoBeanList;
    }
}
