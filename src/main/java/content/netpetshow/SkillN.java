package content.netpetshow;

import content.NextPetShowInterface;
import content.Pet;

public class SkillN implements NextPetShowInterface {

    String[] contents;
    int[] ints;

    public SkillN(String[] strings,int[] ints){
        this.contents = strings;
        this.ints = ints;
    }
    @Override
    public void init(Pet pet) {
        for (int i = 0; i <contents.length; i++) {
            if(contents[i].equals("addBigProtection")){
                pet.addBigProtection(ints[i]);
            }else if(contents[i].equals("削弱")){
                for (int j = 0; j <7; j++) {
                    pet.statusChange(j,-1);
                }
            }else if(contents[i].equals("冰冻")){
                pet.addStatus("冰冻");
            }
        }
    }
}
