package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController implements WebMvcConfigurer {

    @Autowired
    private TodoService todoService;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

        converters.add(converter);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
    }
}