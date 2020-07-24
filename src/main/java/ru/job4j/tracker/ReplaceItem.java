package ru.job4j.tracker;

public class ReplaceItem implements UserAction {

    @Override
    public String name() {
        return "=== Edit item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int selectId = input.askInt("Enter ID: ");
        Item editItem = new Item();
        editItem.setName(input.askStr("Enter new Name:"));
        if (tracker.replace(selectId, editItem)) {
            System.out.println("Объект успешно изменен");
        } else {
            System.out.println("Объект не изменен");
        }
        return true;
    }
}
