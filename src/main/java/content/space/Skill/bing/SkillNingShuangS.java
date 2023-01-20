package content.space.Skill.bing;

import content.Pet;
import content.space.SpaceOneInterface;

public class SkillNingShuangS implements SpaceOneInterface {

    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        if(count==1)return;
        resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.18);
    }

    @Override
    public void beMove(Pet resPet, Pet dstPet) {

    }
}
