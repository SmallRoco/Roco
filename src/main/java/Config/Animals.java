package Config;

import java.util.HashMap;

public class Animals {

    public static String rootPath = "F:/洛克王国资源/";

    public static HashMap<String, Animal> animals = new HashMap();


    /**
     * 往右是 -10
     * 上是   -10
     */
    public static final String[] actionNames = new String[]{"idle","att1","magic","show","dead"};
    static {
        /*Animal animal = new Animal();
        HashMap<String, Action> actions = animal.getActions();
        actions.put("idle",new Action("idle.gif",0));
        actions.put("att1",new Action("att1.gif",42));
        actions.put("magic",new Action("magic.gif",42));
        //actions.put("magic1",new Action("magic1.gif",48,new int[]{0,-25}));
        actions.put("show",new Action("show.gif",25));
        actions.put("dead",new Action("dead.gif",44));
        animals.put("大寒",animal);
        animal = new Animal();
        actions = animal.getActions();
        actions.put("idle",new Action("idle.gif",0));
        actions.put("att1",new Action("att1.gif",41));
        actions.put("magic",new Action("magic.gif",46));
        //actions.put("magic1",new Action("magic1.gif",48,new int[]{0,-25}));
        actions.put("show",new Action("show.gif",25));
        actions.put("dead",new Action("dead.gif",61));
        animals.put("七夕",animal);
        animal = new Animal();
        actions = animal.getActions();
        actions.put("idle",new Action("idle.gif",0));
        actions.put("att1",new Action("att1.gif",53));
        actions.put("magic",new Action("magic.gif",48));
        actions.put("magic1",new Action("magic1.gif",11));
        actions.put("show",new Action("show.gif",25));
        actions.put("dead",new Action("dead.gif",50));
        animals.put("暗之雪莉",animal);
        animal = new Animal();
        actions = animal.getActions();
        actions.put("idle",new Action("idle.gif",0));
        actions.put("att1",new Action("att1.gif",41));
        actions.put("magic",new Action("magic.gif",39));
        actions.put("show",new Action("show.gif",25));
        actions.put("dead",new Action("dead.gif",37));
        animals.put("耀阳土王",animal);
        animal = new Animal();
        actions = animal.getActions();
        actions.put("idle",new Action("idle.gif",0));
        actions.put("att1",new Action("att1.gif",43));
        actions.put("magic",new Action("magic.gif",43));
        actions.put("show",new Action("show.gif",25));
        actions.put("dead",new Action("dead.gif",35));
        animals.put("晨曦凯露儿",animal);
        animal = new Animal();
        actions = animal.getActions();
        actions.put("idle",new Action("idle.gif",0));
        actions.put("att1",new Action("att1.gif",50));
        actions.put("magic",new Action("magic.gif",40));
        actions.put("show",new Action("show.gif",25));
        actions.put("dead",new Action("dead.gif",24));
        animals.put("圣灵草王",animal);*/

    }

    public static Action getAction(String animalname,String actionname){
        Animal animal = null;
        if((animal=animals.get(animalname))!=null){
            Action action = null;
            if((action=animal.getActions().get(actionname))!=null){
                //System.out.println(new Date().toString()+"------->"+animalname+":"+actionname);
                return new Action(rootPath+"宠物/"+animalname+"/"+actionname+".gif",action.getTime(),0);
            }else return null;
        }else return null;
    }



}

class Animal{

    public HashMap<String,Action> actions = new HashMap();

    public HashMap<String, Action> getActions() {
        return actions;
    }
}
