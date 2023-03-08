package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AquariumService {
    private final int numMaleFish;
    private final int numFemaleFish;
    private final List<Fish> fishList;
    private final String[] maleNames = {"Jack", "Tom", "Kevin", "Harry", "Liam", "Noah", "Oliver", "Elijah", "James", "William", "Benjamin", "Lucas", "Henry", "Theodore"};
    private final String[] femaleNames = {"Olivia", "Emma", "Charlotte", "Amelia", "Ava", "Sophia", "Isabella", "Mia", "July", "Monika", "Anna"};
    private final Random rand;

    public AquariumService(int numMaleFish, int numFemaleFish) {
        System.out.println("Erkak baliqlar soni: " + numMaleFish);
        System.out.println("Ayol baliqlar soni: " + numFemaleFish);
        this.numMaleFish = numMaleFish;
        this.numFemaleFish = numFemaleFish;
        this.fishList = new ArrayList<>();
        this.rand = new Random();
    }

    public void startAquarium() {
        // Erkak baliqlarni yaratish jarayoni
        for (int i = 0; i < numMaleFish; i++) {
            Fish fish = new Fish(maleNames[rand.nextInt(maleNames.length-1)], Gender.MALE);
            fishList.add(fish);
            fish.start();
        }

        // Ayol baliqlarni yaratish jarayoni
        for (int i = 0; i < numFemaleFish; i++) {
            Fish fish = new Fish(femaleNames[rand.nextInt(femaleNames.length-1)], Gender.FEMALE);
            fishList.add(fish);
            fish.start();
        }

//        while (true) {
//            breedFish(getRandomFish());
//        }

        for (int i = 0; i < rand.nextInt(10) + 10; i++) {
            breedFish(getRandomFish());
        }
    }

    private Fish getRandomFish() {
        // Random baliq olish jarayoni
        int index = rand.nextInt(fishList.size());
        return fishList.get(index);
    }

    public synchronized void breedFish(Fish fish) {
        // Baliqlarni ko'payish jarayoni
        for (Fish otherFish : fishList) {
            if (otherFish != fish && otherFish.getGender() != fish.getGender()) {
                // Jinslarni tekshirish jarayoni
                Gender gender = rand.nextBoolean() ? Gender.MALE : Gender.FEMALE;
                Fish child = new Fish(gender==Gender.MALE?maleNames[rand.nextInt(maleNames.length)]:femaleNames[rand.nextInt(femaleNames.length)], gender);
                fishList.add(child);
                child.start();
                System.out.println(fish.getFishName() + " va " + otherFish.getFishName() + " => " + child.getFishName() + " ni dunyoga keltirdi");
                return;
            }
        }
    }
}
