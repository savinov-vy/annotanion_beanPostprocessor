package ru.savinov.springcontext;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        System.out.println(context);
        FightClubImpl fightClub = context.getBean(FightClubImpl.class);
//        System.out.println(fightClub);
        fightClub.fight();
    }
}
