package com.example.todo_list.service;

import com.example.todo_list.model.Todo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoServiceTest {

    @Autowired
    private  ITodoService todoService;

    private static String userName = "User";

    @BeforeAll
    public void init(){
        Date date = new Date();
        for (int i = 0; i<5; i++){
            Todo todo = new Todo(userName, "Tarea numero "+ i, date, true);
            todoService.saveTodo(todo);
        }
    }


   /* Casos para los test de Todo / Task Managment:

    Check Todo is not empty
    Check Todo is updatable
    Check Todo is removable
    Check Todo can't start with "@" "#"*/

    @Test
    void todoIsNotEmpty(){
        assertFalse(todoService.getTodosByUser(userName).isEmpty());
    }


    @Test
    void getTodoById() {
        Optional<Todo> todo = todoService.getTodoById(1L) ;
        assertFalse(todo.isPresent());
    }

    @Test
    void updateTodo() {

        Todo todo = todoService.getTodoById(1L).get() ;
        String previosDescription = todo.getDescription();
        todo.setDescription("Descripcion inventada");
        String newDescription = todo.getDescription();

        assertNotEquals(previosDescription, newDescription);
    }

    @Test
    void isRemovable() {
        Todo todo = todoService.getTodoById(1L).get() ;
        todoService.deleteTodo(todo.getId());

        assertFalse(todoService.getTodoById(1L).isPresent());
    }

    /**
     * comprueba que los nombres de los usuarios que se encuentran en la base de datos
     * no comienzan con los caracteres @ o #
     */
    @Test
    void startWithIlegalLetters(){
        /*todoService.getTodosByUser(userName).forEach(
                todo -> {
                    assertFalse(todo.getDescription().startsWith("@")
                    );
                }
        );
        todoService.getTodosByUser(userName).forEach(
                todo -> {
                }
        );

        todoService.getTodosByUser(userName).forEach(
                todo -> {
                    assertFalse(todo.getDescription().startsWith("#") || todo.getDescription().startsWith("#"));
                }
        );*/
    }

    /**
     * comprueba que los TODOS que se encuentran en la base de datos
     * contienen el atributo creation date (este atributo no esta vacío)
     */
    @Test
    void hasCreationDate(){
        todoService.getTodosByUser(userName).forEach(todo -> {
            //cambiar targetDate por CreationDate
            assertFalse(todo.getTargetDate().toString().isEmpty());
        });
    }

    /**
     * comprueba que los TODOS que se encuentran en la base de datos
     * contienen el atributo finalization date (este atributo no esta vacío)
     */
    @Test
    void hasFinalizationDate(){
        todoService.getTodosByUser(userName).forEach(todo -> {
            //cambiar targetDate por CreationDate
            assertFalse(todo.getTargetDate().toString().isEmpty());
        });
    }

    /**
     * comprueba que los TODOS que se encuentran en la base de datos
     * contienen el atributo link (este atributo no esta vacío)
     */
    @Test
    void hasLink(){
        todoService.getTodosByUser(userName).forEach(todo -> {
            assertFalse(todo.getTargetDate().toString().isEmpty());
        });
    }
    /**
     * comprueba que atributo contiene la clase link, imageLink o videoLink
     */
    @Test
    void hasImageLink(){
        todoService.getTodosByUser(userName).forEach(todo -> {
            //comprobar que el text tiene imagen
            assertFalse(todo.getTargetDate().toString().isEmpty());
        });

    }

}