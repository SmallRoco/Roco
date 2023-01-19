package content.space;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;

public class BattleSpace implements SpaceInterface {

    int maxCount = 0;
    int nowCount = 1;
    Pet resPet;
    Pet dstPet;

    public BattleSpace(int maxCount,boolean left) {
        this.maxCount = maxCount;
        if(left) {
            resPet = MainFrame.pet1;
            dstPet = MainFrame.pet2;
        }else {
            resPet = MainFrame.pet2;
            dstPet = MainFrame.pet1;
        }
    }




    @Override
    public void pass(int i) {
        if(resPet.getHp()<=0)return;
        if(nowCount==1){nowCount++; return;}
        MainFrame.pawnRun(resPet,"att1");
        int damage = BaseFun.att(resPet,dstPet,(int)(20*Math.sqrt(nowCount)),false,resPet.getType());
        resPet.setHp((int)(resPet.getHp()+damage));
        nowCount++;
    }

    @Override
    public boolean isEnd(){
        return nowCount>maxCount;
    }

    @Override
    public void beMove(){

    }

    @Override
    public void setDst(Pet dstPet) {

    }
}
