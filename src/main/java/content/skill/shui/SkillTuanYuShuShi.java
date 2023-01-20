package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillTuanYuShuShi implements SkillInterface {
    String name = "湍玉漱石";
    String animal = "att1";
    boolean physical = false;
    int speed = 10;
    String type = "水";
    int power = 100;
    int pp = 10;
    String description = "片云飞瀑，裁素悬秋。降低对方两级魔抗。降低魔抗失败则追加伤害，回复我方全体精力。先手+2";
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

        if (!dstPet.statusChange(3,-2)) {

            BaseFun.att(resPet,dstPet,power+50,physical,type);
            BaseFun.otherPersRecover(BaseFun.getPetList(resPet),null,false,80);
        }else {
            BaseFun.att(resPet, dstPet, power, physical, type);
        }
    }
}
