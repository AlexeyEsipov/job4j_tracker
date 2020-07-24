package ru.job4j.tracker;

public class DeleteItem implements UserAction {

    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int selectId = input.askInt("Enter ID: ");
        if (tracker.delete(selectId)) {
            System.out.println("Item was deleted successfully");
        } else {
            System.out.println("Объект не удален");
        }
        return true;
    }
}
