package com.mikigyakorlas.todoapp.services;

import com.mikigyakorlas.todoapp.models.dtos.TodoDTO;
import com.mikigyakorlas.todoapp.models.dtos.UserDTO;
import com.mikigyakorlas.todoapp.models.entities.Todo;
import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.models.exceptions.UserNotFoundException;
import java.util.List;

public interface UserService {
  User findById(String id) throws UserNotFoundException;
  UserDTO findByIdDTO(String id) throws UserNotFoundException;
  Integer findIdByName(String name);
  void saveUser(User user);
  void deleteUser(String id);
  void addTodo(Todo todo, String id);
  void idValidation(String id) throws IllegalArgumentException, UserNotFoundException;
  void validation(User user) throws IllegalArgumentException;
  void loginCredentials(String username, String password);
  UserDTO convert(User user);
  List<TodoDTO> convertTodoList(List<Todo> todoList);
  void checkDates(String userId);
}
