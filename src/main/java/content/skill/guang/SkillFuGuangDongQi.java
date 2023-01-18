package content.skill.guang;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillFuGuangDongQi implements SkillInterface {
    String name = "扶光东起";
    String animal = "att1";
    boolean physical = true;
    int speed = 12;
    String type = "光";
    int power = 100;
    int pp = 5;
    String description = "薄岚消解，昼起初阳。沐浴晨曦晓光净化自身回复精力，倒置负面强化，暂时免疫负面与异常，并减免自身所受的过高伤害。晨曦形态时删除对方PP值，初阳形态则造成高额伤害先手+4";
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
        resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.35);
        BaseFun.inverteDeBuff(resPet);
        resPet.clearSelf();
        resPet.setNoexception(2);
        resPet.setNoDeBuff(2);
        resPet.setMaxDamaged((int) (resPet.getBaseHp()*0.4),2);

        if(resPet.getMark("初阳")==null){
            dstPet.changePp(3,-1);
        }else {
            BaseFun.att(resPet,dstPet, (int) (power*1.5),physical,type);
            return;
        }
        BaseFun.att(resPet,dstPet,power,physical,type);

    }
}
