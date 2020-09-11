package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедщих пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> combineName = t -> t.getName().contains(key);
        Predicate<Person> combineSName = t -> t.getSurname().contains(key);
        Predicate<Person> combineAddress = t -> t.getAddress().contains(key);
        Predicate<Person> combinePhone = t -> t.getPhone().contains(key);

        Predicate<Person> combine = person -> combineName.or(combineSName.
                or(combineAddress.or(combinePhone))).test(person);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person: persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
