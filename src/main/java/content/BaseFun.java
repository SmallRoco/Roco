package content;

import Config.ConfigFile;
import game.FightStart;
import UI.MainFrame;
import game.Game;
import game.ui.PetList;

import java.util.TreeSet;

public class BaseFun {

    public static byte[] random = new byte[1024];
    static int randomIndex = 0;

    public static int getPetsCount(Pet[] pets){
        int count = 0;
        for (int i = 0; i < pets.length; i++) {
            if(pets[i]==null)return count;
            count ++;
        }
        return count;
    }

    /**
     * 判断是玩家1还是玩家二
     *
     */
    public synchronized static int getIndexPawn(Pet pet){
        if(pet == MainFrame.pet1)return 0;
        return 1;
    }

    /**
     * 倒置 负面强化
     * @param pet
     * @return  是否成功
     */
    public static boolean inverteDeBuff(Pet pet){


        int[] appendStatus = pet.getAppendStatus();
        for (int i = 0; i < appendStatus.length; i++) {
            if(appendStatus[i]<0){
                if(!pet.statusChange(i,-appendStatus[i]*2)){
                    return false;
                }
            }
        }
        return true;
    }




    /**
     * 攻击场下宠物
     * @param pets  宠物列表
     * @return  是否命中
     */
    public static boolean randomAttOtherPet(Pet resPet,Pet dstPet,int power,boolean physical,String type,Pet[] pets){
        int petsCount = getPetsCount(pets);
        if(getPetsCount(pets)-getPetCountInDead(pets)<=1)return false;
        while (true){
            int random = BaseFun.getRandom(petsCount);
            if(pets[random]!=null&&pets[random]!=dstPet){
                Pet dstPet1 = pets[random];

                //如果miss
                if(ConfigFile.MISS_START && BaseFun.is((int) Math.sqrt(((dstPet.getMiss()) - resPet.getHitRate()) * 1.3))){
                    return false;
                }
                if(ConfigFile.MISS_START&&((dstPet1.getMissRestraint()>0&&BaseFun.attritube(dstPet.getType(),resPet)==1)||dstPet1.getNoDamage()>0)){
                    return false;
                }
                BaseFun.att(resPet,dstPet1,power,physical,type);
                return true;
            }
        }
    }

    /**
     * 释放其他加成
     * 参数以二进制传递 011010      从右到左  0为释放
     */
    public static boolean deleteOtherGreatBuff(Pet pet,int index){
        int[] appendStatus = pet.getAppendStatus();
        for (int i = 0; i <appendStatus.length; i++) {
            if(((index>>i)&1)==0){
                //等于0 保留        删除
                if(appendStatus[i]>0){
                    if(!pet.statusChange(i,-appendStatus[i])){
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * 获取阵亡宠物数量
     */
    public static int getPetCountInDead(Pet[] pets){
        int count = 0;
        for (int i = 0; i <pets.length; i++) {
            if(pets[i]==null)return count;
            if(pets[i].getHp()<=0){
                count++;
            }

        }
        return count;
    }

    /**
     * 获取宠物的强化数量
     * @return
     */
    public static int getPetGreatBuffCount(Pet pet){
        int count = 0;
        int[] appendStatus = pet.getAppendStatus();
        for (int i = 0; i <appendStatus.length; i++) {
            if(appendStatus[i]>0){
                count+=appendStatus[i];
            }
        }
        return count;
    }

    /**
     * 获取宠物负面强化的数量
     * @param pet
     * @return
     */
    public static int getPetDeBuffCount(Pet pet){
        int count = 0;
        int[] appendStatus = pet.getAppendStatus();
        for (int i = 0; i <appendStatus.length; i++) {
            if(appendStatus[i]<0){
                count+=-appendStatus[i];
            }
        }
        return count;
    }

    /***
     * 己方全部宠物的强化数量
     * @param pets
     * @return
     */
    public static int getOtherPetGreatBuffCount(Pet[] pets){
        int count = 0;
        for (int i = 0; i <pets.length; i++) {
            if(pets[i]==null)return count;
            count+=getPetGreatBuffCount(pets[i]);

        }
        return count;

    }




    /**
     * 全体技能pp改变
     * @param resPet
     * @return
     */
    public static int allPpChange(Pet resPet,int change){
        int count = 0;
        for (int i = 0; i <resPet.getSkills().size(); i++) {
            if(resPet.getSkills().get(i)!=null){
                resPet.changePp(i,change);
                count += change;
            }
        }
        return count;
    }

    /**
     * pp值不为1 的技能改变
     * @param resPet
     * @param change
     * @return
     */
    public static int otherPpChangeByMaxNotEqualsOne(Pet resPet,int change){
        int count = 0;
        for (int i = 0; i <resPet.getSkills().size(); i++) {
            if(resPet.getSkills().get(i)!=null){
                if(resPet.getSkills().get(i).getPp()==1)continue;
                resPet.changePp(i,change);
                count += change;
            }
        }
        return count;
    }

    /**
     * 获取宠物系别数量
     */
    public static int getPetsTypeCount(Pet[] pets){
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i <pets.length; i++) {
            if(pets[i]==null)return treeSet.size();
            if(pets[i].getType().contains(" ")){
                String[] s = pets[i].getType().split(" ");
                treeSet.add(s[0]);
                treeSet.add(s[1]);
            }
            treeSet.add(pets[i].getType());
        }
        return treeSet.size();
    }

    /**
     * 是否存在该系别宠物
     */
    public static boolean isHavePetForType(Pet[] pets,String s){
        for (int i = 0; i <pets.length; i++) {
            if(pets[i]==null)return false;
            if(pets[i].getType().contains(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取有多少只该系的宠物
     */

    public static int getPetsCountByType(Pet[] pets1,String type){
        int count = 0;
        for (int i = 0; i < pets1.length; i++) {
            if(pets1[i]!=null){
                if((pets1[i].getType()).contains(type)){
                    count++;
                }
            }else {
                return count;
            }
        }
        return count;
    }


    /**
     * 强化转移
     * @param resPet  接收转移
     * @param dstPet    被转移
     */
    public static boolean transferGreatBuff(Pet resPet,Pet dstPet){
        int[] appendStatus = dstPet.getAppendStatus();
        for (int i = 0; i <appendStatus.length; i++) {
            //下面把目标的转态删了之后 数组里的内容也没了 没法转移了， 要先存起来
            int temp = appendStatus[i];
            if(appendStatus[i]>0){
                if(!dstPet.statusChange(i,-appendStatus[i])){
                    return false;
                }else {
                    resPet.statusChange(i,temp);
                }
            }
        }
        return true;
    }



    /**
     * 模拟概率
     * @return 是否中
     */
    public synchronized static boolean is(int pacent){
        if(randomIndex==1024)randomIndex = 0;
        if(Game.connectFlag){
            return BaseFun.random[randomIndex++]<pacent;
        }

        return (Math.random()*100)<pacent;
    }

    /**
     * 只能生成100之内的
     * @param i
     * @return
     */
    public synchronized static int getRandom(int i){
        if(randomIndex==1024)randomIndex = 0;
        if(Game.connectFlag){
            return BaseFun.random[randomIndex++];
        }
        return (int)(Math.random()*i);
    }

    /**
     * 获取该宠物 对应技能的位置
     * @param pet           宠物
     * @param skillname        技能吗
     * @return              -1 代表没带
     */
    public static int getSkillIndex(Pet pet,String skillname){
        for (int i = 0; i <pet.getSkills().size(); i++) {
            if(pet.getSkills().get(i).getName().equals(skillname)){
                return i;
            }
        }
        return -1;
    }


    /**
     * 斩杀
     */
    public static void mustKill(Pet resPet,Pet dstPet){
        dstPet.setHp(dstPet.getHp()-999);

    }

    /**
     * 固定伤害
     * @param resPet    攻击者
     * @param dstPet    被攻击者
     * @param damage    伤害
     */
    public static void trueDamage(Pet resPet,Pet dstPet,int damage){
        //真伤无克制
        dstPet.setHp(dstPet.getHp()-damage);

    }

    /**
     * 普通攻击
     */
    public static int att(Pet srcPet,Pet dstPet,int power,boolean physical,String type){
        //System.out.println("BaseFun: power = "+ power);
        return baseAtt(srcPet, dstPet, power, physical, type,false);
    }

    /**
     *  必定暴击攻击
     */
    public static int boomAtt(Pet srcPet,Pet dstPet,int power,boolean physical,String type){
        return baseAtt(srcPet, dstPet, power, physical, type,true);
    }
    /**
     * 发动攻击
     * @param srcPet  攻击来源对象
     * @param dstPet    攻击目标
     * @param power     攻击强度
     * @param physical  是否是物理伤害
     * @param type      技能的属性
     * @param whaleBoom 是否必定暴击
     */
    private static int baseAtt(Pet srcPet,Pet dstPet,int power,boolean physical,String type,boolean whaleBoom){


        //计算标准伤害
        int damage = calculateDamage(srcPet, dstPet, power,physical);
        //判断属性克制
        switch (attritube(type,dstPet)){
            case 1: damage*=2;  MainFrame.keZhiFlag = 1;
                break;
            case -1: damage*=0.5;  MainFrame.keZhiFlag = -1;
                break;
        }
        //计算暴击
        if(ConfigFile.BOOM_START&&(is((srcPet.getBoom()-300)/15)||whaleBoom)){damage*=2;
            MainFrame.boomFlag = true;
        }
        //TODO 火系攻击解除冰冻
        if(type.contains("火")){dstPet.removeStatus("冰冻");}else if(BaseFun.is(50)){dstPet.removeStatus("冰冻");}
        //TODO 攻击有概率解除睡眠和冰冻
        if(dstPet.getStatus("睡眠")!=null&&BaseFun.is(50)){dstPet.removeStatus("睡眠");}
        //水攻击解除
        if(type.contains("水")){dstPet.removeStatus("烧伤");}



        //TODO 判断是否有威力提升
        if(srcPet.getUpPower()>0){
            damage*=1.25;
        }

        if(srcPet.getAttLimit()>100){
            System.out.println(srcPet.getAttLimit());
        }
        damage *= srcPet.getAttLimit()/100.0;

        dstPet.setHp(dstPet.getHp()-damage);
        return damage;
    }

    /**
     * 删除其他技能pp
     * @param pet
     * @param name
     * @param change
     */

    public static void changeOtherSkillPp(Pet pet,String name,int change){
        for (int i = 0; i <pet.getSkills().size(); i++) {
            if(pet.getSkills().get(i)==null)return;
            if(pet.getSkills().get(i).getName().equals(name))continue;
            pet.changePp(i,change);
        }
    }

    /**
     * 删除其他技能pp
     * @param pet
     * @param name
     * @param change
     */
    public static void changeOtherSkillPp(Pet pet,String name,String name1,int change){
        for (int i = 0; i <pet.getSkills().size(); i++) {
            if(pet.getSkills().get(i)==null)return;
            if(pet.getSkills().get(i).getName().equals(name)||pet.getSkills().get(i).getName().equals(name1))continue;
            pet.changePp(i,change);
        }
    }

    /**
     * 场下宠物回血
     * @param pets      宠物列表
     * @param resPet       场上宠物
     * @param byBaseHp  是否是根据生命值百分比回血   不是则会指定值
     * @param value     回血的百分比或者数值
     */
    public static void otherPersRecover(Pet[] pets,Pet resPet,boolean byBaseHp,int value){
        for (Pet pet:pets) {
            if(pet==null)return;
            if(pet==resPet)continue;
            if(pet.getHp()<=0)continue;
            if(byBaseHp){
                pet.setHp(pet.getHp()+pet.getBaseHp()*value/100);
            }else {
                pet.setHp(pet.getHp()+value);
            }
        }
    }

    /**
     * 驱逐宠物
     * @param pet   被驱逐的宠物
     * @return      是否成功
     */
    public static boolean expelPet(Pet pet){

        Pet[] petList = getPetList(pet);
        for (int i = 0; i < petList.length; i++) {
            if(petList[i]==null)return false;
            if(petList[i]==pet||petList[i].getHp()<=0)continue;
            MainFrame.changePet(pet,i);
            return true;
        }

        return true;
    }

    /**
     * 净化所有存活宠物的异常与负面
     * @param pets
     * @return  净化的成功的次数
     */
    public static int clearAllActivePets(Pet[] pets){
        int count = 0;
        for (int i = 0; i < pets.length; i++) {
            if(pets[i]==null)return count;
            if(pets[i].getHp()<=0)continue;
            count += pets[i].clearSelf();
        }
        return count;
    }


    /**
     * 获取宠物列表中生命值最低的宠物
     * @param petList
     * @return
     */
    public static Pet getPetOfMinHp(Pet[] petList){

        int index = -1;
        int minHp = 10000;
        for (int i = 0; i < petList.length; i++) {
            if(petList[i]==null)return petList[index];
            if(petList[i].getHp()<=0)continue;
            if(petList[i].getHp()<minHp){
                index = i;
                minHp = petList[i].getHp();
            }
        }

        return petList[index];

    }

    /**
     * 获取己方生命值上限最高的宠物
     * @param petList
     * @return
     */
    public static Pet getPetOfMaxBaseHp(Pet[] petList){
        int index = -1;
        int maxBaseHp = 0;
        for (int i = 0; i < petList.length; i++) {
            if(petList[i]==null)return petList[index];
            if(petList[i].getHp()<=0)continue;
            if(petList[i].getBaseHp()>maxBaseHp){
                index = i;
                maxBaseHp = petList[i].getBaseHp();
            }
        }

        return petList[index];
    }





    /**
     * 计算裸伤
     * @param srcPet    攻击来源
     * @param dstPet    攻击目标
     * @param power     攻击强度
     * @param physical  是否是物理伤害
     * @return
     */
    public static int calculateDamage(Pet srcPet,Pet dstPet,int power,boolean physical){


        if(physical){
            return power*srcPet.getPhysicalAttack()/dstPet.getPhysicalDefense();
        }else{
            return power*srcPet.getMagicAttack()/dstPet.getMagicDefense();
        }

    }

    /**
     * 判断属性克制
     * @return
     */
    public static int attritube(String type,String type1){
        String[] arrayList = ConfigFile.typeKeZhi.get(type);
        if(arrayList!=null){
            for (int i = 0; i <arrayList.length; i++) {
                if(arrayList[i].equals(type1)){
                    return 1;
                }
            }
        }

        arrayList = ConfigFile.typeDiKang.get(type);
        if(arrayList!=null){
            for (int i = 0; i <arrayList.length; i++) {
                if(arrayList[i].equals(type1)){
                    return -1;
                }
            }
        }

        return 0;
    }

    /**
     * 判断属性克制
     * @return
     */
    public static int attritube(String type,Pet dstPet){
        String[] arrayList = ConfigFile.typeKeZhi.get(type);
        if(arrayList!=null){
            for (int i = 0; i <arrayList.length; i++) {
                if(arrayList[i].equals(dstPet.getType())){
                    return 1;
                }
            }
        }

        arrayList = ConfigFile.typeDiKang.get(type);
        if(arrayList!=null){
            for (int i = 0; i <arrayList.length; i++) {
                if(arrayList[i].equals(dstPet.getType())){
                    return -1;
                }
            }
        }

        return 0;
    }

    /**
     * pet
     * @return
     */
    public static Pet[] getPetList(Pet pet){
        if(pet==MainFrame.pet1)return FightStart.getPets1();
        return FightStart.getPets2();
    }

    /**
     * 通过 属性获取宠物列表
     * @param type
     * @return
     */
    public static Pet[] getPetList(Pet[] petList,String type){
        Pet[] pets = new Pet[6];
        int count = 0;
        for (int i = 0; i <petList.length; i++) {
            if(petList[i]==null)break;
            count++;
        }
        petList = new Pet[count];
        for (int i = 0; i <count; i++) {
            petList[i] = pets[i];
        }
        return petList;
    }

    /**
     * 获取其他宠物列表
     * @param pet
     * @param name
     * @return
     */
    public static Pet[] getOtherPetList(Pet pet,String name){
        Pet[] petList = getPetList(pet);
        Pet[] pets = new Pet[6];
        int count = 0;
        for (int i = 0; i <petList.length; i++) {
            if(petList[i]==null)break;
            if(petList[i].getName().equals(name))continue;
            count++;
        }
        petList = new Pet[count];
        for (int i = 0; i <count; i++) {
            petList[i] = pets[i];
        }
        return petList;
    }


    /**
     * 获取宠物
     *
     */
    public static Pet getPet(String name,Pet pet){
        Pet[] pets;

        pets = getPetList(pet);
        for (int i = 0; i < pets.length; i++) {
            if(pets[i]!=null&&pets[i].getName().equals(name)){
                return pets[i];
            }
        }
        return null;
    }

    /**
     * 吸取PP
     */
    public static void swapPp(Pet resPet,Pet dstPet){
        for (int i = 0; i <dstPet.getSkills().size(); i++) {
            if(dstPet.getPps()[i]>0){
                dstPet.changePp(i,-1);
            }
        }
        for (int i = 0; i <resPet.getSkills().size(); i++) {
            if(dstPet.getPps()[i]>0){
                dstPet.changePp(i,1);
            }
        }
    }


}
