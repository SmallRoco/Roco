package Config;

import Utils.BaseFunction;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConfigFile {

    private static HashMap<String,AttributeAdd> petAttriubuteAdd = new HashMap<>();
    public static void setPetAttriubuteAdd(String name,AttributeAdd attributeAdd){
        petAttriubuteAdd.put(name,attributeAdd);
    }
    public static AttributeAdd getPetAttributeAdd(String name){
        return petAttriubuteAdd.get(name);
    }




    public final static String[] types = new String[]{"冰","恶魔","幽","土","水","石","普通","龙","火","毒","电","虫","草","武","机械","萌","翼","光","神火","神草","神水"};

    public  static String[] PetNames;

    public final static String[] status = new String[]{"冰冻","睡眠","混乱","麻痹","魅惑","烧伤","中毒","剧毒","束缚"};
    public static HashMap<String,String[]> petCanSkills = new HashMap<>();

    public static HashMap<String, String[]> typeKeZhi = new HashMap<>();
    public static HashMap<String, String[]> typeDiKang = new HashMap<>();
    public static HashMap<String,String> petTypes = new HashMap<>();
    public static ArrayList<String> petIdName = new ArrayList<>();

    public static HashMap<String,Integer> expression = new HashMap<>();

    public static int get(String s){
        return expression.get(s);
    }

    static {
        PetNames = new File(AllConfig.rootPath+"宠物/").list();



        expression.put("暂时",2);
        expression.put("短暂",2);
        expression.put("短期",2);
        expression.put("短时间",2);

        typeKeZhi.put("火",new String[]{"草","冰","虫","机械"});
        typeKeZhi.put("草",new String[]{"水","土","石"});
        typeKeZhi.put("水",new String[]{"火","土","石"});
        typeKeZhi.put("电",new String[]{"水","翼"});
        typeKeZhi.put("冰",new String[]{"草","土","翼","龙"});
        typeKeZhi.put("武",new String[]{"普通","冰","石","恶魔","机械"});
        typeKeZhi.put("毒",new String[]{"草"});
        typeKeZhi.put("土",new String[]{"火","电","毒","石","机械"});
        typeKeZhi.put("翼",new String[]{"草","武","虫"});
        typeKeZhi.put("萌",new String[]{"武","毒"});
        typeKeZhi.put("虫",new String[]{"草","萌","恶魔"});
        typeKeZhi.put("石",new String[]{"火","冰","翼","虫"});
        typeKeZhi.put("幽",new String[]{"萌","幽"});
        typeKeZhi.put("龙",new String[]{"龙"});
        typeKeZhi.put("恶魔",new String[]{"萌","幽"});
        typeKeZhi.put("机械",new String[]{"冰","石"});



            //----------------------------抵抗



        typeDiKang.put("普通",new String[]{"石","机械"});
        typeDiKang.put("火",new String[]{"水","石","龙"});
        typeDiKang.put("草",new String[]{"火","翼","草","毒","虫","龙","机械"});
        typeDiKang.put("水",new String[]{"龙","草"});
        typeDiKang.put("电",new String[]{"电","草","龙"});
        typeDiKang.put("冰",new String[]{"水","火","冰","机械"});
        typeDiKang.put("武",new String[]{"毒","翼","萌","虫"});
        typeDiKang.put("毒",new String[]{"毒","土","石","幽"});
        typeDiKang.put("土",new String[]{"草","虫"});
        typeDiKang.put("翼",new String[]{"电","石","机械"});
        typeDiKang.put("萌",new String[]{"萌","机械"});
        typeDiKang.put("虫",new String[]{"火","武","毒","翼","幽","机械"});
        typeDiKang.put("石",new String[]{"武","土","机械"});
        typeDiKang.put("幽",new String[]{"恶魔","机械"});
        typeDiKang.put("龙",new String[]{"机械"});
        typeDiKang.put("恶魔",new String[]{"武","恶魔","机械"});
        typeDiKang.put("机械",new String[]{"水","火","电","机械"});




        putTypes("大寒","水");
        putTypes("圣水守护","水");
        putTypes("烈火战神","火");
        putTypes("圣灵草王","草");
        putTypes("晨曦凯露儿","光");
        putTypes("耀阳土王","土");
        putTypes("暗之雪莉","恶魔");
        putTypes("七夕","水");
        putTypes("武斗酷猫","草");
        putTypes("福熙水灵","水");
        putTypes("赵云子龙","武");
        putTypes("汐纱水王","水");
        putTypes("光之雪莉","光");
        putTypes("魅影幽兰","冰");


        petCanSkills.put("大寒",new String[]{"怪神之力","水之护盾","净化","恩怨","沉没打击","今朝醉","敌众水澧","灯影幽峻"});
        petCanSkills.put("圣水守护",new String[]{"猛烈撞击","超声攻击","尾巴挠痒","气泡术","钢贝壳","水之神枪","狂咬","挣脱","护盾术","水之波纹","水打击","头槌爆","祭祀之雨","强力护盾","光栅炮","水波术","狂暴水元素","缓冲","窒息","淹没","急速","水之护盾"});
        petCanSkills.put("烈火战神",new String[]{"龙之利爪","空气切割","暗之爪","爆击枪","烟雾弥漫","暗黑诅咒","火之刃","龙之爪牙","切裂之刀","火焰喷发","火焰漩涡","飞翅连击","热力爆弹","烈焰冲锋","巨焰吞噬","魔焰瞬击","怒气中烧","搏命狂暴","烈焰焚烧","爆裂灼烧","火烧眉毛"});
        petCanSkills.put("圣灵草王",new String[]{"圣藤控制","圣藤之灭","灵蔓缠绕","灵蔓盾击","自然之友","草之守护","蔓藤舞动","召唤草灵"});
        petCanSkills.put("晨曦凯露儿",new String[]{"流光护盾","光之守护","破光强袭","幻光千影","扶光东起","华景长明","红镜灼炎","青霄丹霞"});
        petCanSkills.put("耀阳土王",new String[]{"大地祈福","泥浆弹","泥浆飞溅","三清化土","耀阳颂","圣辉地焰","坚土守护","散藤","地爆天星"});
        petCanSkills.put("暗之雪莉",new String[]{"新月乱舞","暗夜旋律","互相伤害","深度睡眠","堕落治疗","深渊魔能","黑洞","幻月黑泽","魔女之吻","赤月魅焰","灵域风暴"});
        petCanSkills.put("七夕",new String[]{"水之神枪","水之波纹","液态水环","水之护盾","清水魂伤","水龙卷","明镜止水","冰夕水灵","七巧祈愿"});
        petCanSkills.put("武斗酷猫",new String[]{"叫声","猛烈撞击","藤之鞭","寄生种子","毒雾","催眠粉","突进","飞叶刀","甜蜜香气","魔法增效","莽撞","清醒","种子炸弹","花瓣群舞","光合作用","阳光烈焰","破土之力","嗜睡吸血","暴力汲取","绿色幻境","阳光护盾","慧根盾击"});
        petCanSkills.put("福熙水灵",new String[]{"净化","祈愿","洗礼","泄洪","水龙卷","甘霖普降","福盈门","阖家欢","春满枝","步青云"});
        petCanSkills.put("赵云子龙",new String[]{"苍龙追月","万夫莫敌","龙战于野","真龙乱舞","虚诱掩杀","蔚蓝龙息","灵域风暴","天若有情","一骑讨","龙之传承"});
        petCanSkills.put("汐纱水王",new String[]{"冰冻","圣洁","恩泽","洗礼","净化","牺牲","碧天清晓","湍玉漱石","烟波长静","灵渊移星"});
        petCanSkills.put("光之雪莉",new String[]{"破魔之光","希望之光","光之守护","破魔圣光","极光十字","破魔圣光箭","柔如彩虹","圣光祝福","永恒思念"});
        petCanSkills.put("魅影幽兰",new String[]{"雪隐","纯白","水汽凝结","寒冰裁决","鼓舞","寒霜涅槃","寒冰","凝霜","冰天雪地"});
    }

    public static void putTypes(String key,String value){
        petTypes.put(key,value);
        petIdName.add(key);

    }

    public static void main(String[] args) {
        String[] split = BaseFunction.getSysClipboardText().split("\n");
        String s = "";
        for (int i = 0; i <split.length; i+=5) {
            s+="\""+split[i]+"\",";
        }
        System.out.println(s);

        BaseFunction.setSysClipboardText(s);
    }

    public static String getType(String name){
        return petTypes.get(name);
    }

    //是否开启环境
    public final static boolean ENVIRONMENT_START = true;
    //是否开启删pp 回pp
    public final static boolean CHANGE_PP = true;
    //是否开启暴击
    public final static boolean BOOM_START = true;
    //是否开启闪避
    public final static boolean MISS_START = true;
    //是否开标记
    public final static boolean MARK_START = true;

}
