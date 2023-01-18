package content.space.Skill.emo;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.space.SpaceOneInterface;

public class SkillHuanYueHeiZeS implements SpaceOneInterface {

    Pet pet;

    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {

        if(count==1){
            pet = dstPet;
        }
        if(pet!=dstPet){
            return;
        }

        int damage = resPet.getMagicAttack()/10+dstPet.getBaseHp()/20;

        dstPet.setHp(dstPet.getHp()-damage);
        resPet.setHp(resPet.getHp()+damage);


    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {

    }
}
