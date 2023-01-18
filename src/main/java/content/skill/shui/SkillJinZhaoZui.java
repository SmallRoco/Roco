package content.skill.shui;

import content.Pet;
import content.SkillInterface;
import content.BaseFun;

public class SkillJinZhaoZui implements SkillInterface {


    String name = "今朝醉";
    String animal = "att1";
    boolean physical = true;
    int speed = 10;
    //属性
    String type = "水";
    //威力
    int power = 100;
    //pp
    int pp = 8;
    //描述
    String description = "今朝有酒今朝醉，明日愁来明日愁。提升1级物攻。先手+2，命中后会随机攻击敌方场下队友，miss则降低敌方双防1级。";

    @Override
    public String getAnimal() {
        return animal;
    }
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        //提升一级物攻
        resPet.statusChange(0,1);
        //造成伤害
        BaseFun.att(resPet,dstPet,power,physical,type);

        boolean b = BaseFun.randomAttOtherPet(resPet, dstPet, power, physical, type, BaseFun.getPetList(dstPet));

    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        dstPet.statusChange(2,-1);
        dstPet.statusChange(3,-1);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean getPhysical() {
        return physical;
    }
    @Override
    public int getSpeed() {
        return speed;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public int getPower() {
        return power;
    }
    @Override
    public int getPp() {
        return pp;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
