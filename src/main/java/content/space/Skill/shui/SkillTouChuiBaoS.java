package content.space.Skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceInterface;
import content.space.SpaceOneInterface;

public class SkillTouChuiBaoS implements SpaceOneInterface {

    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        if(count==2){
            MainFrame.pawnRun(resPet,"att1");
            BaseFun.att(resPet,dstPet,50,true,"水");
        }
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {

    }
}
