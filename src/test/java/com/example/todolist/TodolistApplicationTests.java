package com.example.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.todolist.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testeCreateTodoSuccess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());



	}

	@Test
	void testeCreateTodoFailure() {
		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(
				new Todo("","",false,0)
			).exchange()
			.expectStatus().isBadRequest();
	}

	@Test
	void testeUpdateTodoSuccess() {

		var todo = new Todo("Mudado","Mudado",false,10);

		Todo todos = webTestClient
        .get()
        .uri("/todos")
        .exchange()
        .expectStatus().isOk()
        .returnResult(Todo.class)
        .getResponseBody()
        .blockFirst();

		Long id = todos.getId();


		webTestClient
			.put()
			.uri("/todos/{id}", id)
			.bodyValue(
				todo
			).exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}
	@Test
	void testeUpdateTodoFailure() {
		webTestClient
		.put()
		.uri("/todos/500")
		.bodyValue(
			new Todo("teste","teste",false,0)
		).exchange()
		.expectStatus().isBadRequest();
	}

	@Test
	void testeDeleteTodoSuccess() {
		Todo todo = webTestClient
        .get()
        .uri("/todos")
        .exchange()
        .expectStatus().isOk()
        .returnResult(Todo.class)
        .getResponseBody()
        .blockFirst();

		Long id = todo.getId();

		webTestClient
		.delete()
		.uri("/todos/{id}", id)
		.exchange()
        .expectStatus().isOk();
	}
	@Test
	void testeDeleteTodoFailure() {
		webTestClient
		.delete()
		.uri("/todos/500")
		.exchange()
        .expectStatus().isBadRequest();
	}

}
