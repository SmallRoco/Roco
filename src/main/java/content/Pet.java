package content;

import Config.ImageUtils;
import Config.ConfigFile;
import UI.BloodLine;
import UI.MainFrame;
import UI.SkillItemLabel;
import content.space.SpaceInterface;
import sun.security.krb5.Config;

import javax.swing.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pet{

    final static String[] statusNames = new String[]{"物攻","魔攻","物防","魔防","速度","闪避","命中"};

    HashMap<String,SpaceInterface> spaces = new HashMap<>();

    /**
     * 添加空间   （持续几回合干什么什么）
     * @param skillName    添加该空间的技能名字
     * @param space         空间
     * @return              是否添加成功
     */
    public boolean addSpaces(String skillName,SpaceInterface space){
        spaces.put(skillName,space);
        return true;
    }

    public boolean haveSpace(String skillName){
        if(spaces.get(skillName)==null)return false;
        return true;
    }

    HashMap<String,Integer> marks = new HashMap<>();
    public boolean addMark(String s,int i){
        if(!ConfigFile.MARK_START) return false;
        Integer integer = marks.get(s);
        if(integer==null){
            integer = 0;
        }
        if(i+integer<=0){return true;}
        marks.put(s,i+integer);
        return true;
    }
    public boolean setMark(String s,int i){
        if(!ConfigFile.MARK_START) return false;
        marks.put(s,i);
        return true;
    }
    public Integer getMark(String s){
        if(!ConfigFile.MARK_START)return null;
        return marks.get(s);
    }



    JLabel pawn;
    BloodLine bloodLine;



    String name;
    String type;
    int baseHp;
    int basePhysicalAttack;
    int baseMagicAttack;
    int basePhysicalDefense;
    int baseMagicDefense;
    int baseSpeed;
    int baseBoom;
    int baseMiss;
    int baseHitRate;

    //免疫负面的回合数
    int noDeBuff = 0;
    //免疫异常的回合数
    int noexception = 0;
    //治疗效果的回合数            强度   基础为100
    int Recover = 0;    int recoverPower = 100;
    //免疫伤害回合数
    int noDamage = 0;
    //提升pp损耗
    int ppDown = 0;
    //提升威力伤害
    int upPower = 0;
    //闪避属性克制对手的攻击
    int missRestraint;
    //不会自动消失的护盾
    int bigProtection;
    //承受的最大伤害
    int maxDamagedPower;
    //回合数
    int maxDamaged =0;
    //命中下降 回合数
    int downHitRate = 0;

    public void pass(){
        if(noDeBuff>0&&noDeBuff<1000)noDeBuff--;
        if(noexception>0&&noexception<1000)noexception--;
        if(Recover>0&&Recover<1000)Recover--;
        if(noDamage>0&&noDamage<1000)noDamage--;
        if(maxDamaged>0&&maxDamaged<1000)maxDamaged--;
        if(downHitRate>0&&downHitRate<1000)downHitRate--;
        Set<String> strings = marks.keySet();
        String[] strings1 = new String[strings.size()];
        strings.toArray(strings1);
        for (int i = 0; i < strings1.length; i++) {
            Integer mark = getMark(strings1[i]);
            if(mark==null){continue;}
            if(mark<1000){
                marks.put(strings1[i],mark-1);
                if(mark-1==0){
                    marks.remove(strings1[i]);
                }
            }
        }
    }

    int hp;

    //物理攻击
    //魔攻
    //物防
    //魔防
    //速度

    int physicalAttack;
    int magicAttack;
    int physicalDefense;
    int magicDefense;
    int speed;
    int boom;
    int miss;
    int hitRate;

    final static int MAX_GRADE = 6;
    int[] appendStatus = new int[7];
    public boolean statusChange(int statusId,int changeValue){

        //判断锁定
        // return false;

        if(changeValue<0&&noDeBuff>0)return false;

        if(changeValue>1){

            for (int i = 0; i <changeValue; i++) {
                statusChange(statusId,1);
            }
            return true;
        }else if(changeValue<-1){

            for (int i = 0; i <-changeValue; i++) {
                 statusChange(statusId,-1);
            }
            return true;
        }

        appendStatus[statusId]+=changeValue;

        if(appendStatus[statusId]>MAX_GRADE){
            appendStatus[statusId]=MAX_GRADE;
            return true;
        } else if(appendStatus[statusId]<-MAX_GRADE){
            appendStatus[statusId]=-MAX_GRADE;
            return true;
        }


        if(this==MainFrame.pet1||this==MainFrame.pet2) {
            //TODO 播放动画
            if (changeValue > 0) {
                MainFrame.playStatus(statusNames[statusId] + "提升", this == MainFrame.pet1);
            } else {
                MainFrame.playStatus(statusNames[statusId] + "下降", this == MainFrame.pet1);
            }

        }


        physicalAttack = basePhysicalAttack;
        magicAttack = baseMagicAttack;
        physicalDefense = basePhysicalDefense;
        magicDefense = baseMagicDefense;
        speed = baseSpeed;
        miss = baseMiss;
        hitRate = baseHitRate;

        for (int i = 0; i <appendStatus.length; i++) {
            int value = appendStatus[i];
            if(value>0){
                while (value-->0){
                    switch (i){
                        case 0: physicalAttack*=1.3;
                            break;
                        case 1: magicAttack*=1.3;
                            break;
                        case 2: physicalDefense*=1.3;
                            break;
                        case 3: magicDefense*=1.3;
                            break;
                        case 4: speed*=1.3;
                            break;
                        case 5: miss*=1.3;
                            break;
                        case 6: hitRate*=1.3;
                            break;
                    }
                }
            }else{
                while (value++<0){
                    switch (i){
                        case 0: physicalAttack*=0.7;
                            break;
                        case 1: magicAttack*=0.7;
                            break;
                        case 2: physicalDefense*=0.7;
                            break;
                        case 3: magicDefense*=0.7;
                            break;
                        case 4: speed*=0.7;
                            break;
                        case 5: miss*=0.7;
                            break;
                        case 6: hitRate*=0.7;
                            break;
                    }
                }

            }
        }

        MainFrame.showStatusMessage();
        return true;



        //System.out.println("物攻:"+this.physicalAttack);

    }





    HashMap<String,Boolean> status = new HashMap<>();

    /**
     * 是否存在异常状态
     * @return
     */
    public boolean isException(){
        return status.keySet().size()>0;
    }

    /**
     * 是否存在负面
     * @return
     */
    public boolean isDebuff(){
        for (int i = 0; i <5; i++) {
            if(appendStatus[i]<0)return true;
        }
        return false;
    }
    /**
     * 是否存在强化
     * @return
     */
    public boolean isGreatBuff(){
        for (int i = 0; i <5; i++) {
            if(appendStatus[i]>0)return true;
        }
        return false;
    }

    /**
     * 完全净化
     */
    public boolean clearSelf(){
        clearException();
        clearNoDebuff();
        return true;
    }
    /**
     * 净化异常
     */
    public boolean clearException(){
        status = new HashMap<>();
        return true;
    }

    /**
     * 清除负面
     */
    public boolean clearNoDebuff(){
        for (int i = 0; i <5; i++) {
            if(appendStatus[i]<0)appendStatus[i]=0;
        }
        return true;
    }
    /**
     * 清除强化
     */
    public boolean clearGreatBuff(){
        for (int i = 0; i <5; i++) {
            if(appendStatus[i]>0)appendStatus[i]=0;
        }
        return true;
    }

    /**
     * 添加异常状态
     */
    public boolean addStatus(String name){
        if(noexception>0)return false;
        if(getMark(name+"免疫")!=null)return false;
        if("寄生".equals(name)&&getType().contains("草"))return false;
        if("烧伤".equals(name)){
            if(this.getType().contains("火")){       //火和神火无法烧伤
                return false;
            }
            statusChange(1,-2);
        }
        if("麻醉".equals(name)){statusChange(4,-1);}
        status.put(name,true);
        MainFrame.showStatusMessage();
        MainFrame.playStatus(name+"开始",Pet.this==MainFrame.pet1);

        return true;

    }

    //重设技能
    public void resetSkill(String[] skillNames){
        skills = new ArrayList<>(4);
        for (int i = 0; i <4; i++) {
            skills.add(SkillManager.getHashMap().get(skillNames[i]));
            if(skills.get(i)==null)break;
            this.basePps[i] = skills.get(i).getPp();
            this.pps[i] = skills.get(i).getPp();
        }

    }

    /**
     * 移除异常状态
     */
    public void removeStatus(String name){
        if(status.get(name)==null){return;}
        status.remove(name);
        MainFrame.playStatus(name+"消失",Pet.this==MainFrame.pet1);

    }


    /**
     * 自身异常状态处理
     * 返回值 是否跳过该回合
     */
    public boolean statusHandler(){

        if(hp<=0)return false;

        Set<String> strings = status.keySet();
        String[] ss = new String[strings.size()];
        strings.toArray(ss);
        for (String s:ss) {
            //如果存在该转态
            if(status.get(s)!=null) {
                //判断是否需要解除该状态    1 解除   2 继续    3 暂时解除()不播放动画

                switch (StatusManager.getStatus(s).on(this)){
                    case 1:removeStatus(s);
                        break;
                    case 0:MainFrame.playStatus(s + "开始", Pet.this == MainFrame.pet1);
                        if("烧伤".equals(s)||"中毒".equals(s)){setHp(getHp()-(getBaseHp()/8.0));}
                        if("寄生".equals(s)){
                            int damage = this.hp/8;
                            this.setHp(this.getHp()-damage);
                            if(MainFrame.pet1 == this){ MainFrame.pet2.setHp(MainFrame.pet2.getHp()+damage);
                            }else { MainFrame.pet1.setHp(MainFrame.pet1.getHp()+damage); }
                        }
                        break;
                    case -1:
                        break;

                }
                //if(!status.get(s)) {

                    //MainFrame.playStatus(s + "开始", Pet.this == MainFrame.pet1);
                //}else {status.put(s,false);}
            }
        }

        //if(status.get("冰冻")!=null){return true;}
        //if(status.get("睡眠")!=null){return true;}

        return false;        //是否跳过回合
    }


    ArrayList<SkillInterface> skills = new ArrayList<>(4);
    int[] basePps = new int[]{0,0,0,0};
    int[] pps = new int[]{0,0,0,0};


    public void jumpThisTime(){
        int index = this==MainFrame.pet1?0:1;
        MainFrame.useSkill[index] = 5;
    }


    public void spaceHandler(int i){

        Set<String> strings = spaces.keySet();
        String[] ss = new String[strings.size()];
        strings.toArray(ss);
        for (String s:ss) {
            SpaceInterface space = spaces.get(s);
            if(space==null)return;
            if(space.getDst().getHp()<=0){
                if(this==MainFrame.pet1){
                    if(MainFrame.pet2!=space.getDst()){     //换人了
                        space.setDst(MainFrame.pet2);
                    }
                }else {
                    if(MainFrame.pet1!=space.getDst()){     //换人了
                        space.setDst(MainFrame.pet1);
                    }
                }
            }
            space.pass(i);
            if(space.isEnd())spaces.remove(s);
        }

    }


    /**
     * 移除标记
     */
    public boolean removeMark(String name){
        marks.remove(name);
        return true;
    }

    /**
     * 使用技能
     */
    public void useSkill(int id,Pet dstPet){

        if(getHp()<=0)return;

        if(id>4||id<0){spaceHandler(0);return;}       //跳过回合
        if(getMark("混乱")!=null){dstPet = this;removeMark("混乱");}
        SkillInterface skill = skills.get(id);                                      //获取此次使用的技能
        if(skill==null)return;                                                      //如果技能为空，则跳过该回合
        System.out.println(name+"使用了"+skill.getName()+"     "+skill.getDescription());


                                   //播放该技能对应的动画
        if("magic".equals(skill.getAnimal())&&skill.getTuoshou()!=null){
            //判断是否有脱手技能

            MainFrame.playBond(this,"magic",skill.getTuoshou(),this==MainFrame.pet1);


        }else {
            MainFrame.pawnRun(this,skill.getAnimal());
        }

        //System.out.println("Miss:"+dstPet.getMiss());
        //如果miss
        if(ConfigFile.MISS_START&&!skill.doSelf()&&BaseFun.is((int) Math.sqrt(((dstPet.getMiss())-getHitRate())/1.3))){MainFrame.missFlag = true;
            if(ConfigFile.CHANGE_PP&&ppDown>0)changePp(id,-(int)(basePps[id]*0.2));                                                //pp-2
            changePp(id,-1);                                                //pp-1
            if(this==MainFrame.pet1)MainFrame.skillItemLabels[id].setPp(this.pps[id]);  //设置pp槽
            MainFrame.showDamage(0,this!=MainFrame.pet1);    skill.miss(this,dstPet);  spaceHandler(1);return;}
        if(ConfigFile.MISS_START&&!skill.doSelf()&&((dstPet.getMissRestraint()>0&&BaseFun.attritube(dstPet.getType(),this)==1)||dstPet.getNoDamage()>0)){
            if(ConfigFile.CHANGE_PP&&ppDown>0)changePp(id,-(int)(basePps[id]*0.2));                                                //pp-2
            changePp(id,-1);                                                //pp-1
            if(this==MainFrame.pet1)MainFrame.skillItemLabels[id].setPp(this.pps[id]);  //设置pp槽
            MainFrame.missFlag = true;
            MainFrame.showDamage(0,this!=MainFrame.pet1);    skill.miss(this,dstPet);  spaceHandler(1);return;

        }
        //对目标使用技能
        skill.skill(this,dstPet);
        if(ConfigFile.CHANGE_PP&&ppDown>0)changePp(id,-(int)(basePps[id]*0.2));                                                //pp-2
        changePp(id,-1);                                                //pp-1
        if(this==MainFrame.pet1)MainFrame.skillItemLabels[id].setPp(this.pps[id]);  //设置pp槽

        //更新血条  (必须先更新目标血量，否则暴击标志会被浪费)
        //MainFrame.bloodChange(dstPet,dstPet.hp);
        //MainFrame.bloodChange(this,hp);

        spaceHandler(1);        //处理空间

        if(dstPet.getMark("堕落治疗")!=null){
            SkillManager.getHashMap().get("堕落治疗").miss(dstPet,this);
        }

        MainFrame.first = !MainFrame.first;

    }


    public boolean exceptionTranslate(Pet pet){
        Set<String> strings = this.status.keySet();
        for (String s: strings) {
            pet.addStatus(s);
        }
        this.status = new HashMap<>();
       return true;
    }

    public Pet() {

    }













    private AiInterface ai;


    public AiInterface getAi() {
        return ai;
    }

    public void setAi(AiInterface ai) {
        this.ai = ai;
    }

    public static Pet creatPet(String name, int[] data, String[] skillnames, JLabel pawn, BloodLine bloodLine, AiInterface aiInterface){
        Pet pet = new Pet(name, data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], skillnames, pawn, bloodLine);
        pet.ai = aiInterface;
        return pet;
    }





    public Pet(String name,int baseHp, int basePhysicalAttack, int baseMagicAttack, int basePhysicalDefense, int baseMagicDefense, int baseSpeed,int baseBoom,int baseMiss,String[] skillnames,JLabel pawn,BloodLine bloodLine) {

        String type = ConfigFile.getType(name);
        this.name = name;                                   this.type = type;
        this.baseHp = baseHp;                               this.hp = baseHp;
        this.basePhysicalAttack = basePhysicalAttack;       this.physicalAttack = basePhysicalAttack;
        this.baseMagicAttack = baseMagicAttack;             this.magicAttack = baseMagicAttack;
        this.basePhysicalDefense = basePhysicalDefense;     this.physicalDefense = basePhysicalDefense;
        this.baseMagicDefense = baseMagicDefense;           this.magicDefense = baseMagicDefense;
        this.baseSpeed = baseSpeed;                         this.speed = baseSpeed;
        this.baseBoom = baseBoom;                           this.boom = baseBoom;
        this.baseMiss = baseMiss;                           this.miss = baseMiss;
        this.baseHitRate = 300;                             this.hitRate = 300;

        this.skills = skills;
        this.pawn = pawn;
        this.bloodLine = bloodLine;
        Arrays.fill(appendStatus,0);

        for (int i = 0; i <4; i++) {
            skills.add(SkillManager.getHashMap().get(skillnames[i]));
            if(skills.get(i)==null)continue;
            this.basePps[i] = skills.get(i).getPp();
            this.pps[i] = skills.get(i).getPp();
        }


        //将宠物动画添加到缓冲区
        ImageUtils.initPetImage(this);

    }

    public void bondSkills(){

    }


    public String getStatusToString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(bigProtection>0){
            stringBuilder.append("<font color=\"#00F5FF\">");
            stringBuilder.append(" 护盾:"+bigProtection+" ");
            stringBuilder.append("</font>");
        }
        stringBuilder.append("<font color=\"#FFA500\">");
        status.keySet().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {

                stringBuilder.append(s+", ");

            }
        });
        stringBuilder.append("</font>");
        for (int i = 0; i < appendStatus.length; i++) {
            if(appendStatus[i]!=0){
                if(appendStatus[i]>0){
                    stringBuilder.append("<font color=\"#00FF00\">");
                }else {
                    stringBuilder.append("<font color=\"#FF0000\">");
                }
                stringBuilder.append(statusNames[i]+" "+(appendStatus[i]>0?"+":"")+appendStatus[i]+" ");
                stringBuilder.append("</font>");
            }
        }

        return stringBuilder.toString();
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getBasePhysicalAttack() {
        return basePhysicalAttack;
    }

    public void setBasePhysicalAttack(int basePhysicalAttack) {
        this.basePhysicalAttack = basePhysicalAttack;
    }

    public int getBaseMagicAttack() {
        return baseMagicAttack;
    }

    public void setBaseMagicAttack(int baseMagicAttack) {
        this.baseMagicAttack = baseMagicAttack;
    }

    public int getBasePhysicalDefense() {
        return basePhysicalDefense;
    }

    public void setBasePhysicalDefense(int basePhysicalDefense) {
        this.basePhysicalDefense = basePhysicalDefense;
    }

    public int getBaseMagicDefense() {
        return baseMagicDefense;
    }

    public void setBaseMagicDefense(int baseMagicDefense) {
        this.baseMagicDefense = baseMagicDefense;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(double hpd) {
        int hp = (int)hpd;

        //计算伤害
        int damage = this.hp-hp;

        if(this.getMark("堕落治疗")!=null&&damage>0){
            damage =- damage;
        }

        if(damage<0){
            //回血
            if(Recover>0){
                this.hp += (int)((-damage)*recoverPower/100);
            }else {
                this.hp += (int)((-damage));
            }
            damage = -(int)((-damage)*recoverPower/100);
        }else {
            //受伤

            //先减护盾
            if(bigProtection>=damage){
                bigProtection-=damage;
                damage = 0;
            }else {
                damage-=bigProtection;
                bigProtection = 0;
            }


            damage = Math.min(damage,getMaxDamagedPower());

            this.hp-= damage;

            MainFrame.showStatusMessage();
        }

        if(this.hp>baseHp){
            //说明回血过多
            damage += this.hp - baseHp;
            this.hp = baseHp;
        }

        if(this.hp<=0){
            this.hp=0;
            MainFrame.dead(this);
        }

        //结果需要一个血量的变化量


        MainFrame.bloodChange(this,damage);

    }

    public int getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(int physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
    }

    public int getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public JLabel getPawn() {
        return pawn;
    }

    public void setPawn(JLabel pawn) {
        this.pawn = pawn;
    }

    public BloodLine getBloodLine() {
        return bloodLine;
    }

    public void setBloodLine(BloodLine bloodLine) {
        this.bloodLine = bloodLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getAppendStatus() {
        return appendStatus;
    }

    public void setAppendStatus(int[] appendStatus) {
        this.appendStatus = appendStatus;
    }

    public Boolean getStatus(String s) {
        return status.get(s);
    }

    public Integer getStatusSize() {
        return status.size();
    }


    public void setStatus(HashMap<String, Boolean> status) {
        this.status = status;
    }

    public ArrayList<SkillInterface> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<SkillInterface> skills) {
        this.skills = skills;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[] getPps() {
        return pps;
    }

    /**
     * 恢复满pp
     */
    public void recoverAllPpByIndex(int index){
        if(index>=0&&index<4){
            changePp(index,getSkills().get(index).getPp()- getPps()[index]);
        }
    }

    public void changePp(int index,int changeValue) {
        //禁用pp玩法
        if(!ConfigFile.CHANGE_PP)return;
        this.pps[index] = this.pps[index]+changeValue;
        if(this.pps[index]<0)this.pps[index]=0;
        else if(this.pps[index]>this.basePps[index])this.pps[index]=this.basePps[index];
        if(this==MainFrame.pet1)MainFrame.skillItemLabels[index].setPp(this.pps[index]);

    }

    public static String[] getStatusNames() {
        return statusNames;
    }

    public int getNoDeBuff() {
        return noDeBuff;
    }

    public void setNoDeBuff(int noDeBuff) {
        if(noDeBuff<=this.noDeBuff)return;
        this.noDeBuff = noDeBuff;
    }

    public int getRecover() {
        return Recover;
    }

    public void setRecover(int recover,int power) {
        this.Recover = recover;
        this.recoverPower = power;
    }



    public int getBaseBoom() {
        return baseBoom;
    }

    public void setBaseBoom(int baseBoom) {
        this.baseBoom = baseBoom;
    }

    public int getBaseMiss() {
        return baseMiss;
    }

    public void setBaseMiss(int baseMiss) {
        this.baseMiss = baseMiss;
    }

    public int getBoom() {
        return boom;
    }

    public void setBoom(int boom) {
        this.boom = boom;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getBaseHitRate() {
        return baseHitRate;
    }

    public void setBaseHitRate(int baseHitRate) {
        this.baseHitRate = baseHitRate;
    }

    public int getHitRate() {
        if(getDownHitRate()>0){
            return hitRate/2;
        }
        return hitRate;
    }

    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    public int getNoexception() {
        return noexception;
    }

    public void setNoexception(int noexception) {
        if(noexception<=this.noexception)return;
        this.noexception = noexception;
    }

    public int getPpDown() {
        return ppDown;
    }

    public void setPpDown(int ppDown) {
        if(ppDown<this.ppDown)return;;
        this.ppDown = ppDown;
    }

    public int getNoDamage() {
        return noDamage;
    }

    public void setNoDamage(int noDamage) {
        if(noDamage<=this.noDamage)return;
        this.noDamage = noDamage;
    }

    public boolean isDead(){
        return hp<=0;
    }
    /**
     * 是否携带技能 pp是多少
     */
    public int isHasThisSkill(String s){
        for (int i = 0; i <skills.size(); i++) {
            if(skills.get(i).getName().equals(s)){
                return pps[i];
            }
        }
        return -1;
    }

    public int getUpPower() {
        return upPower;
    }

    public void setUpPower(int upPower) {
        if(this.upPower >= upPower)return;
        this.upPower = upPower;
    }

    public int getMaxDamagedPower() {
        if(maxDamaged>0){
            return maxDamagedPower;
        }
        return Integer.MAX_VALUE;
    }

    public int getDownHitRate() {
        return downHitRate;
    }

    /**
     * 设置下降命中率
     * @param downHitRate 回合数
     */
    public void setDownHitRate(int downHitRate) {
        this.downHitRate = downHitRate;
    }

    /**
     * 设置受到伤害的最大值
     * @param maxDamagedPower  最大值
     * @param maxDamaged    持续回合数
     */
    public void setMaxDamaged(int maxDamagedPower,int maxDamaged) {
        this.maxDamagedPower = maxDamagedPower;
        this.maxDamaged = maxDamaged;
    }

    public int getMissRestraint() {
        return missRestraint;
    }

    public void setMissRestraint(int missRestraint) {
        if(this.missRestraint >= missRestraint)return;
        this.missRestraint = missRestraint;
    }

    public int getBigProtection() {
        return bigProtection;
    }

    /**
     * 伤害经过护盾之后还剩多少
     * @param bigProtection
     * @return  护盾没有挡住的 剩余伤害
     */
    public int addBigProtection(int bigProtection) {
        this.bigProtection += bigProtection;
        if(this.bigProtection<0){
            int t = this.bigProtection;
            this.bigProtection = 0;
            MainFrame.showStatusMessage();
            return -t;
        }
        MainFrame.showStatusMessage();
        return 0;
    }
}
