package content.skill.cao;
import content.Pet;
import content.SkillInterface;

public class SkillGuangHeZuoYong implements SkillInterface {
    String name = "光合作用";
    String animal = "magic";
    boolean physical = false;
    int speed = 10;
    String type = "草";
    int power = 0;
    int pp = 5;
    String description = "回复自身一定的HP。";

    @Override
    public String getAnimal() {
        return animal;
    }
    @Override
    public void skill(Pet resPet, Pet dstPet) {
        //回血40%
        resPet.setHp((int)(resPet.getHp()+resPet.getBaseHp()*0.4));
    }
    @Override public String getName() {
        return name;
    }
    @Override public boolean getPhysical() {
        return physical;
    }
    @Override public int getSpeed() {
        return speed;
    }
    @Override public String getType() {
        return type;
    }
    @Override public int getPower() {
        return power;
    }
    @Override public int getPp() {
        return pp;
    }
    @Override public String getDescription() { return description; }
    @Override public void setDescription(String description) { this.description = description; }
    @Override public boolean doSelf(){return true;}





}
