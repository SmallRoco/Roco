package content.skill.guang;

import UI.MainFrame;
import Utils.BaseFunction;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;

public class SkillQingXiaoDanXia implements SkillInterface {
    String name = "青霄丹霞";
    String animal = "att1";
    boolean physical = true;
    int speed = 9;
    String type = "光";
    int power = 110;
    int pp = 10;
    String description = "朱颜琢秀，皎若晨霞。强化自身物攻并恐惧对方，被免疫则短暂清除并锁定天气环境。若处于晨曦形态，对敌方场下宠物造成伤害，初阳形态将光系队友所携的正面强化与自身除物攻外的正负面强化转化为物攻等级。先手+1";
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
        resPet.statusChange(0,1);
        if(dstPet.addStatus("恐惧")){
            MainFrame.setEnvironment(null,0);
            MainFrame.setEnvironmentLock(1);
        }

        if(resPet.getMark("初阳")==null){
            BaseFun.randomAttOtherPet(resPet,dstPet,power,physical,type,BaseFun.getPetList(dstPet));
        }else {
            Pet[] petList = BaseFun.getPetList(resPet);
            for (int i = 0; i <petList.length; i++) {
                Pet pet = petList[i];
                if(pet==null)break;
                if(pet.getHp()<=0)continue;
                if(pet.getType().contains("光")){
                    int petGreatBuffCount = BaseFun.getPetGreatBuffCount(pet);
                    petGreatBuffCount -= Math.max(0,pet.getAppendStatus()[0]);
                    if(BaseFun.deleteOtherGreatBuff(pet,1)){
                        pet.statusChange(0,petGreatBuffCount);
                    }
                }
            }
        }

        BaseFun.att(resPet,dstPet,power,physical,type);
    }
}
