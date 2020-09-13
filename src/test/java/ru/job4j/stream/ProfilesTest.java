package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void collectAddress() {
        Profiles profiles = new Profiles();
        List<Profile> listProfile = new ArrayList<>();
        Address address1 = new Address("city2", "street2", 200, 20);
        listProfile.add(new Profile("Ivan", address1));
        Address address2 = new Address("city1", "street1", 100, 10);
        listProfile.add(new Profile("Alex", address2));
        List<Address> addresses = profiles.collect(listProfile);
        assertThat(addresses.get(0).getCity(), is("city1"));
        assertEquals(addresses.get(1), address1);
    }
}