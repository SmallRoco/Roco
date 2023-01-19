package content.status;

import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusHunLuan implements StatusInterface {

    int count = 4;
    @Override
    public String getName() {
        return "混乱";
    }
    //解除的概率
    final int P = 15;

    @Override
    public int on(Pet pet) {

        if(count==-1)count=4;
        if(count--<=0){return 1;}
        if(BaseFun.is(100-P)){
            if(BaseFun.is(50)){
                pet.addMark("混乱",1);return -1;
            }
            return 0;
        }

        return 1;
    }
}
