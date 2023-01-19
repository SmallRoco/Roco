package content.skill.tu;

import Config.ConfigFile;
import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.BattleSpace;

public class SkillDiBaoTianXing implements SkillInterface {
    String name = "地爆天星";
    String animal = "att1";
    boolean physical = false;
    int speed = 12;
    String type = "土";
    int power = 0;
    int pp = 8;
    String description = "万象引，土聚星。双方进入决斗空间5回合：提升对方PP损耗，吸取对方的精力并逐渐提升效果，提升自身威力伤害。首回合净化自身并暂时免疫异常，净化成功则回复精力和本技能pp，闪避被克制系别对手攻击，若耀阳颂pp耗尽则额外闪避对方后手技能。先手+4";
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

    //@Override public String getTuoshou(){return "魔焰瞬击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        //5回合战斗空间
        MainFrame.setSpace(new BattleSpace(5,resPet==MainFrame.pet1));
        dstPet.setPpDown(5);
        resPet.setUpPower(5);
        if(resPet.clearSelf()>0){
            resPet.setHp(resPet.getHp()+resPet.getBaseHp()*0.25);
            resPet.changePp(BaseFun.getSkillIndex(resPet,name),1);
        }
        if(resPet.isHasThisSkill("耀阳颂")==0){
            resPet.setNoDamage(1);
        }
        resPet.setMissRestraint(5);

        resPet.setNoexception(ConfigFile.get("暂时"));


    }
}
