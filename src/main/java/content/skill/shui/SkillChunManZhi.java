package content.skill.shui;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillChunManZhi implements SkillInterface {
    String name = "春满枝";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 0;
    int pp = 6;
    String description = "春意盎然，朝气满枝。删除全体技能1PP，并根据福气额外删除PP。召唤水中月影令对方陷入催眠幻象，失败会清除对方强化并追加固伤，成功则强化自身魔攻与速度。驱逐已经被催眠的对手。";
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

@Override public String getTuoshou(){return "催眠粉";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.changeOtherSkillPp(dstPet,"",-1);
        Integer fuQI = resPet.getMark("福气");

        if(fuQI!=null) {
            fuQI-=1000;
            resPet.getPps()[fuQI%4] -= fuQI/4;
        }

        if(!dstPet.addStatus("睡眠")){
            BaseFun.trueDamage(resPet,dstPet,60);
            if(BaseFun.deleteOtherGreatBuff(dstPet,0)){
            }
        }else {
            resPet.statusChange(1, 1);
            resPet.statusChange(4, 1);
        }

        if(dstPet.getStatus("睡眠")!=null){
            BaseFun.expelPet(dstPet);
        }
    }
}
