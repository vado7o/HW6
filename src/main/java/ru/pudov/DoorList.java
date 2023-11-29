package ru.pudov;

import java.util.*;

public class DoorList {
    private final List<Door> list;

    public DoorList () {
        List<Door> list = new ArrayList<>(Arrays.asList(new Door(true), new Door(false),
                new Door(false)));
        Collections.shuffle(list);
        this.list = list;
    }

    public int removeOne(Integer input) {
        List<Integer> indexies = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            if (i != input - 1 && !list.get(i).car) indexies.add(i);
        }
        Collections.shuffle(indexies);
        return indexies.get(0);
    }

    public List<Door> getList() {
        return list;
    }
}
