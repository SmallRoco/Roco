package content.space;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;

/**
 * 适用于连续攻击，
 */
public class SpaceBase implements SpaceInterface{

    int maxCount = 0;
    int nowCount = 0;
    Pet resPet;
    Pet dstPet;
    SpaceOneInterface spaceOne;

    public SpaceBase(int maxCount,boolean left,SpaceOneInterface spaceOne) {
        this.maxCount = maxCount;
        if(left) {
            resPet = MainFrame.pet1;
            dstPet = MainFrame.pet2;
        }else {
            resPet = MainFrame.pet2;
            dstPet = MainFrame.pet1;
        }

        this.spaceOne = spaceOne;
        //spaceOne.pass(resPet,dstPet,nowCount);
    }




    @Override
    public void pass(int i) {
        nowCount++;
        if(dstPet.getHp()<=0)return;
        spaceOne.pass(resPet,dstPet,nowCount);
    }

    @Override
    public boolean isEnd(){
        return nowCount==maxCount;
    }

    @Override
    public void beMove(Pet resPet,Pet dstPet){
        spaceOne.beMove(resPet,dstPet);
    }

    @Override
    public void setDst(Pet dstPet) {
        this.dstPet = dstPet;

    }

    @Override
    public Pet getDst(){
        return dstPet;
    }


}
