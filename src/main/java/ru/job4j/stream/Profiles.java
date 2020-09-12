package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    private List<Profile> listProfile = new ArrayList<>();

    public void add(Profile profile) {
        this.listProfile.add(profile);
    }

    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }
}
