package content.space.Skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillBingDongS implements SpaceOneInterface {
    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {

        if(count==1)return;
        MainFrame.playBond(resPet,"magic","冰晶结界",resPet==MainFrame.pet1);
        BaseFun.att(resPet,dstPet,15,false,"水");
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {

    }
}
