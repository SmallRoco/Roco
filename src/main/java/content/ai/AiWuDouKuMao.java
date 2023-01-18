package content.ai;

import UI.MainFrame;
import content.AiInterface;
import content.BaseFun;
import content.Pet;

public class AiWuDouKuMao extends BaseAi {

    @Override
    public String[] getSkllNames() {
        return new String[]{"慧根盾击","催眠粉","阳光烈焰","魔法增效"};
    }

    @Override
    public String getName() {
        return "武斗酷猫";
    }

    @Override
    public void useSkill(Pet resPet, Pet dstPet) {

        int index = 2;

        if(dstPet.getStatus("睡眠")==null&&dstPet.getNoexception()>0&&dstPet.getMark("睡眠免疫")==null){
            index = 1;
        }else if(dstPet.getStatus("睡眠")!=null&&resPet.getAppendStatus()[1]<4){
            index = 3;
        }else if(dstPet.getHp()<100||resPet.haveSpace("阳光烈焰")||resPet.getHp()<250){
            index = 0;
        }

        MainFrame.useSkill[BaseFun.getIndexPawn(resPet)] = index;
    }
}
