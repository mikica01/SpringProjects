package com.mikigyakorlas.todoapp.controllers;

import com.mikigyakorlas.todoapp.services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoAppRestController {
    TodoService service;

    public TodoAppRestController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/api/todos")
    public ResponseEntity<?> getAll() {
      return ResponseEntity.ok(service.getAll());
  }
}
