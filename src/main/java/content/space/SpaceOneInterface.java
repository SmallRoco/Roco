package content.space;

import content.Pet;

public interface SpaceOneInterface {


    void pass(Pet resPet,Pet dstPet,int count);

    void beMove(Pet resPet,Pet dstPet);

    //被异常状态跳过回合时，是否跳过该空间
    default boolean isJump(){
        return true;
    }
}
