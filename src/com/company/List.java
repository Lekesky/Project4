package com.company;

interface CollectionOfTasks{
    public void allTasks();
}

public class List implements CollectionOfTasks{
    public void allTasks() {
        for (Task a : Main.list) {
            System.out.println("List: " + a);
        }

    }
}
