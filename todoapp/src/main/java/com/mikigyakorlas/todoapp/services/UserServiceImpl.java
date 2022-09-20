package com.mikigyakorlas.todoapp.services;

import com.mikigyakorlas.todoapp.models.dtos.TodoDTO;
import com.mikigyakorlas.todoapp.models.dtos.UserDTO;
import com.mikigyakorlas.todoapp.models.entities.Todo;
import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.models.enums.Priority;
import com.mikigyakorlas.todoapp.models.exceptions.UserNotFoundException;
import com.mikigyakorlas.todoapp.repositories.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  UserRepository repo;

  public UserServiceImpl(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public User findById(String id) throws UserNotFoundException {
    return repo.findById(Integer.parseInt(id)).orElseThrow(UserNotFoundException::new);
  }

  @Override
  public UserDTO findByIdDTO(String id) throws UserNotFoundException {
    int userId;
    try {
      userId = Integer.parseInt(id);
    } catch(NumberFormatException e) {
      throw new UserNotFoundException();
    }
    return convert(repo.findById(userId).orElseThrow(UserNotFoundException::new));
  }

  @Override
  public Integer findIdByName(String name) {
    return repo.findByName(name).getId();
  }

  @Override
  public void saveUser(User user) {
    repo.save(user);
  }

  @Override
  public void deleteUser(String id) {
    repo.deleteById(Integer.parseInt(id));
  }

  public void addTodo(Todo todo, String id) {
    todo.setModifiedAt(LocalDate.now());
    todo.setCreatedAt(LocalDate.now());
    todo.setStatus("undone");
    findById(id).addTodo(todo);
    saveUser(findById(id));
  }

  @Override
  public void validation(User user) throws IllegalArgumentException {
    if (user.getName() == null || user.getName().trim().isEmpty() || user.getName().length() < 6 ||
        user.getName().length() > 20) {
      throw new IllegalArgumentException("You must enter a name between 6 and 20 characters");
    }
    if (repo.existsByName(user.getName())) {
      throw new IllegalArgumentException("This username is already taken");
    }
    if (!user.getEmail().matches(
        "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*")) {
      throw new IllegalArgumentException("You must enter a valid e-mail address!");
    }
    if (repo.existsByEmail(user.getEmail())) {
      throw new IllegalArgumentException("This e-mail address is already taken!");
    }
    if (user.getPassword().length() < 3) {
      throw new IllegalArgumentException("You must enter a password that is longer than 3");
    }
    if (!user.getPassword().matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}")) {
      throw new IllegalArgumentException(
          "Your password must contain at least one uppercase, one lowercase letter and a number");
    }
    if (user.getGender() == null) {
      throw new IllegalArgumentException("Choose a gender");
    }
  }

  @Override
  public void loginCredentials(String username, String password) {
    if (username == null || username.trim().isEmpty()) {
      throw new IllegalArgumentException("You must enter a name");
    }
    if (password == null || password.trim().isEmpty()) {
      throw new IllegalArgumentException("You must enter a password");
    }
    if (!repo.existsByName(username)) {
      throw new IllegalArgumentException("No such user found by this name");
    }
    User user = repo.findByName(username);
    if (!user.getPassword().equals(password)) {
      throw new IllegalArgumentException("Password is not correct");
    }
  }

  @Override
  public void idValidation(String id) throws IllegalArgumentException, UserNotFoundException {
    try {
      Integer.parseInt(id);
      findByIdDTO(id);
    } catch (IllegalArgumentException | UserNotFoundException e) {
      throw new UserNotFoundException();
    }
  }

  @Override
  public UserDTO convert(User user) {
    return new UserDTO(user.getName(), user.getEmail(), user.getGender(), user.getBio(),
        convertTodoList(user.getListOfTodos()));
  }

  @Override
  public List<TodoDTO> convertTodoList(List<Todo> todoList) {
   return todoList.stream()
        .map(x-> new TodoDTO(x.getId(), x.getTitle(), x.getStatus(), x.getDeadline(), x.getPriority(), x.getUser().getName()))
        .collect(Collectors.toList());
  }

  @Override
  public void checkDates(String userId) {
    findById(userId).getListOfTodos().forEach(x-> {
      if (x.getDeadline().toEpochDay() - LocalDate.now().toEpochDay() < 0) {
        x.setStatus("expired");
        x.setPriority(Priority.NONE);
      }
    });
    saveUser(findById(userId));
  }
}
