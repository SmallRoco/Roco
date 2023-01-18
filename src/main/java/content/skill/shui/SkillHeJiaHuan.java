package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillHeJiaHuan implements SkillInterface {
    String name = "阖家欢";
    String animal = "att1";
    boolean physical = false;
    int speed = 9;
    String type = "水";
    int power = 0;
    int pp = 8;
    String description = "金玉满堂，幸福安康。净化自身并汲取对方场下宠物血量为自身与场下宠物提供治疗，自身的福气越多效果越好。先手则强化对队友治疗效果，否则强化对自身治疗效果。若福气全部分享(福气为0，没有使用过福盈门)则额外回复其他技能1PP。先手+1";
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
        resPet.clearSelf();
        Integer fuQI = resPet.getMark("福气");
        if(fuQI==null){
            BaseFun.changeOtherSkillPp(resPet,name,1);
            fuQI = 1;
        }else {
            fuQI -=999;
        }

        boolean b = BaseFun.randomAttOtherPet(resPet, dstPet, 10*fuQI, false, type, BaseFun.getPetList(dstPet));
        if(b){
            if(MainFrame.first) {
                resPet.setHp(resPet.getHp()+(resPet.getBaseHp()*fuQI*2)/100);
                BaseFun.otherPersRecover(BaseFun.getPetList(resPet), resPet, true, fuQI*2);
            }else {
                resPet.setHp(resPet.getHp()+(resPet.getBaseHp()*fuQI*4)/100);
                BaseFun.otherPersRecover(BaseFun.getPetList(resPet), resPet, true, fuQI);
            }
        }



    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.clearSelf();
    }
}
