package com.postgreSql.demo;

import com.postgreSql.demo.Controller.TodoListController;
import com.postgreSql.demo.Model.TodoList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // ceci permet d'etre sur un nouveau context (2 joueurs en base) pour chaque test
class TodoListApplicationTests {

    @Autowired
    private TodoListController todoListController;


    @Test
    void contextLoads() {
        Assertions.assertThat(todoListController).isNotNull();
    }

    @Test
    void getAllTodoList() {
        List<TodoList> joueurs = todoListController.getAllTodoList();
        Assertions.assertThat(joueurs).containsExactlyInAnyOrder(
                TodoList.builder().todoTitle("test").todoDescription("test").build(),
                TodoList.builder().todoTitle("test1").todoDescription("test1").build());
    }

    @Test
    void getTodoList() {
        TodoList todoList = todoListController.getTodoList(1l).getBody();
        Assertions.assertThat(todoList).isEqualTo(TodoList.builder().todoTitle("test").todoDescription("test").build());
    }

    @Test
    void addTodoList() {
        Assertions.assertThat(todoListController.getAllTodoList()).hasSize(2);
        todoListController.addTodoList(TodoList.builder().todoTitle("test3").todoDescription("test3").build());
        Assertions.assertThat(todoListController.getAllTodoList()).hasSize(3);
    }

    @Test
    void updateJoueur() {
        TodoList todoList = todoListController.getTodoList(1l).getBody();
        Assertions.assertThat(todoList.getTodoTitle()).isEqualTo("test");

        todoList.setTodoTitle("testAgain");
        todoListController.updateTodoList(todoList, todoList.getTodoId());

        Assertions.assertThat(todoListController.getTodoList(1l).getBody().getTodoTitle()).isEqualTo("testAgain");
    }

    @Test
    void deleteJoueur() {
        Assertions.assertThat(todoListController.getAllTodoList()).hasSize(2);
        todoListController.deleteTodoList(2l);
        Assertions.assertThat(todoListController.getAllTodoList()).hasSize(1);
    }
}
