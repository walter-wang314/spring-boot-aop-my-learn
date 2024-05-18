package com.walter.example.springaop.repository;

import com.walter.example.springaop.annotation.Timed;
import com.walter.example.springaop.dto.TodoList;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.lang.String.format;

/**
 * UUID: bc434e38-f377-4c64-9ee9-55975790ab02
 * for different users
 */

@Repository
public class TodoRepository {
    private final Map<UUID, List<TodoList>> todoMap = new HashMap<>();

    TodoRepository() {
        UUID uuid = UUID.fromString("bc434e38-f377-4c64-9ee9-55975790ab02");
        System.out.println("uuid = " + uuid);
        List<TodoList> arrayList = new ArrayList<>();
        TodoList todoList = new TodoList();
        todoList.setName("morning");
        ArrayList<String> todoList1 = new ArrayList<>();
        todoList1.add("wash face");
        todoList1.add("brash teach");
        todoList.setTodos(todoList1);

        arrayList.add(todoList);
        todoMap.put(uuid, arrayList);
    }

    @Timed
    public void add(final UUID userId, final TodoList list) {
        if (get(userId, list.getName()).isPresent()) {
            throw new IllegalArgumentException(format("List with name %s already exists", list.getName()));
        }
        if (list.getTodos() == null) {
            list.setTodos(new ArrayList<>());
        }
        get(userId).add(list);
    }

    @Timed
    public List<TodoList> get(final UUID userId) {
        return todoMap.computeIfAbsent(userId, u -> new ArrayList<>());
    }

    @Timed
    public Optional<TodoList> get(final UUID userId, final String todoList) {
        return get(userId).stream().filter(l -> l.getName().equalsIgnoreCase(todoList)).findAny();
    }

    @Timed
    public void delete(final UUID userId) {
        todoMap.remove(userId);
    }

    @Timed
    public void delete(final UUID userId, final String todoList) {
        List<TodoList> list = get(userId);

        list.removeIf(l -> l.getName().equals(todoList));
    }

    @Timed
    public void deleteAll() {
        todoMap.clear();
    }
}
