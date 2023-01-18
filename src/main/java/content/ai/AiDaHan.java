package content.ai;

import UI.MainFrame;
import content.AiInterface;
import content.BaseFun;
import content.Pet;

import java.util.Objects;

public class AiDaHan extends BaseAi {

    int i = 5;
    int count = 0;
    @Override
    public String[] getSkllNames() {
        return new String[]{"怪神之力","敌众水澧","恩怨","今朝醉"};

    }

    @Override
    public String getName() {
        return "大寒";
    }

    /*@Override
    public void useSkill(Pet resPet, Pet dstPet) {

        int index = 3;

        i--;

        if(count==0){
            index = 1;
        } else if(dstPet.getNoexception()<=0&&BaseFun.is(25)){
            index = 0;
        }else if(resPet.getHp()<resPet.getBaseHp()*0.4&&BaseFun.is((5-i)*20)){
            index = 1;
            i = 5;
        }else if(resPet.getAppendStatus()[0]>=3){
            index = 2;
        }
        count++;

        if(i<0)i=0;

        MainFrame.useSkill[BaseFun.getIndexPawn(resPet)] = index;
    }*/
}
