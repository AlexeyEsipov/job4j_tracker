package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerTest {

    @Test
    public void whenReplace() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            int id = tracker.add(new Item("Bug")).getId();
            Item bugWithDesc = new Item("Bug with description");
            tracker.add(bugWithDesc);
            tracker.replace(id, bugWithDesc);
            assertThat(tracker.findById(id).getName(), is("Bug with description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDelete() {
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            int id = tracker.add(new Item("Bug")).getId();
            Item expected = new Item(null);
            tracker.delete(id);
            assertThat(tracker.findById(id), is(expected));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}