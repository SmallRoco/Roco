package content.space.Skill.huo;

import content.Pet;
import content.space.SpaceOneInterface;

public class SkillNuQiZhongShaoS implements SpaceOneInterface {
    @Override
    public void pass(Pet resPet, Pet dstPet, int count) {
        if (count == 5) {
            resPet.setBoom(resPet.getBaseBoom() - 150);
        }
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet) {
        resPet.setBoom(resPet.getBaseBoom() - 150);
    }
}