package content.space.Skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillYanMeiS implements SpaceOneInterface {
    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {

        if(count==1)return;
        MainFrame.playBond(resPet,"magic","冰天雪地",resPet==MainFrame.pet1);




        BaseFun.trueDamage(resPet,dstPet,40);
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {

    }
}
