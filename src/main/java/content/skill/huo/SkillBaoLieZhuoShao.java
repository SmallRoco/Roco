package content.skill.huo;

import Config.ConfigFile;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBaoLieZhuoShao implements SkillInterface {
    String name = "爆裂灼烧";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "火";
    int power = 90;
    int pp = 10;
    String description = "对处于烧伤状态的宠物更容易造成暴击。";
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
        if(ConfigFile.BOOM_START&&dstPet.getStatus("烧伤")!=null&&(BaseFun.is((resPet.getBoom())/15))){
            BaseFun.boomAtt(resPet,dstPet,power,physical,type);
            return;
        }
        BaseFun.att(resPet,dstPet,power,physical,type);

    }
}
