package content.skill.guang;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHuaJingChangMing implements SkillInterface {
    String name = "华景长明";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "光";
    int power = 0;
    int pp = 1;
    String description = "中天曜灵，八极垂光。激发天使血脉进入初阳形态并获得短暂庇护，回复满自身精力和上一次使用技能的PP，永久免疫异常状态。先手+7";
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

        resPet.addMark("初阳",1000);
        resPet.setNoDamage(1);
        resPet.setHp(resPet.getBaseHp());
        int i = MainFrame.useSkillOld[BaseFun.getIndexPawn(resPet)];
        resPet.recoverAllPpByIndex(i);
        resPet.setNoexception(1000);


    }
}
