package content.space.Skill.shui;

import UI.MainFrame;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillLvSeHuanJingS implements SpaceOneInterface {
    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {

        if(count==1)return;
        MainFrame.playBond(resPet,"magic","光明增益",resPet==MainFrame.pet1);

        resPet.setHp(resPet.getHp()+60);
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {

    }
}
