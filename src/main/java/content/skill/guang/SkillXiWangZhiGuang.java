package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillXiWangZhiGuang implements SkillInterface {
    String name = "希望之光";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "光";
    int power = 0;
    int pp = 4;
    String description = "不要放弃，总有希望。带来希望的光明能驱散一切黑暗，被动免疫诅咒、恐惧，主动使用净化自身，免疫异常及负面5回合，回复己方全体，回复量与魔抗有关。先手+7";
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

@Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        resPet.clearSelf();
        resPet.setNoexception(5);
        resPet.setNoDeBuff(5);
        resPet.setHp(resPet.getBaseHp());
        Pet[] petList = BaseFun.getPetList(resPet);
        for (int i = 0; i < petList.length; i++) {
            if(petList[i]==null)return;
            if(petList[i].getHp()<=0)continue;
            petList[i].setHp(petList[i].getHp()+Math.max(resPet.getMagicDefense()/4,400));
        }

    }


    @Override
    public void init(Pet resPet) {
        resPet.addMark("诅咒免疫",1000);
        resPet.addMark("恐惧免疫",1000);

    }
}
