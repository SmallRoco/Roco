package content;

import Config.LoadUtils;

import java.util.HashMap;
import java.util.List;

public class AiManager {

    static HashMap<String, AiInterface> hashMap =new HashMap<>();


    static {

        List<String> className = LoadUtils.getClassName("content.ai");

        for (String name:className) {
            AiInterface ai = (AiInterface) LoadUtils.createInstance(name);
            //System.out.println(skill);
            assert ai != null;
            hashMap.put(ai.getName(),ai);
        }

    }

    public static AiInterface getAi(String name){
        return hashMap.get(name);
    }
}
