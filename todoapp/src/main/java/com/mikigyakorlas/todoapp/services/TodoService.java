package com.mikigyakorlas.todoapp.services;


import com.mikigyakorlas.todoapp.models.dtos.TodoDTO;
import com.mikigyakorlas.todoapp.models.entities.Todo;
import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.models.exceptions.TodoNotFoundException;
import java.util.List;

public interface TodoService {
  Todo findById(String id);
  List<TodoDTO> getAll();
  void modify(String todoId, Todo todo, User user);
  void delete(String id);
  void completeTodo(String todoId);
  TodoDTO convert(Todo todo);
  void validation(Todo todo)  throws IllegalArgumentException;
  void idValidation(String id)  throws IllegalArgumentException, TodoNotFoundException;
  void sortValidation(String sort, Boolean asc) throws IllegalArgumentException;
  List<TodoDTO> sort(String userId, String sort, boolean asc);
}
