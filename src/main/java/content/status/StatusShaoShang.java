package content.status;

import UI.MainFrame;
import content.BaseFun;
import content.Pet;
import content.StatusInterface;

public class StatusShaoShang implements StatusInterface {
    @Override
    public String getName() {
        return "烧伤";
    }

    @Override
    public int on(Pet pet) {


        return 0;
    }
}
