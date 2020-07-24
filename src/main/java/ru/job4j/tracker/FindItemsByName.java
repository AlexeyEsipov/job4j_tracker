package ru.job4j.tracker;

public class FindItemsByName implements UserAction {
    private final Output out;

    public FindItemsByName(Output out) {
        this.out = out;
    }

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
                out.println("ID: " + el.getId() + ", Name: " + el.getName());
            }
        } else {
            out.println("Объекты с требуемым именем не найдены");
        }
        return true;
    }
}
