package content.skill.new1;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.netpetshow.SkillN;

public class SkillHanBing implements SkillInterface {
    String name = "寒冰";
    String animal = "att1";
    boolean physical = false;
    int speed = 8;
    String type = "冰";
    int power = 120;
    int pp = 10;
    String description = "以绝对零度的寒流冰冻对手，对面免疫则会吸取PP值。如果对方已经处于冰冻状态，则对方下一个上场宠物会进入冰冻状态。后手-1";
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

@Override public String getTuoshou(){return "冰晶结界";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(dstPet.getStatus("冰冻")!=null){
            MainFrame.addNextPetShow(dstPet,new SkillN(new String[]{"冰冻"},new int[]{0}));
        }else {
            if (!dstPet.addStatus("冰冻")) {
                BaseFun.swapPp(resPet, dstPet);
            }
        }
    }
}
