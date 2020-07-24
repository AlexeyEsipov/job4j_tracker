package ru.job4j.tracker;

public class FindItemById implements UserAction {

    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int selectId = input.askInt("Enter ID: ");
        Item selectItem = tracker.findById(selectId);
        if (selectItem != null) {
            System.out.println("ID: " + selectItem.getId() + ", Name: " + selectItem.getName());
        } else {
            System.out.println("Обект с требуемым ID не найден");
        }
        return true;
    }
}
