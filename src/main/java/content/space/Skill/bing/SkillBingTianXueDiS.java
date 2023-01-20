package content.space.Skill.bing;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillBingTianXueDiS implements SpaceOneInterface {


    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        if(count==1)return;

        MainFrame.playBond(resPet,"magic","冰天雪地",resPet==MainFrame.pet1);
        if(!resPet.getType().contains("冰")){
            resPet.setHp(resPet.getHp()-resPet.getBaseHp()/5.0);
        }
        if(!dstPet.getType().contains("冰")){
            dstPet.setHp(dstPet.getHp()-dstPet.getBaseHp()/5.0);
        }

    }

    @Override
    public void beMove(Pet resPet, Pet dstPet) {

    }
}
