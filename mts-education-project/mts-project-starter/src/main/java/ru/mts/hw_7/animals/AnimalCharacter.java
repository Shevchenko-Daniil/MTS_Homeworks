package ru.mts.hw_7.animals;

public enum AnimalCharacter {
    AGRESSIVE("Агрессивный"),
    AFFECTIONATE("Ласковый"),
    HARMFUL("Вредный"),
    VORACIOUS("Прожорливый"),
    REVENGEFUL("Мстительный"),
    PLAYFUL("Игривый");
    private String title;

    AnimalCharacter(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}
