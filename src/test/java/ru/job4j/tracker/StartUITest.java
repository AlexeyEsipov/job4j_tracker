package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            Input in = new StubInput(new String[] {"0", "Item name", "1"});
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction(out));
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            assertThat(tracker.findAll().get(0).getName(), is("Item name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplaceItem() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            Item item = tracker.add(new Item("Replaced item"));
            String replacedName = "New item name";
            Input in = new StubInput(
                    new String[] {"0", String.valueOf(item.getId()), "New item name", "1"}
            );
            List<UserAction> actions = new ArrayList<>();
            actions.add(new ReplaceItem(out));
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            Item byId = tracker.findById(item.getId());
            assertThat(byId.getName(), is(replacedName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteItem() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            Item item = tracker.add(new Item("Deleted item"));
            Input in = new StubInput(
                    new String[] {"0", String.valueOf(item.getId()), "1"}
            );
            List<UserAction> actions = new ArrayList<>();
            actions.add(new DeleteItem(out));
            actions.add(new ExitProgram(out));
            Item expected = new Item(null);
            new StartUI(out).init(in, tracker, actions);
            assertThat(tracker.findById(item.getId()), is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenExit() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );

        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitProgram(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                        + "0. === Exit Program ===" + System.lineSeparator()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @Test
    public void whenFindById() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            int id = tracker.add(new Item("New item")).getId();
            Input in = new StubInput(
                new String[]{"0", String.valueOf(id), "1"}
            );
            List<UserAction> actions = new ArrayList<>();
            actions.add(new FindItemById(out));
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()
                  + "ID: " + id + ", Name: New item" + System.lineSeparator()
                  + "Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenNotFindById() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            tracker.add(new Item("New item"));
            Input in = new StubInput(
                    new String[]{"0", "101", "1"}
            );
            List<UserAction> actions = new ArrayList<>();
            actions.add(new FindItemById(out));
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()
                  + "Объект с требуемым ID не найден" + System.lineSeparator()
                  + "Menu." + System.lineSeparator()
                  + "0. === Find item by Id ===" + System.lineSeparator()
                  + "1. === Exit Program ===" + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindByName() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            Item item = tracker.add(new Item("New item"));
            int id = item.getId();
            Input in = new StubInput(new String[]{"0", item.getName(), "1"});
            List<UserAction> actions = new ArrayList<>();
            actions.add(new FindItemsByName(out));
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()
                    + "ID: " + id
                    + ", Name: New item" + System.lineSeparator()
                    + "Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    @Test
    public void whenNotFindByName() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            tracker.add(new Item("New item"));
            Input in = new StubInput(new String[]{"0", "Name", "1"});
            List<UserAction> actions = new ArrayList<>();
            actions.add(new FindItemsByName(out));
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            assertThat(out.toString(),
                is("Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()
                    + "Объекты с требуемым именем не найдены" + System.lineSeparator()
                    + "Menu." + System.lineSeparator()
                    + "0. === Find items by name ===" + System.lineSeparator()
                    + "1. === Exit Program ===" + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindAll() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            tracker.add(new Item("First item"));
            tracker.add(new Item("Second item"));
            Input in = new StubInput(new String[]{"0", "1"});
            List<UserAction> actions = new ArrayList<>();
            actions.add(new ShowAllItems(out));
            actions.add(new ExitProgram(out));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenInvalidExit() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            Output out = new StubOutput();
            Input in = new StubInput(new String[]{"-1", "0"});
            List<UserAction> actions = new ArrayList<>();
            actions.add(new ExitProgram(out));
            new StartUI(out).init(in, tracker, actions);
            assertThat(out.toString(), is(
                String.format(
                    "Menu.%n"
                    + "0. === Exit Program ===%n"
                    + "Wrong input, you can select: 0 .. 0%n"
                    + "Menu.%n"
                    + "0. === Exit Program ===%n")
                ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}