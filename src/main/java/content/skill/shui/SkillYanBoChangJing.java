package content.skill.shui;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.netpetshow.SkillN;

public class SkillYanBoChangJing implements SkillInterface {
    String name = "烟波长静";
    String animal = "att1";
    boolean physical = false;
    int speed = 7;
    String type = "水";
    int power = 0;
    int pp = 10;
    String description = "薄霭云淡，收风凝川。据自身已损精力追加固伤并消除双方正面强化。消除对方成功则删除其最近使用技能PP，否则为己方精力上限最高的队友提供精力与PP回复。若对方不具有冰冻或睡眠抗性，会赋予对方场上宠物和下一只替换的宠物额外的削弱状态并回复自身部分技能PP。受到伤害时必中。后手-1";
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

    @Override public String getTuoshou(){return "";};
    @Override
    public void skill(Pet resPet, Pet dstPet) {

        int downHp = resPet.getBaseHp()-resPet.getHp();

        BaseFun.trueDamage(resPet,dstPet,Math.max(40+downHp/5,300));

        resPet.clearGreatBuff();

        if(dstPet.clearGreatBuff()){
            dstPet.changePp(MainFrame.useSkillOld[BaseFun.getIndexPawn(resPet)],-1);
        }else {
            //获取生命值上限最高的宠物
            Pet petOfMaxBaseHp = BaseFun.getPetOfMaxBaseHp(BaseFun.getPetList(resPet));
            petOfMaxBaseHp.setHp(petOfMaxBaseHp.getHp()+petOfMaxBaseHp.getBaseHp()*0.3);
            //改变一只宠物的所有技能pp  pp上限为1的技能不变
            BaseFun.otherPpChangeByMaxNotEqualsOne(petOfMaxBaseHp,1);

        }

        if(dstPet.getMark("冰冻免疫")==null&&dstPet.getMark("睡眠免疫")==null){
            for (int i = 0; i <7; i++) {
                dstPet.statusChange(i,-1);
            }
            MainFrame.addNextPetShow(dstPet,new SkillN(new String[]{"削弱"},new int[]{0}));
            resPet.changePp(0,1);
        }

    }

    @Override
    public void miss(Pet resPet, Pet dstPet) {
        if(!MainFrame.first&&resPet.isBeDamage()){
            skill(resPet,dstPet);
        }
    }
}
