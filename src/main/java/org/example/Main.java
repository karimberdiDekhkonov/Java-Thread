package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AquariumService aquarium = new AquariumService(new Random().nextInt(11) + 5, new Random().nextInt(11) + 5);
        aquarium.startAquarium();
    }
}