package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "New item name", "1"}
        );
        UserAction[] actions = {
                new ReplaceItem(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteItem(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                        + "0. === Exit Program ===" + System.lineSeparator()));
    }

    @Test
    public void whenFindById() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new FindItemById(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()
                  + "ID: 1, Name: New item" + System.lineSeparator()
                  + "Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()));
    }

    @Test
    public void whenNotFindById() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", "101", "1"}
        );
        UserAction[] actions = {
                new FindItemById(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()
                  + "Объект с требуемым ID не найден" + System.lineSeparator()
                  + "Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()));
    }

    @Test
    public void whenFindByName() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", item.getName(), "1"}
        );
        UserAction[] actions = {
                new FindItemsByName(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()
                    + "ID: 1, Name: New item" + System.lineSeparator()
                    + "Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()));
    }

    @Test
    public void whenNotFindByName() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("New item"));
        Input in = new StubInput(
                new String[]{"0", "Name", "1"}
        );
        UserAction[] actions = {
                new FindItemsByName(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()
                    + "Объекты с требуемым именем не найдены" + System.lineSeparator()
                    + "Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()));
    }

    @Test
    public void whenFindAll() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("First item"));
        tracker.add(new Item("Second item"));
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        UserAction[] actions = {
                new ShowAllItems(out),
                new ExitProgram(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                        + "0. === Show all items ===" + System.lineSeparator()
                        + "1. === Exit Program ===" + System.lineSeparator()
                        + "Item: ID: 1, Name: First item" + System.lineSeparator()
                        + "Item: ID: 2, Name: Second item" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. === Show all items ===" + System.lineSeparator()
                        + "1. === Exit Program ===" + System.lineSeparator()));
    }
}