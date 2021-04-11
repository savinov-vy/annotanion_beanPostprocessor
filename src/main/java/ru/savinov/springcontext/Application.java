package ru.savinov.springcontext;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
   //     FightClubImpl fightClub = context.getBean(FightClubImpl.class);
        FightClub fightClub = context.getBean(FightClub.class);
        fightClub.fight();
    }
}
