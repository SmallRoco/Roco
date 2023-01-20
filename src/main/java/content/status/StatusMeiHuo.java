package content.status;

import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusMeiHuo implements StatusInterface {

    @Override
    public String getName() {
        return "魅惑";
    }

    @Override
    public int on(Pet pet) {
        if(BaseFun.is(50)){
            pet.jumpThisTime();
            return 0;
        }

        return -1;
    }
}
