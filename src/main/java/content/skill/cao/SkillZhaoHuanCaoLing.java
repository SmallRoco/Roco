package content.skill.cao;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillZhaoHuanCaoLing implements SkillInterface {
    String name = "召唤草灵";
    String animal = "att1";
    boolean physical = false;
    int speed = 10;
    String type = "草";
    int power = 100;
    int pp = 12;
    String description = "召唤草灵攻击对方，删除敌方全体技能2PP，闪避抵抗草系目标的后手技能一回合并恐惧对面，免疫恐惧则恢复其它技能1PP并强化自身1级魔攻再造成伤害。造成伤害后释放除魔攻外的所有强化。伤害与阵中草系队友及自身强化有关。当己方队伍中阵亡宠物过多，本技能无法回复自然之友的PP。先手+2";
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

        BaseFun.allPpChange(dstPet,-2);
        if(BaseFun.attritube(dstPet.getType(),resPet)==-1){
            resPet.setNoDamage(1);
            if(!dstPet.addStatus("恐惧")){
                resPet.statusChange(1,1);

            }
        }

        BaseFun.att(resPet,dstPet,power+BaseFun.getPetList(BaseFun.getOtherPetList(resPet,name),"草").length*10+BaseFun.getPetGreatBuffCount(resPet)*10,physical,type);

        BaseFun.deleteOtherGreatBuff(resPet,2);

        if(BaseFun.getPetCountInDead(BaseFun.getPetList(resPet))>3){
            BaseFun.changeOtherSkillPp(resPet,name,"自然之友",1);
        }else {
            BaseFun.changeOtherSkillPp(resPet,name,1);
        }



    }
}
