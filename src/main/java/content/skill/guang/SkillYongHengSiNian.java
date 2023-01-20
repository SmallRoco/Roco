package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillYongHengSiNian implements SkillInterface {
    String name = "永恒思念";
    String animal = "att1";
    boolean physical = false;
    int speed = 11;
    String type = "光";
    int power = 120;
    int pp = 10;
    String description = "永恒的思念绵长无尽，根据魔抗追加本技能效果。先手+3";
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

        BaseFun.att(resPet,dstPet,power+Math.max(resPet.getMagicDefense()/10,80),physical,type);
    }
}
