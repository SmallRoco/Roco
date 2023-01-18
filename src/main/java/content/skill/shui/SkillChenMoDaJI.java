package content.skill.shui;

import content.Pet;
import content.SkillInterface;
import content.BaseFun;

public class SkillChenMoDaJI implements SkillInterface {

    String name = "沉没打击";
    String animal = "att1";
    boolean physical = true;
    int speed = 9;
    //属性
    String type = "水";
    //威力
    int power = 90;
    //pp
    int pp = 15;
    //描述
    String description = "沉重的一击，命中后降低对方双防。先手+1";

    @Override
    public String getAnimal() {
        return animal;
    }
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        //提升双抗
        dstPet.statusChange(2,-1);
        dstPet.statusChange(3,-1);
        //造成伤害
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean getPhysical() {
        return physical;
    }
    @Override
    public int getSpeed() {
        return speed;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public int getPower() {
        return power;
    }
    @Override
    public int getPp() {
        return pp;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
