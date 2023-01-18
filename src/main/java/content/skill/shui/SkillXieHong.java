package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillXieHong implements SkillInterface {
    String name = "泄洪";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 65;
    int pp = 20;
    String description = "清除对方额外的强化等级并追加伤害，免疫清除强化的对手会受到额外伤害。先手+1";
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

        int petGreatBuffCount = BaseFun.getPetGreatBuffCount(dstPet);
        if(BaseFun.deleteOtherGreatBuff(dstPet,0)){
            BaseFun.att(resPet,dstPet,power*petGreatBuffCount+10,physical,type);
        }else {
            BaseFun.att(resPet, dstPet, power, physical, type);
            BaseFun.trueDamage(resPet,dstPet,80);
        }
    }
}
