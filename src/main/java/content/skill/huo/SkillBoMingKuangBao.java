package content.skill.huo;

import Config.ConfigFile;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBoMingKuangBao implements SkillInterface {
    String name = "搏命狂暴";
    String animal = "att1";
    boolean physical = true;
    int speed = 8;
    String type = "火";
    int power = 80;
    int pp = 10;
    String description = "降低自身的防御等级，大幅度提升本回合的暴击概率。";
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


        if(resPet.statusChange(0,-2)&&ConfigFile.BOOM_START&&dstPet.getStatus("烧伤")!=null&&(BaseFun.is((resPet.getBoom()+400)/15))){
            BaseFun.boomAtt(resPet,dstPet,power,physical,type);
            return;
        }
        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
