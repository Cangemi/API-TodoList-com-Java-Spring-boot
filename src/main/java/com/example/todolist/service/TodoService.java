package com.example.todolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;

@Service
public class TodoService {

    private TodoRepository todoRepository;


    public TodoService(com.example.todolist.repository.TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> list(){
        Sort sort = Sort.by("prioridade").descending().and(
            Sort.by("nome").ascending()
        );
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Long id, Todo todoDetails){
        
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo not found with id: " + id));


        todo.setNome(todoDetails.getNome());
        todo.setDescricao(todoDetails.getDescricao());
        todo.setPrioridade(todoDetails.getPrioridade());
        todo.setRealizado(todoDetails.isRealizado());

        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(Long id){
        Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todo not found with id: " + id));
        todoRepository.delete(todo);
        return list();
    }

}
