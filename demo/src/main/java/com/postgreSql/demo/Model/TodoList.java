package com.postgreSql.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    private String todoTitle;
    private String todoDescription;

}
