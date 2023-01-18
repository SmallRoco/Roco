package content.space.Skill.guang;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillHongJingZhuoYanS implements SpaceOneInterface {
    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {

        MainFrame.pawnRun(resPet,"att1");
        BaseFun.att(resPet,dstPet,55,true,"光");
        if(resPet.getMark("初阳")!=null){
            dstPet.changePp(1,-1);
        }else {
            dstPet.clearGreatBuff();
        }


    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {
        resPet.setBoom(resPet.getBaseBoom() - 150);
    }
}
