package com.postgreSql.demo.Repository;

import com.postgreSql.demo.Model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
