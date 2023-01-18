package content;

import Config.LoadUtils;
import content.skill.bing.SkillShuiQINingJie;
import content.skill.cao.SkillGuangHeZuoYong;
import content.skill.shui.SkillChenMoDaJI;
import content.skill.shui.SkillJinZhaoZui;

import java.util.HashMap;
import java.util.List;

public class SkillManager {

    static HashMap<String, SkillInterface> hashMap =new HashMap<>();


    public static HashMap<String, SkillInterface> getHashMap() {
        return hashMap;
    }

    static {

        List<String> className = LoadUtils.getClassName("content.skill");

        for (String name:className) {
            SkillInterface skill = (SkillInterface) LoadUtils.createInstance(name);
            //System.out.println(skill);
            assert skill != null;
            hashMap.put(skill.getName(),skill);
        }


        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());
        //hashMap.put("今朝醉",new SkillJinZhaoZui());

        for (SkillInterface skill:hashMap.values()) {
            skill.setDescription((skill.getPhysical()?"   [物理]   ":"   [魔法]   ")+skill.getDescription());
        }
    }
}
