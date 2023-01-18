package content.skill.emo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillMoNvZhiWen implements SkillInterface {
    String name = "魔女之吻";
    String animal = "magic";
    boolean physical = false;
    int speed = 11;
    String type = "恶魔";
    int power = 0;
    int pp = 20;
    String description = "消耗自身魔力，从对方身上吸取精魄。先手+3";
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

@Override public String getTuoshou(){return "超级吸取";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        dstPet.setHp(resPet.getHp()-200);
        resPet.setHp(resPet.getHp()+200);

    }
}
