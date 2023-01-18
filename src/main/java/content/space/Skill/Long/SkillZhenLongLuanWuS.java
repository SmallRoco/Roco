package content.space.Skill.Long;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillZhenLongLuanWuS implements SpaceOneInterface {


    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        if(count==1)return;
        MainFrame.pawnRun(resPet,"att1");
        BaseFun.att(resPet,dstPet,60,true,"龙");
        if(resPet.getMark("苍龙庇佑")!=null){
            resPet.setHp(resPet.getHp()+75);
        }
    }

    @Override
    public void beMove(Pet resPet, Pet dstPet) {

    }
}
