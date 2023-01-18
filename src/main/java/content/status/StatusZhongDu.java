package content.status;

import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusZhongDu implements StatusInterface {

    @Override
    public String getName() {
        return "中毒";
    }


    @Override
    public int on(Pet pet) {

        return 0;
    }
}
