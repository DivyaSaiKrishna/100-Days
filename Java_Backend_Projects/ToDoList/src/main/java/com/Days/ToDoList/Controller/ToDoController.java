package com.Days.ToDoList.Controller;

import com.Days.ToDoList.Business.Bean.ToDoBean;
import com.Days.ToDoList.Service.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ToDoController {

    @Autowired
    private ToDoServiceImpl toDoService;

    public ToDoController(ToDoServiceImpl toDoService) {
        this.toDoService = toDoService;
    }

    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("test", HttpStatus.OK);
    }

    @RequestMapping(
            value="/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ResponseEntity<String> addToDo(@RequestBody ToDoBean toDoBean) throws Exception {
        System.out.println(toDoBean.toString());
        String id = toDoService.addToDo(toDoBean);
        return new ResponseEntity<String>("Created To Do id : "+id,HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ToDoBean> updateToDo(@RequestBody ToDoBean toDoBean) throws Exception {
        ToDoBean toDoBean1 = toDoService.updateToDo(toDoBean);
        if(toDoBean1==null){
            return new ResponseEntity<ToDoBean>(toDoBean1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ToDoBean>(toDoBean1,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ToDoBean> deleteToDo(@RequestBody ToDoBean toDoBean) throws Exception {
        Boolean check = toDoService.deleteToDo(toDoBean);
        if(check){
            return new ResponseEntity<ToDoBean>(new ToDoBean(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ToDoBean>(toDoBean, HttpStatus.OK);
    }

    @RequestMapping(
            value ="/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ToDoBean> getById(@PathVariable("id") String id) throws Exception {
        ToDoBean toDoBean = toDoService.getTodoById(id);
        if(toDoBean==null){
            return new ResponseEntity<ToDoBean>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ToDoBean>(toDoBean,HttpStatus.OK);
    }

    @RequestMapping(
            value ="/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<ToDoBean>> getAllToDo() throws Exception {
        Collection<ToDoBean> listOfToDo = toDoService.getAllToDo();
        return new ResponseEntity<Collection<ToDoBean>>(listOfToDo,HttpStatus.OK);
    }

}
