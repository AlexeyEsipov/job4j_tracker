package ru.job4j.tracker;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private int id;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Item another) {
        return Integer.compare(id, another.id);
    }

    @Override
    public String toString() {
        return "Item{"
                + " ID= " + id + ","
                + " name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}