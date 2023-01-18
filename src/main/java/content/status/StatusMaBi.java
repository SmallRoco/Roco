package content.status;

import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusMaBi implements StatusInterface {

    @Override
    public String getName() {
        return "麻痹";
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
