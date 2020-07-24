package ru.job4j.tracker;

public class FindItemsByName implements UserAction {

    @Override
    public String name() {
        return "=== Find items by name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String keyName = input.askStr("Enter name: ");
        Item[] allItem = tracker.findByName(keyName);
        if (allItem.length != 0) {
            for (Item el: allItem) {
                System.out.println("Item: ID: " + el.getId() + ", Name: " + el.getName());
            }
        } else {
            System.out.println("Объекты с требуемым именем не найдены");
        }
        return true;
    }
}
