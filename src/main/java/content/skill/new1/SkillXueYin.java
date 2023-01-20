package content.skill.new1;

import Config.ConfigFile;
import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.bing.SkillXueYinS;
import content.space.SpaceBase;

public class SkillXueYin implements SkillInterface {
    String name = "雪隐";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "冰";
    int power = 0;
    int pp = 4;
    String description = "召唤冰雹天气袭击场上对手，五回合内对场上非水、冰、神水系宠物造成伤害，短时间内降低对方的攻击力。先手+4";
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

    @Override public String getTuoshou(){return "冰天雪地";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        if(!resPet.getType().contains("冰")&&!resPet.getType().contains("水")){
            resPet.setHp(resPet.getHp()-resPet.getBaseHp()/4.0);
        }
        if(!dstPet.getType().contains("冰")&&!dstPet.getType().contains("水")){
            dstPet.setHp(dstPet.getHp()-dstPet.getBaseHp()/4.0);
        }

        resPet.addSpaces(name,new SpaceBase(5,resPet==MainFrame.pet1,new SkillXueYinS()));

        int duanShiJian = ConfigFile.get("短时间");
        dstPet.setAttLimit(60,duanShiJian);

    }
}
