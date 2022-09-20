package com.mikigyakorlas.todoapp.services;

import com.mikigyakorlas.todoapp.models.dtos.TodoDTO;
import com.mikigyakorlas.todoapp.models.entities.Todo;
import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.models.enums.Priority;
import com.mikigyakorlas.todoapp.models.exceptions.TodoNotFoundException;
import com.mikigyakorlas.todoapp.repositories.TodoRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
  TodoRepository todoRepo;

  public TodoServiceImpl(TodoRepository todoRepo) {
    this.todoRepo = todoRepo;
  }

  @Override
  public Todo findById(String id) throws TodoNotFoundException {
    if (!todoRepo.existsById(Integer.parseInt(id))) {
      throw new TodoNotFoundException();
    }
    return todoRepo.findById(Integer.parseInt(id)).orElse(null);
  }

  @Override
  public List<TodoDTO> getAll() {
    return todoRepo.findAll().stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }

  @Override
  public void modify(String todoId, Todo todo, User user) {
    Todo beingEdited = todoRepo.findById(Integer.parseInt(todoId)).orElse(new Todo());
    beingEdited.setPriority(todo.getPriority());
    if (todo.getPriority() != Priority.DONE) {
      beingEdited.setStatus("undone");
    }
    beingEdited.setTitle(todo.getTitle());
    beingEdited.setModifiedAt(LocalDate.now());
    beingEdited.setDeadline(todo.getDeadline());
    todoRepo.save(beingEdited);
  }

  @Override
  public void delete(String id) {
    findById(id).getUser().getListOfTodos().remove(findById(id));
    todoRepo.deleteById(Integer.parseInt(id));
  }

  @Override
  public void completeTodo(String todoId) {
    Todo todo = findById(todoId);
    todo.setPriority(Priority.DONE);
    todo.setStatus("done");
    todo.setModifiedAt(LocalDate.now());
    todoRepo.save(todo);
  }

  @Override
  public TodoDTO convert(Todo todo) {
    return new TodoDTO(todo.getId(), todo.getTitle(), todo.getStatus(), todo.getDeadline(),
        todo.getPriority(), todo.getUser().getName());
  }

  public void validation(Todo todo) {
    if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
      throw new IllegalArgumentException("You must write a title!");
    }
    if (todo.getDeadline() == null) {
      throw new IllegalArgumentException("You must set a deadline");
    }
    if (todo.getPriority() == null && todo.getPriority() != Priority.DONE) {
      throw new IllegalArgumentException("You must set a priority");
    }
  }

  @Override
  public void idValidation(String id) throws IllegalArgumentException, TodoNotFoundException {
    try {
      Integer.parseInt(id);
      findById(id);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    } catch (TodoNotFoundException e) {
      throw new TodoNotFoundException();
    }
  }

  @Override
  public void sortValidation(String sort, Boolean asc) throws IllegalArgumentException{
    List<String> sortables = Arrays.asList("title", "priority", "deadline");
    if (sort == null || sort.trim().isEmpty() || !sortables.contains(sort)) {
      throw new IllegalArgumentException("Cannot sort by " + sort);
    }
    if (asc == null) {
      throw new IllegalArgumentException("You must give a sort order!");    }
  }

  public List<TodoDTO> sort(String userId, String sort, boolean asc) {
    if (asc) {
      if (sort.equals("title")) {
        return todoRepo.sortTitleAsc(Integer.parseInt(userId), sort).stream().map(this::convert).collect(
            Collectors.toList());
      }
      if (sort.equals("priority")) {
        return todoRepo.sortPriorityAsc(Integer.parseInt(userId), sort).stream().map(this::convert).collect(
            Collectors.toList());
      }
      if (sort.equals("deadline")) {
        return todoRepo.sortDeadlineAsc(Integer.parseInt(userId), sort).stream().map(this::convert).collect(
            Collectors.toList());
      }

    } else {

      if (sort.equals("title")) {
        return todoRepo.sortTitleDesc(Integer.parseInt(userId), sort).stream().map(this::convert).collect(
            Collectors.toList());
      }
      if (sort.equals("priority")) {
        return todoRepo.sortPriorityDesc(Integer.parseInt(userId), sort).stream().map(this::convert).collect(
            Collectors.toList());
      }
      if (sort.equals("deadline")) {
        return todoRepo.sortDeadlineDesc(Integer.parseInt(userId), sort).stream().map(this::convert).collect(
            Collectors.toList());
      }
    }
    return null;
  }
}
