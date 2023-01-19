package content.skill.new1;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.netpetshow.SkillN;

public class SkillLingYuanYiXing implements SkillInterface {
    String name = "灵渊移星";
    String animal = "magic";
    boolean physical = false;
    int speed = 11;
    String type = "冰";
    int power = 110;
    int pp = 10;
    String description = "澄渊映斗，星汉西流。冰冻对方，免疫则为下一只出场宠物提供护盾。自身无正面强化时额外回复碧天清晓PP。之后在非冰雹天气下提升魔攻。先手+3";
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
        BaseFun.att(resPet,dstPet,power,physical,type);
        if(dstPet.addStatus("冰冻")){
            MainFrame.addNextPetShow(resPet,new SkillN(new String[]{"addBigProtection"}, new int[]{200}));
        }
        if(BaseFun.getPetGreatBuffCount(resPet)==0){
            int index = BaseFun.getSkillIndex(resPet, "碧天清晓");
            if(index!=-1){
                resPet.changePp(index,1);
            }
        }

        if(!MainFrame.getEnvironment().equals("冰雹")){
            resPet.statusChange(1,1);
        }
    }
}
