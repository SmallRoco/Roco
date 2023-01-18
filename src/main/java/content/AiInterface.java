package content;

public interface AiInterface{


    String getName();

    void useSkill(Pet resPet,Pet dstPet);

    default String[] getSkllNames(){
        return null;
    }
}
