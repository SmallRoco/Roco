package content.skill.tu;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillYaoYangSong implements SkillInterface {
    String name = "耀阳颂";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "土";
    int power = 0;
    int pp = 1;
    String description = "耀阳现，万物生。净化自身并免疫后手伤害，回复自身精力和其他技能PP。免疫负面2回合，永久免疫异常状态。先手+7";
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
    @Override public boolean doSelf(){return true;}

@Override public String getTuoshou(){return "大地宽恕";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
        resPet.setNoDamage(1);
        resPet.setHp((int)(resPet.getHp()+resPet.getBaseHp()*0.25));
        BaseFun.changeOtherSkillPp(resPet,name,1);
        resPet.setNoDeBuff(2);
        resPet.setNoexception(1000);
    }
}
