package content.ai;

import UI.MainFrame;
import content.AiInterface;
import content.BaseFun;
import content.Pet;

public class AIShengShuiShouHu extends BaseAi  {

    @Override
    public String[] getSkllNames() {
        return new String[]{"水之护盾","淹没","光栅炮","窒息"};
    }

    @Override
    public String getName() {
        return "圣水守护";
    }

    /*@Override
    public void useSkill(Pet resPet, Pet dstPet) {

        int index = 2;

        if(!resPet.haveSpace("窒息")){
            index = 3;
        }else if(!resPet.haveSpace("淹没")){
            index = 1;
        }else if(!resPet.haveSpace("水之护盾")){
            index = 0;
        }

        MainFrame.useSkill[BaseFun.getIndexPawn(resPet)] = index;
    }*/
}
