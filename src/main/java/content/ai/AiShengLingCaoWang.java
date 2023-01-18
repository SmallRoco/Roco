package content.ai;

import UI.MainFrame;
import content.AiInterface;
import content.BaseFun;
import content.Pet;

public class AiShengLingCaoWang extends BaseAi  {

    @Override
    public String[] getSkllNames() {
        return new String[]{"圣藤控制","自然之友","草之守护","圣藤之灭"};
    }

    @Override
    public String getName() {
        return "圣灵草王";
    }
    int count = 0;

    /*@Override
    public void useSkill(Pet resPet, Pet dstPet) {

        int index = 3;


        if(dstPet.getStatus("睡眠")==null&&dstPet.getNoexception()<=0&&!dstPet.getType().contains("草")&&dstPet.getMark("睡眠免疫")==null){
            index = 0;
        }else if(resPet.getBaseHp()<resPet.getBaseHp()*0.45&&BaseFun.is(50)){
            index = 1;
        }else if(!resPet.haveSpace("草之守护")&&BaseFun.is(50)){
            index = 2;
        }

        count++;
        MainFrame.useSkill[BaseFun.getIndexPawn(resPet)] = index;
    }*/
}
