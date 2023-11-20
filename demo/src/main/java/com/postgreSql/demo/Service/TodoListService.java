package com.postgreSql.demo.Service;

import com.postgreSql.demo.Repository.TodoListRepository;
import com.postgreSql.demo.Model.TodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private TodoListRepository todoListRepository;

    @Autowired
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoList> getAllTodoList(){
        List<TodoList> todoLists = new ArrayList<>();
        todoListRepository.findAll().forEach(todoList -> {
            todoLists.add(todoList);
        });
        return todoLists;
    }

    public TodoList getTodoList(Long id){
        return todoListRepository.findById(id).orElse(null);
    }

    public void deleteTodoList(Long id){
        todoListRepository.deleteById(id);
    }

    public void addTodoList(TodoList todoList) {
        todoListRepository.save(todoList);
    }

    public void updateTodoList(TodoList todoList, Long id) {
        todoListRepository.save(todoList);
    }

}
