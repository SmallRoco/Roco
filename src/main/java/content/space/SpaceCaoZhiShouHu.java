package content.space;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;

public class SpaceCaoZhiShouHu implements SpaceInterface{

    int maxCount = 0;
    int nowCount = 1;
    Pet resPet;
    Pet dstPet;

    public SpaceCaoZhiShouHu(int maxCount,boolean left) {
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
        resPet.statusChange(BaseFun.getRandom(7),2);

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
        this.dstPet =dstPet;
    }

    @Override
    public Pet getDst(){
        return dstPet;
    }

}
