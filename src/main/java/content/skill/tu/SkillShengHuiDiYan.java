package content.skill.tu;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillShengHuiDiYan implements SkillInterface {
    String name = "圣辉地焰";
    String animal = "att1";
    boolean physical = false;
    int speed = 11;
    String type = "土";
    int power = 100;
    int pp = 10;
    String description = "圣地焰，焚邪魅。召唤沃土环境后发动攻击，根据伤害回复精力。召唤环境成功则回复首技能PP，否则删除对方PP；耀阳颂PP耗尽必定暴击，否则增强回复精力效果并清除对方强化同时吸取其PP。对方有仇恨标记或己方场下阵亡辉月土王携带异常或负面状态时消耗此状态斩杀对方。先手+3";
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
    public void skill(Pet resPet, Pet dstPet) {
        //召唤沃土环境
        if(MainFrame.setEnvironment("沃土",4)){
            resPet.changePp(0,1);
        }else {
            dstPet.changePp(0,-2);
        }
        //发动攻击
        Pet hytw = BaseFun.getPet("辉月土王",resPet);
        if(dstPet.getMark("仇恨")!=null||(hytw!=null&&(hytw.isDebuff()||hytw.isException()))){
            BaseFun.mustKill(resPet,dstPet);
        }else {
            if(resPet.isHasThisSkill("耀阳颂")==0){
                int damage = BaseFun.boomAtt(resPet,dstPet,power,physical,type);
                resPet.setHp((int)(resPet.getHp()+damage*0.4));
            }else {
                int damage = BaseFun.att(resPet,dstPet,power,physical,type);
                resPet.setHp((int)(resPet.getHp()+damage*0.4));
                resPet.setRecover(3,120);
                dstPet.clearGreatBuff();
                BaseFun.swapPp(resPet,dstPet);

            }
        }

    }
}
