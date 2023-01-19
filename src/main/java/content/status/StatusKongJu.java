package content.status;

import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusKongJu implements StatusInterface {

    int count = 4;
    @Override
    public String getName() {
        return "恐惧";
    }
    //解除的概率
    final int P = 28;

    @Override
    public int on(Pet pet) {

        if(count==-1)count=4;
        if(count--<=0){return 1;}
        if(count<=3&&BaseFun.is(100-P)){
            pet.jumpThisTime();
            return 0;
        }

        return 1;
    }
}
