package content.space.Skill.shui;

import UI.MainFrame;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillShuiZhiHuDunS implements SpaceOneInterface {

    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        MainFrame.playBond(resPet,"magic","加强防御",resPet==MainFrame.pet1);

        resPet.addBigProtection(60);
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {

    }
}
