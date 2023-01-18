package content;

import Config.LoadUtils;
import content.StatusInterface;
import content.status.StatusBingDong;

import java.util.HashMap;
import java.util.List;

public class StatusManager {

    static HashMap<String, StatusInterface> hashMap =new HashMap<>();


    public static StatusInterface getStatus(String name) {
        return hashMap.get(name);
    }

    static {

        List<String> className = LoadUtils.getClassName("content.status");

        for (String name:className) {
            StatusInterface status = (StatusInterface) LoadUtils.createInstance(name);
            //System.out.println(status);
            assert status != null;
            hashMap.put(status.getName(),status);
        }

        //hashMap.put("沉没打击",new SkillChenMoDaJI());
        //hashMap.put("光合作用",new SkillGuangHeZuoYong());
        //hashMap.put("水汽凝结",new SkillShuiQINingJie());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
    }
}
