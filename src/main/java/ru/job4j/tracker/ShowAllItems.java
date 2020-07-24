package ru.job4j.tracker;

public class ShowAllItems implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] allItem = tracker.findAll();
        for (Item el: allItem) {
            System.out.println("Item: ID: " + el.getId() + ", Name: " + el.getName());
        }
        return true;
    }
}
