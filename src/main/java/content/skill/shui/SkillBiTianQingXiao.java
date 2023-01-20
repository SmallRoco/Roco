package content.skill.shui;

import Config.ConfigFile;
import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillBiTianQingXiao implements SkillInterface {
    String name = "碧天清晓";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "水";
    int power = 0;
    int pp = 4;
    String description = "风丝扫镜，雨晴净空。被动免疫束缚、催眠、寄生、迷惑、麻醉和烧伤，召唤阴雨并净化存活宠物的异常与负面，短暂免疫异常。防控回合内减免自身所受的伤害。根据净化的异常与负面项数为受净化的宠物回复PP。先手+7";
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

    @Override public String getTuoshou(){return "翅膀增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        MainFrame.setEnvironment("阴雨",6);
        int clearCount = BaseFun.clearAllActivePets(BaseFun.getPetList(resPet));
        int duanZan = ConfigFile.get("短暂");
        resPet.setNoexception(duanZan);
        resPet.setNoDeBuff(duanZan);
        resPet.setDownDamage(70,duanZan);
        BaseFun.otherPpChangeByMaxNotEqualsOne(resPet,clearCount/4);

    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {

    }

    @Override
    public void init(Pet resPet){
        resPet.addMark("束缚免疫",1000);
        resPet.addMark("睡眠免疫",1000);
        resPet.addMark("寄生免疫",1000);
        resPet.addMark("魅惑免疫",1000);
        resPet.addMark("麻痹免疫",1000);
        resPet.addMark("烧伤免疫",1000);
    }
}
