package content.ai;

import UI.MainFrame;
import content.AiInterface;
import content.BaseFun;
import content.Pet;

public class BaseAi implements AiInterface {

    @Override
    public String getName() {
        return "基础AI";
    }

    @Override
    public void useSkill(Pet resPet, Pet dstPet) {
        MainFrame.useSkill[BaseFun.getIndexPawn(resPet)] = BaseFun.getRandom(4);
    }
}
