package content.skill.Long;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillLongZhiChuanCheng implements SkillInterface {
    String name = "龙之传承";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "龙";
    int power = 0;
    int pp = 3;
    String description = "解除异常状态并暂时免疫异常状态与负面强化，立即强化自身并恢复部分生命。先手+4";
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
        resPet.clearSelf();
        resPet.setNoDeBuff(2);
        resPet.setNoexception(2);
        resPet.statusChange(0,1);
        resPet.statusChange(4,1);
        resPet.setHp(resPet.getHp()+Math.max(600,resPet.getBaseHp()*0.7));
    }
}
