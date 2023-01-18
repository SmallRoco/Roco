package content.status;

import content.BaseFun;
import content.Pet;
import content.SkillInterface;
import content.StatusInterface;

public class StatusShuiMian implements StatusInterface {

    @Override
    public String getName() {
        return "睡眠";
    }
    int count = 4;

    @Override
    public int on(Pet pet) {
        if(count--==0||BaseFun.is(25)){
            return 1;            //解除睡眠
        }

        pet.jumpThisTime();         //跳过回合
        return 0;
    }
}
