package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillFuYingMen implements SkillInterface {
    String name = "福盈门";
    String animal = "magic";
    boolean physical = false;
    int speed = 15;
    String type = "水";
    int power = 0;
    int pp = 4;
    String description = "福泽延绵，喜气盈门。根据PP值累计福气。召唤星云天气，净化自身并暂时免疫异常，短暂抵御爆发性伤害。根据福气数回复精力与PP并强化自身魔攻。受福气护佑获得寄生、麻醉、迷惑、束缚、催眠免疫。先手+7";
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

    @Override public String getTuoshou(){return "光明增益";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        int pp = resPet.getPps()[BaseFun.getSkillIndex(resPet,name)];
        Integer fuQi = resPet.getMark("福气");
        int nowFuQi = 0;
        if(fuQi==null){
            resPet.addMark("福气",1000+pp);
            resPet.addMark("寄生免疫",1000);
            resPet.addMark("麻痹免疫",1000);
            resPet.addMark("魅惑免疫",1000);
            resPet.addMark("束缚免疫",1000);
            resPet.addMark("睡眠免疫",1000);
            nowFuQi = pp;
        }else {
            resPet.addMark("福气",pp);
            nowFuQi = fuQi+pp-1000;
        }
        MainFrame.setEnvironment("星云",5);
        resPet.clearSelf();
        resPet.setNoexception(2);
        resPet.setMaxDamaged(200,1);
        resPet.setHp(resPet.getHp()+nowFuQi*40);

        if(nowFuQi>3) {
            BaseFun.changeOtherSkillPp(resPet,name,nowFuQi/4);
            resPet.statusChange(1, nowFuQi / 4);
        }


    }
}
