package content.skill.guang;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillRouRuCaiHong implements SkillInterface {
    String name = "柔如彩虹";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "光";
    int power = 0;
    int pp = 4;
    String description = "温柔的微笑使对方无法抵挡。重置场上天气或环境。4回合内自身受到的伤害大幅度减免。若成功清除则删除对方全体2PP，反之则回复自身其它技能1PP。先手+4";
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

        boolean b = MainFrame.setEnvironment("", 0);
        resPet.setDownDamage(50,4);
        if(b){
            BaseFun.allPpChange(dstPet,-2);
        }else {
            BaseFun.changeOtherSkillPp(resPet,name,1);
        }

    }
}
