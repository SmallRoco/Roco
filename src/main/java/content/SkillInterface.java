package content;

import content.Pet;

public interface SkillInterface {

    public String name = "";
    public String animal = "";
    public int speed = 0;
    public boolean physical = false;
    public String tuoshou = "";
    //属性
    public String type = "";
    //威力
    public int power = 0;
    //pp
    public int pp = 0;
    //描述
    public String description = null;

    String getAnimal();


    String getName();
    int getSpeed();
    boolean getPhysical();
    String getType();
    int getPower();
    int getPp();
    String getDescription();

    void skill(Pet resPet, Pet dstPet);
    void setDescription(String description);
    default String getTuoshou(){return null;};
    //magic  必须写 脱手技能
    default boolean doSelf(){return false;}

    default void miss(Pet resPet, Pet dstPet){

    }

}
