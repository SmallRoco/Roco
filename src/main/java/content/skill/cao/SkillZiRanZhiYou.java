package content.skill.cao;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillZiRanZhiYou implements SkillInterface {
    String name = "自然之友";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "草";
    int power = 0;
    int pp = 4;
    String description = "亲近自然，净化自身，6回合免疫异常状态及负面强化，召唤芬芳环境6回合。回复自身生命值，根据场下的存活或者阵亡的草系队友的数目为队友及自身提供保护。先手+7";
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
        resPet.setNoexception(6);
        resPet.setNoDeBuff(6);
        MainFrame.setEnvironment("芬芳",6);
        resPet.setHp(resPet.getHp()+150);
        Pet[] pets1 = BaseFun.getPetList(resPet);
        int count = BaseFun.getPetsCountByType(pets1,"草");

        for (int i = 0; i < pets1.length; i++) {
            if(pets1[i]!=null){
                pets1[i].addBigProtection(count*50);
            }
        }
    }
}
