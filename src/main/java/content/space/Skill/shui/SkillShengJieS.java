package content.space.Skill.shui;

import content.Pet;
import content.space.SpaceOneInterface;

public class SkillShengJieS implements SpaceOneInterface {

    int i = 0;

    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        if(count==1&&resPet.getAppendStatus()[5]<6);
        resPet.statusChange(5,1);
        i = 1;
    }

    @Override
    public void beMove(Pet resPet, Pet dstPet) {
        if(i==1) {
            resPet.statusChange(5, 1);
        }
    }
}
