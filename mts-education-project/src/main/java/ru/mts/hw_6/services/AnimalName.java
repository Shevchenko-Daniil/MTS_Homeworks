package ru.mts.hw_6.services;

public enum AnimalName {
    BARSIK ("Барсик"),
    KUZYA("Кузя"),
    LEO("Лео"),
    REKS("Рекс"),
    GOSPAR("Госпар"),
    ROMUL("Ромул");
    private String title;

    AnimalName(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }


}
