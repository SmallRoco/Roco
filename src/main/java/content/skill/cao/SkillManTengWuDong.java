package content.skill.cao;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillManTengWuDong implements SkillInterface {
    String name = "蔓藤舞动";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "草";
    int power = 0;
    int pp = 8;
    String description = "束缚敌方，对方免疫则强化自身1级魔攻，束缚成功则清空敌方最近使用的技能并恢复自身全体技能2PP。转移敌方正面强化到自身，转移失败则造成固定伤害。先手+2";
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

@Override public String getTuoshou(){return "灵蔓盾击";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        if(!dstPet.addStatus("束缚")){
            resPet.statusChange(2,1);
        }else {
            int i = MainFrame.useSkillOld[BaseFun.getIndexPawn(dstPet)];
            if(i>=0){
                resPet.changePp(i,-resPet.getPps()[i]);
            }
        }
        BaseFun.allPpChange(resPet,+2);
        if(!BaseFun.transferGreatBuff(resPet,dstPet)){
            BaseFun.trueDamage(resPet,dstPet,40);
        }

    }
}
