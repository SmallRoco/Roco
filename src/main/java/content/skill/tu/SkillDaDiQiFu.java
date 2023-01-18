package content.skill.tu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillDaDiQiFu implements SkillInterface {
    String name = "大地祈福";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "土";
    int power = 90;
    int pp = 10;
    String description = "一定机率让对方魔抗等级下降。";
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

@Override public String getTuoshou(){return "火眼金睛";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        //System.out.println("大地祈福");
        if(BaseFun.is(50)){dstPet.statusChange(3,-1);}
        else if(BaseFun.is(70)){dstPet.statusChange(3,-2);}


        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
