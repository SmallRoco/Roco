package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBingXiShuiLing implements SkillInterface {
    String name = "冰夕水灵";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 0;
    int pp = 8;
    String description = "冰冻对方，被免疫则回复其它技能PP。任意天气/环境下赋予自身护盾。若无天气/环境则给对方造成一段固伤。先手+1";
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

@Override public String getTuoshou(){return null;};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(!dstPet.addStatus("冰冻")){
            BaseFun.changeOtherSkillPp(resPet,name,1);
        }
        if(MainFrame.getEnvironment()!=null){
            resPet.addBigProtection((int) (resPet.getBaseHp()*0.3));
        }else {
            BaseFun.trueDamage(resPet,dstPet,80);
        }
    }
}
