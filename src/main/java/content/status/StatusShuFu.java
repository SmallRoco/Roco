package content.status;

import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusShuFu implements StatusInterface {
    @Override
    public String getName() {
        return "束缚";
    }

    public int count = 4;

    @Override
    public int on(Pet pet) {

        if(count==-1)count=4;
        if(count--<=0){
            return 1;
        }

        return 0;
    }
}
