package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AquariumService extends Thread{
    private final int numMaleFish;
    private final int numFemaleFish;
    private final List<Fish> fishList;
    private final String[] maleNames = {"Jack", "Tom", "Kevin", "Harry", "Liam", "Noah", "Oliver", "Elijah", "James", "William", "Benjamin", "Lucas", "Henry", "Theodore"};
    private final String[] femaleNames = {"Olivia", "Emma", "Charlotte", "Amelia", "Ava", "Sophia", "Isabella", "Mia", "July", "Monika", "Anna"};
    private final Random rand;

    private final Fish shark = new Fish("Akula", Gender.MALE);

    public AquariumService(int numMaleFish, int numFemaleFish) {
        //Baliqlar sonini o'rnatish jarayoni

        System.out.println("Erkak baliqlar soni: " + numMaleFish);
        System.out.println("Ayol baliqlar soni: " + numFemaleFish);
        this.numMaleFish = numMaleFish;
        this.numFemaleFish = numFemaleFish;
        this.fishList = new ArrayList<>();
        this.rand = new Random();
    }

    public void startAquarium() {
        // Erkak va Ayol baliqlarni turli hil lokatsiya(index)larda yaratish jarayoni

        fishList.add(shark);
        shark.start();
        if (numMaleFish>numFemaleFish){
            for (int i = 0; i < numMaleFish; i++) {
                Fish fish = new Fish(maleNames[rand.nextInt(maleNames.length-1)], Gender.MALE);
                fishList.add(fish);
                fish.start();

                if (numFemaleFish > i){
                    // Ayol baliqlarni yaratish jarayoni
                    fish = new Fish(femaleNames[rand.nextInt(femaleNames.length-1)], Gender.FEMALE);
                    fishList.add(fish);
                    fish.start();
                }
            }
        }else{
            for (int i = 0; i < numFemaleFish; i++) {
                Fish fish = new Fish(femaleNames[rand.nextInt(femaleNames.length-1)], Gender.FEMALE);
                fishList.add(fish);
                fish.start();

                if (numMaleFish > i){
                    // Ayol baliqlarni yaratish jarayoni
                    fish = new Fish(maleNames[rand.nextInt(maleNames.length-1)], Gender.MALE);
                    fishList.add(fish);
                    fish.start();
                }
            }
        }

        Loop();
    }

    public void Loop(){
        //Baliqlarni ko'payish va Akulani doimiy ovlash jarayoni

        while (true) {
            breedFish(getRandomFish());
            sharkIsHunting();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("xatolik yuz berdi");
                return;
            }
        }
    }

    private Fish getRandomFish() {
        //Random baliq olish jarayoni

        int index = rand.nextInt(fishList.size());
        return fishList.get(index);
    }

    private Fish getFishByLocation(Fish inputFish) {
        // Lokatsiya bo'yicha baliq olish jarayoni

        int index = fishList.indexOf(inputFish);
        Fish result = fishList.get(index);
        if (index>fishList.size()-1){
            return inputFish;
        }
        else if (index == 0){
            return fishList.get(index + 1);
        }
        if (result.getGender()==inputFish.getGender()){
            return fishList.get(index - 1);
        }
        return result;
    }

    public void breedFish(Fish fish) {
        // Baliqlarni ko'payish jarayoni

        Fish otherFish = getFishByLocation(fish);

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

    public void sharkIsHunting(){
        //Akulani ovlash jarayoni

        Fish fish = getFishByLocation(shark);
        System.out.println(fish.getFishName() + " Akula tomonidan ovlandi");
        fishList.remove(fish);
    }
}
