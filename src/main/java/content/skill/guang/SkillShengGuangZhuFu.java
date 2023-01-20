package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShengGuangZhuFu implements SkillInterface {
    String name = "圣光祝福";
    String animal = "magic";
    boolean physical = false;
    int speed = 10;
    String type = "光";
    int power = 0;
    int pp = 6;
    String description = "使用光明的力量祝福我方全体，增加我方全体的伤害值，(增幅与亲密度相关)[取消]。若自身生命值低于25%，会重置并锁定全场强化。先手+2";
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
    public boolean doSelf() {
        return true;
    }

    @Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        Pet[] petList = BaseFun.getPetList(resPet);
        for (int i = 0; i < petList.length; i++) {
            if(petList[i]==null)break;
            if(petList[i].getHp()<=0)continue;
            petList[i].setAttLimit(150,1000);
        }

        if(resPet.getHp()< resPet.getBaseHp()*0.25){
            for (int i = 0; i < petList.length; i++) {
                if(petList[i]==null)break;
                if(petList[i].getHp()<=0)continue;
                petList[i].clearBuff();
                petList[i].setLockBuffCount(1000);
            }
            petList = BaseFun.getPetList(dstPet);
            for (int i = 0; i < petList.length; i++) {
                if(petList[i]==null)break;
                if(petList[i].getHp()<=0)continue;
                petList[i].clearBuff();
                petList[i].setLockBuffCount(1000);
            }

        }

    }
}
