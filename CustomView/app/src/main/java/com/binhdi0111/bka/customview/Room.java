package com.binhdi0111.bka.customview;

public class Room {
    public String name;
    public boolean visibility;

    public Room(String name) {
        this.name = name;
        this.visibility =false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
