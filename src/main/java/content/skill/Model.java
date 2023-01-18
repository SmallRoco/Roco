package content.skill;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import game.FightStart;

public class Model implements SkillInterface {
    String name = "技能魔板";
    String animal = "magic";
    boolean physical = false;
    int speed = 9;
    String type = "冰";
    int power = 100;
    int pp = 15;
    String description = "有概率冻结敌人，对水和神水系敌人效果拔群。先手+1";
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
        //提升一级物攻
        resPet.statusChange(0,1);
        //造成伤害
        BaseFun.att(resPet,dstPet,power,physical,type);
        //必定暴击
        BaseFun.boomAtt(resPet,dstPet,power,physical,type);
        //回血40%
        resPet.setHp((int)(resPet.getHp()+resPet.getBaseHp()*0.4));
        //99%的概率让对方睡眠
        if(BaseFun.is(99)){
            dstPet.addStatus("睡眠");
        }
        //净化自身
        resPet.clearSelf();
        //净化异常
        resPet.clearException();
        //净化负面
        resPet.clearNoDebuff();
        //清除强化
        resPet.clearGreatBuff();
        //两回合免疫异常
        resPet.setNoexception(2);
        //永久免疫负面
        resPet.setNoDeBuff(1000);
        //治疗效果调整为60% 持续2回合
        resPet.setRecover(2,60);
        //召唤沃土环境4回合
        MainFrame.setEnvironment("沃土",4);
        //删除第二个技能2个pp
        resPet.changePp(1,-2);
        //场下宠物列表
        Pet[] pets = BaseFun.getPetList(resPet);
        //添加标记  添加1层仇恨标记
        dstPet.addMark("仇恨",1);
        //获取标记    没有则返回null     ，必须用Integer接收，不能用int  也不能强转  因为int没有null
        Integer integer = dstPet.getMark("仇恨");
        //宠物是否存在异常
        pets[0].isException();
        //是否存在负面
        dstPet.isDebuff();
        //是否存在强化
        dstPet.isGreatBuff();
        //是否阵亡
        boolean dead = dstPet.isDead();
        //斩杀        固定值999
        BaseFun.mustKill(resPet,dstPet);
        //判断技能pp    -1为没带
        int count = dstPet.isHasThisSkill("魔焰瞬击");
        //获取场下宠物
        Pet pet_1 = BaseFun.getPet("辉月土王",resPet);
        //吸取pp
        BaseFun.swapPp(resPet,dstPet);
        //提升pp损耗        5回合
        dstPet.setPpDown(5);
        //提升技能威力    5回合
        dstPet.setUpPower(5);
        //闪避属性克制对手的攻击   5回合
        dstPet.setMissRestraint(5);
        //闪避（免疫）一回合
        resPet.setNoDamage(1);
        //获取技能下标
        int index_dbtx = BaseFun.getSkillIndex(resPet, "地爆天星");
        //添加护盾  不会自主消失的
        resPet.addBigProtection(100);
        //添加标记
        resPet.addMark("仇恨",2);
        //改变所有技能pp
        BaseFun.allPpChange(resPet,+2);
        //转移强化 把dst的转到res
        BaseFun.transferGreatBuff(resPet,dstPet);
        //固定伤害40
        BaseFun.trueDamage(resPet,dstPet,40);
        //睡眠免疫5回合
        resPet.addMark("睡眠免疫",5);
        //获取有多少种属性
        BaseFun.getPetsTypeCount(FightStart.getPets1());
        //获取是否存在该系别宠物
        BaseFun.isHavePetForType(FightStart.getPets1(),"草");
        //获取草系宠物的数量
        int cc = BaseFun.getPetsCountByType(FightStart.getPets1(),"草");
        //倒置负面强化
        BaseFun.inverteDeBuff(resPet);
        //随机攻击场下宠物
        //BaseFun.randomAttOtherPet()

        //获取异常数量
        resPet.getStatusSize();
        //获取debuff数量
        BaseFun.getPetDeBuffCount(resPet);
        //场下回血
        BaseFun.otherPersRecover(BaseFun.getPetList(resPet),null,false,50);
        //驱逐宠物下场
        BaseFun.expelPet(dstPet);

    }
}
