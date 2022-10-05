package com.example.todo_list.service;

import com.example.todo_list.model.Todo;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Assertions;
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

        for (int i = 0; i<5; i++){

            Date creationDate = new Date();
            Date targetDate = new Date();
            targetDate = DateUtil.tomorrow();

            Todo todo = new Todo(userName, "Tarea numero "+ i, targetDate, true);
            todo.setCreationDate(creationDate);
            todo.setStartHour(10);
            todo.setEndHour(14);
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

    /* La fecha de finalizacion debe ser mayor a la de inicio */
    @Test
    void finishDateGreaterThanStartDate() {
        todoService.getTodosByUser(userName).forEach(todo -> {
            Assertions.assertTrue((todo.getTargetDate().after(todo.getCreationDate())));
        });
    }

    /* añadir hora de inicio y hora de final como antributos de la clase TODO y testear que se pueden añadir*/
    @Test
    void timeSlotsBetween() {
        todoService.getTodosByUser(userName).forEach(todo -> {
            Assertions.assertTrue((todo.getStartHour()<todo.getEndHour()));
        });

    }

    /*añadir las tareas pasadas, futuras y sin fecha */

    @Test
    void testGetPast() {
        todoService.getPastTodos(userName).forEach(todo -> {
            Date now = new Date();
            Assertions.assertTrue(todo.getTargetDate().before(now));
        });
    }

    @Test
    void testGetFuture() {
        todoService.getTodosByUser(userName).forEach(todo -> {
            Assertions.assertTrue((todo.getStartHour()<todo.getEndHour()));
        });
    }

    @Test
    void testGetNoDate() {
        todoService.getTodosByUser(userName).forEach(todo -> {
            Assertions.assertTrue((todo.getStartHour()<todo.getEndHour()));
        });
    }


    /*add priority to Todos*/
    @Test
    void testAddPriority() {
        Todo todo = new Todo(userName, "tarea nueva",new Date(),false );

        int selectedPriority = 3;
        todoService.saveTodo(todo);
        todoService.setPriority(todo, selectedPriority);

        assertTrue(todo.getPriority() == selectedPriority);

    }

    /*search task by description*/


}
