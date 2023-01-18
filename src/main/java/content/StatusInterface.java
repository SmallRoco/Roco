package content;

import content.Pet;

public interface StatusInterface {

    String getName();

    //解除状态返回1
    //继续状态返回0
    //继续状态，但本回合未触发返回-1
    int on(Pet pet);


}
