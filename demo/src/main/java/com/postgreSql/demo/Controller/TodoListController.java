package com.postgreSql.demo.Controller;

import com.postgreSql.demo.Service.TodoListService;
import com.postgreSql.demo.Model.TodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/todoList")
@RequiredArgsConstructor
public class TodoListController {
    @Autowired
    private final TodoListService todoListService;

    //Donner les infos de tous les joueurs
    @RequestMapping(method = RequestMethod.GET )
    public List<TodoList> getAllTodoList(){
        return todoListService.getAllTodoList();
    }

    //Donner les infos d'un joueur en particulier
    @RequestMapping(method = RequestMethod.GET , value = "/{id}")
    public ResponseEntity<TodoList> getTodoList(@PathVariable Long id){

        return ResponseEntity.ok(todoListService.getTodoList(id));
    }

    // Ajouter un joueur
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public  void addTodoList(@RequestBody TodoList joueur){
        todoListService.addTodoList(joueur);
    }

    // Modifier un joueur
    @RequestMapping(method = RequestMethod.PUT , value = "/{id}")
    public void updateTodoList(@RequestBody TodoList todoList, @PathVariable Long id){
        todoListService.updateTodoList(todoList, id);
    }

    // Supprimer un joueur
    @RequestMapping(method = RequestMethod.DELETE , value = "/{id}")
    public void deleteTodoList(@PathVariable Long id){
        todoListService.deleteTodoList(id);
    }
}
