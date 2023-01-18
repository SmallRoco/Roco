package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import game.FightStart;

public class SkillDengYingYouJun implements SkillInterface {
    String name = "灯影幽峻";
    String animal = "att1";
    boolean physical = false;
    int speed = 8;
    String type = "水";
    int power = 0;
    int pp = 5;
    String description = "灯影在先时，则视敌方宠物系别数量有不同效果。灯影在后时，则视敌方技能是否为草系和电系，有不同效果。";
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
        if(MainFrame.first){
            int petsTypeCount = BaseFun.getPetsTypeCount(BaseFun.getPetList(dstPet));
            petsTypeCount = Math.max(petsTypeCount,10);
            BaseFun.att(resPet,dstPet,(10-petsTypeCount)*20,physical,type);
        }else {
            String dstSkillType = dstPet.getSkills().get(MainFrame.useSkill[BaseFun.getIndexPawn(dstPet)]).getType();
            if("草".equals(dstSkillType)){
                resPet.setHp(resPet.getHp()+resPet.getBaseHp()*80);
            }else if("电".equals(dstSkillType)){
                resPet.addBigProtection(resPet.getBaseHp()*80);
            }else {

            }
        }
    }
}
