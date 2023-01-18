package content.skill.guang;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.space.Skill.guang.SkillHongJingZhuoYanS;
import content.space.SpaceBase;

public class SkillHongJingZhuoYan implements SkillInterface {
    String name = "红镜灼炎";
    String animal = "magic";
    boolean physical = false;
    int speed = 8;
    String type = "光";
    int power = 0;
    int pp = 10;
    String description = "炎天昼火，长日弗暮。连续3回合对敌方造成伤害，场上无天气环境则增加其PP损耗。晨曦形态会清除对方强化，初阳形态则删除其PP。首回合会影响对方技能的命中并汲取对方场下被灼伤宠物的精力用以回复自身。若处于晨曦形态还会以阳炎之力灼烧对方。";
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


        dstPet.setDownHitRate(1);
        if(MainFrame.getEnvironment()==null){
            resPet.setPpDown(3);
        }

        Pet[] petList = BaseFun.getPetList(dstPet);
        for (Pet pet :petList) {
            if(pet==null)break;
            if(pet.getHp()<=0)continue;
            if(pet.getMark("灼伤")!=null){
                int damage = pet.getBaseHp()/8;
                pet.setHp(pet.getHp()-damage);
                resPet.setHp(resPet.getHp()+damage);
            }
        }
        if(resPet.getMark("初阳")==null){
            dstPet.addMark("灼伤",1000);
        }

        resPet.addSpaces(name,new SpaceBase(3,resPet==MainFrame.pet1,new SkillHongJingZhuoYanS()));


    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        resPet.addSpaces(name,new SpaceBase(3,resPet==MainFrame.pet1,new SkillHongJingZhuoYanS()));
    }
}
