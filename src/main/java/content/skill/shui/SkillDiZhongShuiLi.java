package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillDiZhongShuiLi implements SkillInterface {
    String name = "敌众水澧";
    String animal = "magic";
    boolean physical = false;
    int speed = 12;
    String type = "水";
    int power = 0;
    int pp = 4;
    String description = "以水之柔，化敌之众。净化自身异常与负面，免疫异常与召唤星云天气五回合。敌方存在草与龙系宠物时常开保护，否则根据水系伙伴数回复自身精力值。先手+4";
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

    @Override public String getTuoshou(){return "蓝色水球";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
        resPet.setNoexception(5);
        MainFrame.setEnvironment("星云",5);
        if(BaseFun.isHavePetForType(BaseFun.getPetList(dstPet),"草")||BaseFun.isHavePetForType(BaseFun.getPetList(dstPet),"龙")){
            resPet.addBigProtection((int) (resPet.getBaseHp()*0.4));
        }else {
            int count = BaseFun.getPetsCountByType(BaseFun.getPetList(resPet), "水");
            resPet.setHp(resPet.getHp()+count*100);
        }
    }
}
