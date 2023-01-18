package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillEnYuan implements SkillInterface {
    String name = "恩怨";
    String animal = "att1";
    boolean physical = true;
    int speed = 9;
    String type = "水";
    int power = 75;
    int pp = 10;
    String description = "先手时，根据对方生命值追加伤害；后手时，根据自身损失生命值追加。先手+1";
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

    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(MainFrame.first){
            BaseFun.att(resPet,dstPet,power+(Math.max(dstPet.getHp()/10,125)),physical,type);
        }else {
            BaseFun.att(resPet, dstPet, power+(Math.max((resPet.getBaseHp()-resPet.getHp())/10,125)), physical, type);
        }
    }
}
