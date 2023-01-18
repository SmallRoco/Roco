package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShengTengZhiMie implements SkillInterface {
    String name = "圣藤之灭";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "草";
    int power = 120;
    int pp = 5;
    String description = "对对方造成伤害，如果对方处于睡眠状态，则自身回复等同于本次造成伤害的HP。先手+1";
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
        if(dstPet.getStatus("睡眠")!=null){
            int damage = BaseFun.att(resPet, dstPet, power, physical, type);
            resPet.setHp(resPet.getHp()+damage);
            return;

        }
        BaseFun.att(resPet, dstPet, power, physical, type);


    }
}
