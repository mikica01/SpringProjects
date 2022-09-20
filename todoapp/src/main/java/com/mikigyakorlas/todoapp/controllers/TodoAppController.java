package com.mikigyakorlas.todoapp.controllers;

import com.mikigyakorlas.todoapp.models.dtos.UserDTO;
import com.mikigyakorlas.todoapp.models.entities.Todo;
import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.models.enums.Priority;
import com.mikigyakorlas.todoapp.models.exceptions.TodoNotFoundException;
import com.mikigyakorlas.todoapp.models.exceptions.UserNotFoundException;
import com.mikigyakorlas.todoapp.services.TodoService;
import com.mikigyakorlas.todoapp.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class TodoAppController {

  UserService userService;
  TodoService todoService;

  public TodoAppController(UserService userService,
                           TodoService todoService) {
    this.userService = userService;
    this.todoService = todoService;
  }

  @ModelAttribute
  public void giveList(Model model) {
    model.addAttribute("urgencies", Arrays.asList(Priority.CASUAL, Priority.IMPORTANT, Priority.URGENT));
  }

  @GetMapping({"/", "/login"})
  public String renderLogin() {
    return "login";
  }

  @GetMapping("/registration")
  public String renderRegistration() {
    return "registration";
  }

  @PostMapping("/add")
  @ApiOperation(value = "adds user to db")
  public String addUser(@ModelAttribute User user, Model model) {
    try {
      userService.validation(user);
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "registration";
    }
    userService.saveUser(user);
    return "redirect:/my-todos/" + user.getId();
  }

  @GetMapping("/my-todos/{id}")
  public String renderUserTodos(@PathVariable String id, Model model) {
    try {
      userService.idValidation(id);
    } catch (IllegalArgumentException | UserNotFoundException e) {
      model.addAttribute("error", e.getMessage());
      return "login";
    }
    userService.checkDates(id);
    model.addAttribute("userId", Integer.parseInt(id));
    model.addAttribute("todos", userService.findByIdDTO(id).getTodos());
    model.addAttribute("user", userService.findByIdDTO(id));
    return "todos";
  }

  @GetMapping("/users/{userId}")
  public String renderProfilePage(@PathVariable String userId, Model model) {
    try {
      userService.idValidation(userId);
    } catch (IllegalArgumentException | UserNotFoundException e) {
      model.addAttribute("error", e.getMessage());
      return "login";
    }
    model.addAttribute("userId", Integer.parseInt(userId));
    model.addAttribute("user", userService.findByIdDTO(userId));
    return "profile";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute User user, Model model) {
    try {
      userService.loginCredentials(user.getName(), user.getPassword());
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "login";
    }
    Integer id = userService.findIdByName(user.getName());
    return "redirect:/users/" + id;
  }

  @GetMapping("users/{userId}/todo")
  public String renderAddTodo(@PathVariable String userId, Model model) {
    try {
      userService.idValidation(userId);
    } catch (IllegalArgumentException | UserNotFoundException e) {
      model.addAttribute("error", e.getMessage());
      return "login";
    }
    model.addAttribute("userId", Integer.parseInt(userId));
    model.addAttribute("todo", null);
    model.addAttribute("user", userService.findByIdDTO(userId));
    return "modifyTodo";
  }

  @PostMapping("/users/{userId}/todos/add")
  public String addTodo(@PathVariable String userId, @ModelAttribute Todo todo, Model model) {
    try {
      todoService.validation(todo);
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("todo", null);
      model.addAttribute("user", userService.findByIdDTO(userId));
      return "modifyTodo";
    }
    userService.addTodo(todo, userId);
    userService.checkDates(userId);
    model.addAttribute("userId", Integer.parseInt(userId));
    model.addAttribute("user", userService.findByIdDTO(userId));
    model.addAttribute("todos", userService.findByIdDTO(userId).getTodos());
    return "todos";
  }

  @PostMapping("/users/delete/{id}")
  public String deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
    return "redirect:/";
  }

  @PostMapping("/users/{userId}/editBio")
  public String editBio(@PathVariable String userId, @RequestParam(required = false) String bio) {
    User user = userService.findById(userId);
    user.setBio(bio);
    userService.saveUser(user);
    return "redirect:/users/" + userId;
  }

  @PostMapping("/todos/delete/{id}")
  public String deleteTodo(@PathVariable String id, Model model) {
    User user = todoService.findById(id).getUser();
    todoService.delete(id);
    model.addAttribute("user", userService.convert(user));
    return "redirect:/my-todos/" + user.getId();
  }

  @GetMapping("/users/{userId}/todo/{todoId}")
  public String renderEditTodo(@PathVariable String userId, @PathVariable String todoId,
                               Model model) {
    Todo todo;
    try {
      userService.findByIdDTO(userId);
      todo = todoService.findById(todoId);
    } catch (UserNotFoundException | TodoNotFoundException e) {
      model.addAttribute("error", e.getMessage());
      return "login";
    }
    model.addAttribute("todo", todoService.convert(todo));
    model.addAttribute("userId", Integer.parseInt(userId));
    return "modifyTodo";
  }

  @PostMapping("/users/{userId}/todo/{todoId}")
  public String editTodo(@PathVariable String userId, @PathVariable String todoId,
                         @ModelAttribute Todo todo, Model model) {
    Todo editingTodo = todoService.findById(todoId);
    if (todo.getPriority() == null && editingTodo.getPriority() == Priority.DONE) {
      todo.setPriority(Priority.DONE);
    }
    try {
      todoService.validation(todo);
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("todo", editingTodo);
      model.addAttribute("userId", Integer.parseInt(userId));
      return "modifyTodo";
    }
    todoService.modify(todoId, todo, userService.findById(userId));
    return "redirect:/my-todos/" + userId;
  }

  @GetMapping("/users/{userId}/sort")
  public String sortByTitle(@PathVariable String userId,
                            @RequestParam(required = false) String sort,
                            @RequestParam(required = false) Boolean asc, Model model) {
    try {
      userService.idValidation(userId);
      todoService.sortValidation(sort, asc);
    } catch(UserNotFoundException | IllegalArgumentException e) {
      UserDTO user = userService.findByIdDTO(userId);
      model.addAttribute("error", e.getMessage());
      model.addAttribute("todos", user.getTodos());
      model.addAttribute("user", user);
      model.addAttribute("userId", userId);
      return "todos";
    }

    model.addAttribute("todos", todoService.sort(userId, sort, asc));
    model.addAttribute("user", userService.findByIdDTO(userId));
    model.addAttribute("userId", userId);
    return "todos";
  }

  @PostMapping("/todos/{todoId}/complete")
  public String completeTodo(@PathVariable String todoId,
                             @RequestParam(required = false) String user) {
    todoService.completeTodo(todoId);
    return "redirect:/my-todos/" + user;
  }
}
