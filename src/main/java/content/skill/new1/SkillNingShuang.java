package content.skill.new1;

import Config.ConfigFile;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillNingShuang implements SkillInterface {
    String name = "凝霜";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "冰";
    int power = 0;
    int pp = 4;
    String description = "凝聚冰霜保护自身，暂时免疫异常状态，五回合内持续恢复生命，降低自身受到的伤害。先手+7";
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

@Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        int zanShi = ConfigFile.get("暂时");
        resPet.setNoexception(zanShi);
        resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.18);
        resPet.setDownDamage(60,5);

    }
}
