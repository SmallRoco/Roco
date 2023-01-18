import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class JString {
    private static ArrayList pinyin = new ArrayList(395);

    //首字母大写
    public static String captureName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;
    }
    /**
     * 该方法返回一个字符串的拼音，对于要做敏感字
     * 检查时应该一个字一个字来获取其拼音以免无法
     * 得知每个字对应的拼音。
     * @param word
     * @return String
     */
    public static String getPinyin(String word) {
        String pinyin = "";
        for (int i = 0; i < word.length(); i++) {
            pinyin+= captureName(getPinyin2(getCode(word.charAt(i))));

        }
        return pinyin;
    }
    public static String getPinyinSmall(String word) {
        String pinyin = "";
        for (int i = 0; i < word.length(); i++) {
            pinyin+= getPinyin2(getCode(word.charAt(i)));

        }
        return pinyin;
    }
    /**
     * 该方法通过DBCS的编码值到哈希表中查询得到对应的拼音串
     * @param hz
     * @return String
     */
    protected static String getPinyin2(int hz) {
        String py = "";
        if (hz > 0 && hz < 160)
            py += hz;
            //else if (hz < -20319 || hz > -10247);
        else if (hz <= -10247 && hz >= -20319){
            PinyinCode pc = null;
            int i = pinyin.size() - 1;
            for (; i >= 0; i--) {
                pc = (PinyinCode) pinyin.get(i);
                if (pc.code <= hz)
                    break;
            }
            if (i >= 0)
                py = pc.pinyin;
        }
        return py;
    }

    /**
     * 该方法返回一个字符的DBCS编码值
     * @param cc
     * @return int
     */
    protected static int getCode(char cc) {
        byte[] bs = new byte[0];
        try {
            bs = String.valueOf(cc).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int code = (bs[0] << 8) | (bs[1] & 0x00FF);
        if(bs.length < 2)
            code = (int)cc;
        bs = null;
        return code;
    }




    static {
        pinyin.add(new PinyinCode("a", -20319));
        pinyin.add(new PinyinCode("ai", -20317));
        pinyin.add(new PinyinCode("an", -20304));
        pinyin.add(new PinyinCode("ang", -20295));
        pinyin.add(new PinyinCode("ao", -20292));
        pinyin.add(new PinyinCode("ba", -20283));
        pinyin.add(new PinyinCode("bai", -20265));
        pinyin.add(new PinyinCode("ban", -20257));
        pinyin.add(new PinyinCode("bang", -20242));
        pinyin.add(new PinyinCode("bao", -20230));
        pinyin.add(new PinyinCode("bei", -20051));
        pinyin.add(new PinyinCode("ben", -20036));
        pinyin.add(new PinyinCode("beng", -20032));
        pinyin.add(new PinyinCode("bi", -20026));
        pinyin.add(new PinyinCode("bian", -20002));
        pinyin.add(new PinyinCode("biao", -19990));
        pinyin.add(new PinyinCode("bie", -19986));
        pinyin.add(new PinyinCode("bin", -19982));
        pinyin.add(new PinyinCode("bing", -19976));
        pinyin.add(new PinyinCode("bo", -19805));
        pinyin.add(new PinyinCode("bu", -19784));
        pinyin.add(new PinyinCode("ca", -19775));
        pinyin.add(new PinyinCode("cai", -19774));
        pinyin.add(new PinyinCode("can", -19763));
        pinyin.add(new PinyinCode("cang", -19756));
        pinyin.add(new PinyinCode("cao", -19751));
        pinyin.add(new PinyinCode("ce", -19746));
        pinyin.add(new PinyinCode("ceng", -19741));
        pinyin.add(new PinyinCode("cha", -19739));
        pinyin.add(new PinyinCode("chai", -19728));
        pinyin.add(new PinyinCode("chan", -19725));
        pinyin.add(new PinyinCode("chang", -19715));
        pinyin.add(new PinyinCode("chao", -19540));
        pinyin.add(new PinyinCode("che", -19531));
        pinyin.add(new PinyinCode("chen", -19525));
        pinyin.add(new PinyinCode("cheng", -19515));
        pinyin.add(new PinyinCode("chi", -19500));
        pinyin.add(new PinyinCode("chong", -19484));
        pinyin.add(new PinyinCode("chou", -19479));
        pinyin.add(new PinyinCode("chu", -19467));
        pinyin.add(new PinyinCode("chuai", -19289));
        pinyin.add(new PinyinCode("chuan", -19288));
        pinyin.add(new PinyinCode("chuang", -19281));
        pinyin.add(new PinyinCode("chui", -19275));
        pinyin.add(new PinyinCode("chun", -19270));
        pinyin.add(new PinyinCode("chuo", -19263));
        pinyin.add(new PinyinCode("ci", -19261));
        pinyin.add(new PinyinCode("cong", -19249));
        pinyin.add(new PinyinCode("cou", -19243));
        pinyin.add(new PinyinCode("cu", -19242));
        pinyin.add(new PinyinCode("cuan", -19238));
        pinyin.add(new PinyinCode("cui", -19235));
        pinyin.add(new PinyinCode("cun", -19227));
        pinyin.add(new PinyinCode("cuo", -19224));
        pinyin.add(new PinyinCode("da", -19218));
        pinyin.add(new PinyinCode("dai", -19212));
        pinyin.add(new PinyinCode("dan", -19038));
        pinyin.add(new PinyinCode("dang", -19023));
        pinyin.add(new PinyinCode("dao", -19018));
        pinyin.add(new PinyinCode("de", -19006));
        pinyin.add(new PinyinCode("deng", -19003));
        pinyin.add(new PinyinCode("di", -18996));
        pinyin.add(new PinyinCode("dian", -18977));
        pinyin.add(new PinyinCode("diao", -18961));
        pinyin.add(new PinyinCode("die", -18952));
        pinyin.add(new PinyinCode("ding", -18783));
        pinyin.add(new PinyinCode("diu", -18774));
        pinyin.add(new PinyinCode("dong", -18773));
        pinyin.add(new PinyinCode("dou", -18763));
        pinyin.add(new PinyinCode("du", -18756));
        pinyin.add(new PinyinCode("duan", -18741));
        pinyin.add(new PinyinCode("dui", -18735));
        pinyin.add(new PinyinCode("dun", -18731));
        pinyin.add(new PinyinCode("duo", -18722));
        pinyin.add(new PinyinCode("e", -18710));
        pinyin.add(new PinyinCode("en", -18697));
        pinyin.add(new PinyinCode("er", -18696));
        pinyin.add(new PinyinCode("fa", -18526));
        pinyin.add(new PinyinCode("fan", -18518));
        pinyin.add(new PinyinCode("fang", -18501));
        pinyin.add(new PinyinCode("fei", -18490));
        pinyin.add(new PinyinCode("fen", -18478));
        pinyin.add(new PinyinCode("feng", -18463));
        pinyin.add(new PinyinCode("fo", -18448));
        pinyin.add(new PinyinCode("fou", -18447));
        pinyin.add(new PinyinCode("fu", -18446));
        pinyin.add(new PinyinCode("ga", -18239));
        pinyin.add(new PinyinCode("gai", -18237));
        pinyin.add(new PinyinCode("gan", -18231));
        pinyin.add(new PinyinCode("gang", -18220));
        pinyin.add(new PinyinCode("gao", -18211));
        pinyin.add(new PinyinCode("ge", -18201));
        pinyin.add(new PinyinCode("gei", -18184));
        pinyin.add(new PinyinCode("gen", -18183));
        pinyin.add(new PinyinCode("geng", -18181));
        pinyin.add(new PinyinCode("gong", -18012));
        pinyin.add(new PinyinCode("gou", -17997));
        pinyin.add(new PinyinCode("gu", -17988));
        pinyin.add(new PinyinCode("gua", -17970));
        pinyin.add(new PinyinCode("guai", -17964));
        pinyin.add(new PinyinCode("guan", -17961));
        pinyin.add(new PinyinCode("guang", -17950));
        pinyin.add(new PinyinCode("gui", -17947));
        pinyin.add(new PinyinCode("gun", -17931));
        pinyin.add(new PinyinCode("guo", -17928));
        pinyin.add(new PinyinCode("ha", -17922));
        pinyin.add(new PinyinCode("hai", -17759));
        pinyin.add(new PinyinCode("han", -17752));
        pinyin.add(new PinyinCode("hang", -17733));
        pinyin.add(new PinyinCode("hao", -17730));
        pinyin.add(new PinyinCode("he", -17721));
        pinyin.add(new PinyinCode("hei", -17703));
        pinyin.add(new PinyinCode("hen", -17701));
        pinyin.add(new PinyinCode("heng", -17697));
        pinyin.add(new PinyinCode("hong", -17692));
        pinyin.add(new PinyinCode("hou", -17683));
        pinyin.add(new PinyinCode("hu", -17676));
        pinyin.add(new PinyinCode("hua", -17496));
        pinyin.add(new PinyinCode("huai", -17487));
        pinyin.add(new PinyinCode("huan", -17482));
        pinyin.add(new PinyinCode("huang", -17468));
        pinyin.add(new PinyinCode("hui", -17454));
        pinyin.add(new PinyinCode("hun", -17433));
        pinyin.add(new PinyinCode("huo", -17427));
        pinyin.add(new PinyinCode("ji", -17417));
        pinyin.add(new PinyinCode("jia", -17202));
        pinyin.add(new PinyinCode("jian", -17185));
        pinyin.add(new PinyinCode("jiang", -16983));
        pinyin.add(new PinyinCode("jiao", -16970));
        pinyin.add(new PinyinCode("jie", -16942));
        pinyin.add(new PinyinCode("jin", -16915));
        pinyin.add(new PinyinCode("jing", -16733));
        pinyin.add(new PinyinCode("jiong", -16708));
        pinyin.add(new PinyinCode("jiu", -16706));
        pinyin.add(new PinyinCode("ju", -16689));
        pinyin.add(new PinyinCode("juan", -16664));
        pinyin.add(new PinyinCode("jue", -16657));
        pinyin.add(new PinyinCode("jun", -16647));
        pinyin.add(new PinyinCode("ka", -16474));
        pinyin.add(new PinyinCode("kai", -16470));
        pinyin.add(new PinyinCode("kan", -16465));
        pinyin.add(new PinyinCode("kang", -16459));
        pinyin.add(new PinyinCode("kao", -16452));
        pinyin.add(new PinyinCode("ke", -16448));
        pinyin.add(new PinyinCode("ken", -16433));
        pinyin.add(new PinyinCode("keng", -16429));
        pinyin.add(new PinyinCode("kong", -16427));
        pinyin.add(new PinyinCode("kou", -16423));
        pinyin.add(new PinyinCode("ku", -16419));
        pinyin.add(new PinyinCode("kua", -16412));
        pinyin.add(new PinyinCode("kuai", -16407));
        pinyin.add(new PinyinCode("kuan", -16403));
        pinyin.add(new PinyinCode("kuang", -16401));
        pinyin.add(new PinyinCode("kui", -16393));
        pinyin.add(new PinyinCode("kun", -16220));
        pinyin.add(new PinyinCode("kuo", -16216));
        pinyin.add(new PinyinCode("la", -16212));
        pinyin.add(new PinyinCode("lai", -16205));
        pinyin.add(new PinyinCode("lan", -16202));
        pinyin.add(new PinyinCode("lang", -16187));
        pinyin.add(new PinyinCode("lao", -16180));
        pinyin.add(new PinyinCode("le", -16171));
        pinyin.add(new PinyinCode("lei", -16169));
        pinyin.add(new PinyinCode("leng", -16158));
        pinyin.add(new PinyinCode("li", -16155));
        pinyin.add(new PinyinCode("lia", -15959));
        pinyin.add(new PinyinCode("lian", -15958));
        pinyin.add(new PinyinCode("liang", -15944));
        pinyin.add(new PinyinCode("liao", -15933));
        pinyin.add(new PinyinCode("lie", -15920));
        pinyin.add(new PinyinCode("lin", -15915));
        pinyin.add(new PinyinCode("ling", -15903));
        pinyin.add(new PinyinCode("liu", -15889));
        pinyin.add(new PinyinCode("long", -15878));
        pinyin.add(new PinyinCode("lou", -15707));
        pinyin.add(new PinyinCode("lu", -15701));
        pinyin.add(new PinyinCode("lv", -15681));
        pinyin.add(new PinyinCode("luan", -15667));
        pinyin.add(new PinyinCode("lue", -15661));
        pinyin.add(new PinyinCode("lun", -15659));
        pinyin.add(new PinyinCode("luo", -15652));
        pinyin.add(new PinyinCode("ma", -15640));
        pinyin.add(new PinyinCode("mai", -15631));
        pinyin.add(new PinyinCode("man", -15625));
        pinyin.add(new PinyinCode("mang", -15454));
        pinyin.add(new PinyinCode("mao", -15448));
        pinyin.add(new PinyinCode("me", -15436));
        pinyin.add(new PinyinCode("mei", -15435));
        pinyin.add(new PinyinCode("men", -15419));
        pinyin.add(new PinyinCode("meng", -15416));
        pinyin.add(new PinyinCode("mi", -15408));
        pinyin.add(new PinyinCode("mian", -15394));
        pinyin.add(new PinyinCode("miao", -15385));
        pinyin.add(new PinyinCode("mie", -15377));
        pinyin.add(new PinyinCode("min", -15375));
        pinyin.add(new PinyinCode("ming", -15369));
        pinyin.add(new PinyinCode("miu", -15363));
        pinyin.add(new PinyinCode("mo", -15362));
        pinyin.add(new PinyinCode("mou", -15183));
        pinyin.add(new PinyinCode("mu", -15180));
        pinyin.add(new PinyinCode("na", -15165));
        pinyin.add(new PinyinCode("nai", -15158));
        pinyin.add(new PinyinCode("nan", -15153));
        pinyin.add(new PinyinCode("nang", -15150));
        pinyin.add(new PinyinCode("nao", -15149));
        pinyin.add(new PinyinCode("ne", -15144));
        pinyin.add(new PinyinCode("nei", -15143));
        pinyin.add(new PinyinCode("nen", -15141));
        pinyin.add(new PinyinCode("neng", -15140));
        pinyin.add(new PinyinCode("ni", -15139));
        pinyin.add(new PinyinCode("nian", -15128));
        pinyin.add(new PinyinCode("niang", -15121));
        pinyin.add(new PinyinCode("niao", -15119));
        pinyin.add(new PinyinCode("nie", -15117));
        pinyin.add(new PinyinCode("nin", -15110));
        pinyin.add(new PinyinCode("ning", -15109));
        pinyin.add(new PinyinCode("niu", -14941));
        pinyin.add(new PinyinCode("nong", -14937));
        pinyin.add(new PinyinCode("nu", -14933));
        pinyin.add(new PinyinCode("nv", -14930));
        pinyin.add(new PinyinCode("nuan", -14929));
        pinyin.add(new PinyinCode("nue", -14928));
        pinyin.add(new PinyinCode("nuo", -14926));
        pinyin.add(new PinyinCode("o", -14922));
        pinyin.add(new PinyinCode("ou", -14921));
        pinyin.add(new PinyinCode("pa", -14914));
        pinyin.add(new PinyinCode("pai", -14908));
        pinyin.add(new PinyinCode("pan", -14902));
        pinyin.add(new PinyinCode("pang", -14894));
        pinyin.add(new PinyinCode("pao", -14889));
        pinyin.add(new PinyinCode("pei", -14882));
        pinyin.add(new PinyinCode("pen", -14873));
        pinyin.add(new PinyinCode("peng", -14871));
        pinyin.add(new PinyinCode("pi", -14857));
        pinyin.add(new PinyinCode("pian", -14678));
        pinyin.add(new PinyinCode("piao", -14674));
        pinyin.add(new PinyinCode("pie", -14670));
        pinyin.add(new PinyinCode("pin", -14668));
        pinyin.add(new PinyinCode("ping", -14663));
        pinyin.add(new PinyinCode("po", -14654));
        pinyin.add(new PinyinCode("pu", -14645));
        pinyin.add(new PinyinCode("qi", -14630));
        pinyin.add(new PinyinCode("qia", -14594));
        pinyin.add(new PinyinCode("qian", -14429));
        pinyin.add(new PinyinCode("qiang", -14407));
        pinyin.add(new PinyinCode("qiao", -14399));
        pinyin.add(new PinyinCode("qie", -14384));
        pinyin.add(new PinyinCode("qin", -14379));
        pinyin.add(new PinyinCode("qing", -14368));
        pinyin.add(new PinyinCode("qiong", -14355));
        pinyin.add(new PinyinCode("qiu", -14353));
        pinyin.add(new PinyinCode("qu", -14345));
        pinyin.add(new PinyinCode("quan", -14170));
        pinyin.add(new PinyinCode("que", -14159));
        pinyin.add(new PinyinCode("qun", -14151));
        pinyin.add(new PinyinCode("ran", -14149));
        pinyin.add(new PinyinCode("rang", -14145));
        pinyin.add(new PinyinCode("rao", -14140));
        pinyin.add(new PinyinCode("re", -14137));
        pinyin.add(new PinyinCode("ren", -14135));
        pinyin.add(new PinyinCode("reng", -14125));
        pinyin.add(new PinyinCode("ri", -14123));
        pinyin.add(new PinyinCode("rong", -14122));
        pinyin.add(new PinyinCode("rou", -14112));
        pinyin.add(new PinyinCode("ru", -14109));
        pinyin.add(new PinyinCode("ruan", -14099));
        pinyin.add(new PinyinCode("rui", -14097));
        pinyin.add(new PinyinCode("run", -14094));
        pinyin.add(new PinyinCode("ruo", -14092));
        pinyin.add(new PinyinCode("sa", -14090));
        pinyin.add(new PinyinCode("sai", -14087));
        pinyin.add(new PinyinCode("san", -14083));
        pinyin.add(new PinyinCode("sang", -13917));
        pinyin.add(new PinyinCode("sao", -13914));
        pinyin.add(new PinyinCode("se", -13910));
        pinyin.add(new PinyinCode("sen", -13907));
        pinyin.add(new PinyinCode("seng", -13906));
        pinyin.add(new PinyinCode("sha", -13905));
        pinyin.add(new PinyinCode("shai", -13896));
        pinyin.add(new PinyinCode("shan", -13894));
        pinyin.add(new PinyinCode("shang", -13878));
        pinyin.add(new PinyinCode("shao", -13870));
        pinyin.add(new PinyinCode("she", -13859));
        pinyin.add(new PinyinCode("shen", -13847));
        pinyin.add(new PinyinCode("sheng", -13831));
        pinyin.add(new PinyinCode("shi", -13658));
        pinyin.add(new PinyinCode("shou", -13611));
        pinyin.add(new PinyinCode("shu", -13601));
        pinyin.add(new PinyinCode("shua", -13406));
        pinyin.add(new PinyinCode("shuai", -13404));
        pinyin.add(new PinyinCode("shuan", -13400));
        pinyin.add(new PinyinCode("shuang", -13398));
        pinyin.add(new PinyinCode("shui", -13395));
        pinyin.add(new PinyinCode("shun", -13391));
        pinyin.add(new PinyinCode("shuo", -13387));
        pinyin.add(new PinyinCode("si", -13383));
        pinyin.add(new PinyinCode("song", -13367));
        pinyin.add(new PinyinCode("sou", -13359));
        pinyin.add(new PinyinCode("su", -13356));
        pinyin.add(new PinyinCode("suan", -13343));
        pinyin.add(new PinyinCode("sui", -13340));
        pinyin.add(new PinyinCode("sun", -13329));
        pinyin.add(new PinyinCode("suo", -13326));
        pinyin.add(new PinyinCode("ta", -13318));
        pinyin.add(new PinyinCode("tai", -13147));
        pinyin.add(new PinyinCode("tan", -13138));
        pinyin.add(new PinyinCode("tang", -13120));
        pinyin.add(new PinyinCode("tao", -13107));
        pinyin.add(new PinyinCode("te", -13096));
        pinyin.add(new PinyinCode("teng", -13095));
        pinyin.add(new PinyinCode("ti", -13091));
        pinyin.add(new PinyinCode("tian", -13076));
        pinyin.add(new PinyinCode("tiao", -13068));
        pinyin.add(new PinyinCode("tie", -13063));
        pinyin.add(new PinyinCode("ting", -13060));
        pinyin.add(new PinyinCode("tong", -12888));
        pinyin.add(new PinyinCode("tou", -12875));
        pinyin.add(new PinyinCode("tu", -12871));
        pinyin.add(new PinyinCode("tuan", -12860));
        pinyin.add(new PinyinCode("tui", -12858));
        pinyin.add(new PinyinCode("tun", -12852));
        pinyin.add(new PinyinCode("tuo", -12849));
        pinyin.add(new PinyinCode("wa", -12838));
        pinyin.add(new PinyinCode("wai", -12831));
        pinyin.add(new PinyinCode("wan", -12829));
        pinyin.add(new PinyinCode("wang", -12812));
        pinyin.add(new PinyinCode("wei", -12802));
        pinyin.add(new PinyinCode("wen", -12607));
        pinyin.add(new PinyinCode("weng", -12597));
        pinyin.add(new PinyinCode("wo", -12594));
        pinyin.add(new PinyinCode("wu", -12585));
        pinyin.add(new PinyinCode("xi", -12556));
        pinyin.add(new PinyinCode("xia", -12359));
        pinyin.add(new PinyinCode("xian", -12346));
        pinyin.add(new PinyinCode("xiang", -12320));
        pinyin.add(new PinyinCode("xiao", -12300));
        pinyin.add(new PinyinCode("xie", -12120));
        pinyin.add(new PinyinCode("xin", -12099));
        pinyin.add(new PinyinCode("xing", -12089));
        pinyin.add(new PinyinCode("xiong", -12074));
        pinyin.add(new PinyinCode("xiu", -12067));
        pinyin.add(new PinyinCode("xu", -12058));
        pinyin.add(new PinyinCode("xuan", -12039));
        pinyin.add(new PinyinCode("xue", -11867));
        pinyin.add(new PinyinCode("xun", -11861));
        pinyin.add(new PinyinCode("ya", -11847));
        pinyin.add(new PinyinCode("yan", -11831));
        pinyin.add(new PinyinCode("yang", -11798));
        pinyin.add(new PinyinCode("yao", -11781));
        pinyin.add(new PinyinCode("ye", -11604));
        pinyin.add(new PinyinCode("yi", -11589));
        pinyin.add(new PinyinCode("yin", -11536));
        pinyin.add(new PinyinCode("ying", -11358));
        pinyin.add(new PinyinCode("yo", -11340));
        pinyin.add(new PinyinCode("yong", -11339));
        pinyin.add(new PinyinCode("you", -11324));
        pinyin.add(new PinyinCode("yu", -11303));
        pinyin.add(new PinyinCode("yuan", -11097));
        pinyin.add(new PinyinCode("yue", -11077));
        pinyin.add(new PinyinCode("yun", -11067));
        pinyin.add(new PinyinCode("za", -11055));
        pinyin.add(new PinyinCode("zai", -11052));
        pinyin.add(new PinyinCode("zan", -11045));
        pinyin.add(new PinyinCode("zang", -11041));
        pinyin.add(new PinyinCode("zao", -11038));
        pinyin.add(new PinyinCode("ze", -11024));
        pinyin.add(new PinyinCode("zei", -11020));
        pinyin.add(new PinyinCode("zen", -11019));
        pinyin.add(new PinyinCode("zeng", -11018));
        pinyin.add(new PinyinCode("zha", -11014));
        pinyin.add(new PinyinCode("zhai", -10838));
        pinyin.add(new PinyinCode("zhan", -10832));
        pinyin.add(new PinyinCode("zhang", -10815));
        pinyin.add(new PinyinCode("zhao", -10800));
        pinyin.add(new PinyinCode("zhe", -10790));
        pinyin.add(new PinyinCode("zhen", -10780));
        pinyin.add(new PinyinCode("zheng", -10764));
        pinyin.add(new PinyinCode("zhi", -10587));
        pinyin.add(new PinyinCode("zhong", -10544));
        pinyin.add(new PinyinCode("zhou", -10533));
        pinyin.add(new PinyinCode("zhu", -10519));
        pinyin.add(new PinyinCode("zhua", -10331));
        pinyin.add(new PinyinCode("zhuai", -10329));
        pinyin.add(new PinyinCode("zhuan", -10328));
        pinyin.add(new PinyinCode("zhuang", -10322));
        pinyin.add(new PinyinCode("zhui", -10315));
        pinyin.add(new PinyinCode("zhun", -10309));
        pinyin.add(new PinyinCode("zhuo", -10307));
        pinyin.add(new PinyinCode("zi", -10296));
        pinyin.add(new PinyinCode("zong", -10281));
        pinyin.add(new PinyinCode("zou", -10274));
        pinyin.add(new PinyinCode("zu", -10270));
        pinyin.add(new PinyinCode("zuan", -10262));
        pinyin.add(new PinyinCode("zui", -10260));
        pinyin.add(new PinyinCode("zun", -10256));
        pinyin.add(new PinyinCode("zuo", -10254));
    }
}

class PinyinCode{

    public String pinyin;
    public int code;

    public PinyinCode(String s,int i){
        this.pinyin = s;
        this.code = i;
    }

}