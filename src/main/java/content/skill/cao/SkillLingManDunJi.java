package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLingManDunJi implements SkillInterface {
    String name = "灵蔓盾击";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 10;
    String description = "灵蔓盾击的PP值越少，造成的伤害越高。";
    @Override
    public String getAnimal() {
        return animal;
    }
    @Override public String getName() {
        return name;
    }
    @Override public boolean getPhysical() {
        return physical;
    }
    @Override public int getSpeed() {
        return speed;
    }
    @Override public String getType() {
        return type;
    }
    @Override public int getPower() {
        return power;
    }
    @Override public int getPp() {
        return pp;
    }
    @Override public String getDescription() { return description; }
    @Override public void setDescription(String description) { this.description = description; }

@Override public String getTuoshou(){return "灵蔓盾击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet, dstPet, 60+(10-resPet.isHasThisSkill(name))*10, physical, type);
    }
}
