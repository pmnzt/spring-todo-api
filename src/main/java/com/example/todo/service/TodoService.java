package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.Todo;

@Service
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}