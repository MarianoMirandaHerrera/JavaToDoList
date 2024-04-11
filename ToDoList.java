import java.util.ArrayList;

public class ToDoList {
    ToDoListComponent toDoLista;
    ArrayList <TaskComponent> tasks;
    public ToDoList(ToDoListComponent tda) {
        toDoLista = new ToDoListComponent(tda);
        new ArrayList<>();
    } 
}
