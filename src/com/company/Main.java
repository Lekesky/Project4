// Leke Obayemi
// 3/3/22
//Project 1
package com.company;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);
    static List tasks = new List();
    static int action;  // Initialize action variable
    static ArrayList <Task> list = new ArrayList<>();     // Create the ArrayList

    static File saveDate = new File("C:\\Users\\113768\\Downloads\\Project4\\data.json");
    static FileReader fileReader;
    static Gson gson = new Gson();

    static {
        try{
            fileReader = new FileReader(saveDate);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static TaskList master = gson.fromJson(fileReader, TaskList.class);

    static void serialize(ArrayList<Task> a){
        try{
            FileWriter writer = new FileWriter("data.json");
            gson.toJson(a, writer);
            writer.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void prompt(){        //Prompt
        System.out.println("""
                        Please choose an option:

                        (1) Add a task.
                        (2) Remove a task.
                        (3) Update a task.
                        (4) List all tasks.
                        (5) List all tasks of a certain priority
                        (6) Serialize 
                        (0) Exit.
                        """);

        System.out.println("Select an Action ");
    }

    public static void addTask(){       //Adds task
        System.out.println("What task would you like to add?"); //Name of task
        String itemAdd = input.nextLine();
        System.out.println("Enter task description");
        String itemDesc = input.nextLine();
        boolean priority = true;
        while(priority){
            try{
                System.out.println("Enter task priority: (5 - Most Important   0 - Least Important)");
                input.nextLine();
                int itemPrior = input.nextInt();
                Task a = new Task(itemAdd, itemDesc, itemPrior);
                list.add(a);
                priority = false;
            }catch(InputMismatchException e){
                System.out.println("Must be an integer");
            }
        }
        System.out.println("List has been updated!");
    }

    public static void removeTask(){        //Removes task
        boolean remove = true;
        while(remove){
            try{
                System.out.println("List: " + list);
                System.out.println("What task would you like to remove?");
                input.nextLine();
                int itemRemove = input.nextInt();
                list.remove(itemRemove);
                remove = false;
            }catch(InputMismatchException e){
                System.out.println("\nMust be an integer");
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("\nNumber entered is outside of array\nTry again\n");
            }
        }
        System.out.println("List has been updated!");
    }

    public static void updateTask(){        //Updates task
        Integer itemUpdateNum = null;
        boolean update = true;
        while(update){
            try{
                tasks.allTasks();
                System.out.println("Select an element to update");
                input.nextLine();
                itemUpdateNum = input.nextInt();
                update = false;
            }catch(InputMismatchException e){
                System.out.println("\nMust be an integer");
            }
        }

        System.out.println("What task would you like to replace it with?");
        input.nextLine();
        String itemUpdate = input.nextLine();
        System.out.println("Enter new description for task");
        String itemNewDesc = input.nextLine();
        boolean priority = true;
        while(priority){
            try{
                System.out.println("Enter task priority: (5 - Most Important   0 - Least Important)");
                input.nextLine();
                int itemNewPrior = input.nextInt();

                if (!(itemNewPrior <= 5 && itemNewPrior >= 0)){
                        System.out.println("Number entered must be between 0-5\nTry again\n");
                        System.out.println("Enter task priority: (5 - Most Important   0 - Least Important)");
                        itemNewPrior = input.nextInt();
                }
                Task a = new Task(itemUpdate, itemNewDesc, itemNewPrior);
                list.set(itemUpdateNum, a);
                priority = false;
            }catch(InputMismatchException e){
                System.out.println("Must be an integer");
            }catch (IndexOutOfBoundsException f){
                if(list.size() == 0){
                    System.out.println("Array is empty, please add a task first");
                    break;
                }else{
                    System.out.println("\nNumber must be in bounds");
                    System.out.println("\nSelect an element to update");
                    itemUpdateNum = input.nextInt();
            }
        }

        System.out.println("List has been updated!");
        }
    }

    public static void listTask(){      //Prints all tasks in the ArrayList
        Collections.sort(list);
        if(list.size() == 0 && master == null){
            System.out.println("Array is empty");
        }else{
            tasks.allTasks();
            if(master != null){
                System.out.println(master);
            }

        }
    }

    public static void listTaskPriority(){
        System.out.println("Enter a priority: (5 - Most Important   0 - Least Important)");
        boolean listPriority = true;
        while(listPriority){
            try{
                input.nextLine();
                int priority = input.nextInt();
                if(priority >= 0 && priority <= 5){
                    for (Task a: list){     //Prints all task of a specified priority
                        if (a.priority == priority){
                            System.out.println(a);
                        }
                    }
                }else{
                    System.out.println("Number entered is outside of array\nTry again\n");
                    Main.listTaskPriority();
                }
                listPriority = false;
            }catch(InputMismatchException e){
                System.out.println("\nMust be an integer");
            }
        }
    }



    public static void main(String[] args) {

        prompt();       //Initially prompt user with options before entering the while loop
        try {
            int action1 = input.nextInt();
            action = action1;
            input.nextLine();
        }catch (InputMismatchException e){
            System.out.println("Action must be an integer");
            input.nextLine();
            Main.main(null);
        }


        while(action != 0 ) {
            if (action == 1) {
                addTask();

            } else if (action == 2) {
                removeTask();

            } else if (action == 3) {
                updateTask();

            } else if (action == 4) {
                listTask();
            } else if (action == 5) {
                Collections.sort(list);
                listTaskPriority();

            } else if (action == 0) {
                System.exit(0);

            } else
                System.out.println("Invalid action, Please try again..");
            prompt();

            try {
                int action2 = input.nextInt();
                action = action2;
                input.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Action must be an integer");
                input.nextLine();
                Main.main(null);
            }
        }
        serialize(list);
        System.out.println("Tasks have been saved, Good-bye!");        //Prints if user initially enters 0
    }
}
