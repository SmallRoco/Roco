package content.ai;

import UI.MainFrame;
import content.AiInterface;
import content.BaseFun;
import content.Pet;

public class AiQiXi extends BaseAi {

    @Override
    public String[] getSkllNames() {
        return new String[]{"冰夕水灵","明镜止水","水龙卷","七巧祈愿"};
    }

    @Override
    public String getName() {
        return "七夕";
    }
    int count = 0;

    /*@Override
    public void useSkill(Pet resPet, Pet dstPet) {

        int index = 2;

        if(count==0)index = 1;

        else if(dstPet.getStatus("冰冻")==null&&dstPet.getNoexception()>0&&dstPet.getMark("冰冻免疫")==null){
            index = 0;
        }else if(resPet.getHp()<resPet.getBaseHp()*0.5&&BaseFun.is(75)){
            index = 1;
        }else if(dstPet.getStatus("冰冻")!=null||BaseFun.is(50)){
            index = 3;
        }

        count++;
        MainFrame.useSkill[BaseFun.getIndexPawn(resPet)] = index;
    }*/
}
