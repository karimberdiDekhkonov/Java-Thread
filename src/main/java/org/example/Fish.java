package org.example;

import java.util.Random;

public class Fish extends Thread{
    private static final int MAX_LIFETIME = 12;
    private static final Random random = new Random();

    private final String name;
    private final Gender gender;
    private int age = 0;
    private final int lifetime;



    public Fish(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.lifetime = random.nextInt(MAX_LIFETIME)+1;
    }

    public void run(){
        System.out.println(name + " akvariumga qo'shildi");
        for (int i = 0; i < lifetime; i++) {
            age++;
            System.out.println(name + " akvaiumda suzmoqda va yoshi " + age + " da");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(name + " bilan xatolik yuz berdi");
                return;
            }
        }

        System.out.println(name + " vafot etdi");
    }

    public String getFishName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", lifetime=" + lifetime +
                '}';
    }
}

