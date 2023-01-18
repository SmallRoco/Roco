package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShiShuiXiXue implements SkillInterface {
    String name = "嗜睡吸血";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 5;
    String description = "恢复40点HP，并有概率让对方进入睡眠状态。";
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
        resPet.setHp(resPet.getHp()+40);
        if(BaseFun.is(75)){
            dstPet.addStatus("睡眠");
        }
    }
}
