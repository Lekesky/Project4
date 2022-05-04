package com.company;

public class Task implements Comparable<Task> {
    private String title;
    private String desc;
    public int priority;

    public Task(String title, String desc, int priority) {
        this.title = title;
        this.desc = desc;
        this.priority = priority;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", priority=" + priority +
                '}';
    }


    @Override
    public int compareTo(Task list) {

        int compareResults = this.title.compareTo(list.getTitle());

        if (this.priority == list.getPriority()) {
            if (compareResults == 0){
                return this.title.compareTo(list.getTitle());
            }else{
                return compareResults;
            }

        } else if (this.priority > list.getPriority()) {
            return 1;

        } else {
            return -1;
        }
    }
}

