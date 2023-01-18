package content.status;

import content.Pet;
import content.BaseFun;
import content.StatusInterface;

public class StatusBingDong implements StatusInterface {


    @Override
    public String getName() {
        return "冰冻";
    }

    @Override
    public int on(Pet pet) {

        if(BaseFun.is(10)){
            return 1;        //状态移除
        }

        pet.jumpThisTime();
        return 0;
    }
}
