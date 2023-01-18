package content.skill.emo;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillChiYueMeiYan implements SkillInterface {
    String name = "赤月魅焰";
    String animal = "magic";
    boolean physical = false;
    int speed = 10;
    String type = "恶魔";
    int power = 0;
    int pp = 8;
    String description = "利用赤月魔杖幻化出魅焰，驱散自身全体的负面强化及异常状态以削减敌方场上宠物的PP值，若引入血月之力则效果加倍。先手+2";
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

@Override public String getTuoshou(){return "魔焰瞬击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
        if(MainFrame.getEnvironment().equals("血月")) {
            BaseFun.allPpChange(dstPet, -2);
        }else {
            BaseFun.allPpChange(dstPet, -1);
        }
    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
    }
}
