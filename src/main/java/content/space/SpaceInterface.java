package content.space;

import content.Pet;

public interface SpaceInterface {


    int maxCount = 0;
    int nowCount = 0;


    /**
     *
     * @param i  i=0 跳过
     */
    void pass(int i);

    boolean isEnd();

    void beMove(Pet resPet,Pet dstPet);

    void setDst(Pet dstPet);


    default Pet getDst(){
        return null;
    }
}
