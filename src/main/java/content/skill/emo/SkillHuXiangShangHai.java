package content.skill.emo;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuXiangShangHai implements SkillInterface {
    String name = "互相伤害";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "恶魔";
    int power = 0;
    int pp = 10;
    String description = "根据自身已损失生命值和异常状态，对敌方造成重大伤害。";
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

        int petDeBuffCount =resPet.getStatusSize()*10;


        BaseFun.att(resPet,dstPet,petDeBuffCount+Math.max((resPet.getBaseHp()-resPet.getHp())/4,120),physical,type);

    }
}
