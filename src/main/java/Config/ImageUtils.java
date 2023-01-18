package Config;

import content.Pet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageUtils {

    static HashMap<String, BufferedImage> typeLarge = new HashMap<>();
    static HashMap<String, BufferedImage> typeSmall = new HashMap<>();

    static HashMap<String, BufferedImage> petIcon = new HashMap<>();
    static HashMap<String, ImageIcon> statusAnimal = new HashMap<>();
    static HashMap<String, Integer> statuspx = new HashMap<>();

    static HashMap<String, ImageIcon> currPetAnimals = new HashMap<>();

    //脱手技能
    static HashMap<String, ImageAnimal> tuoShouSkillAnimals = new HashMap<>();




    //获取属性的大图标
    public static BufferedImage getTypeLarge(String name){
        return typeLarge.get(name);
    }
    //获取属性的小图标
    public static BufferedImage getTypeSmall(String name){
        return typeSmall.get(name);
    }

    //获取宠物头像
    public static BufferedImage getPetIcon(String name){
        return petIcon.get(name);
    }
    //获取状态动画
    public static ImageIcon getStatusAnimal(String name){return statusAnimal.get(name);};
    //获取状态动画的坐标偏移
    public static int getStatusPx(String name){return statuspx.get(name);}


    //获取脱手技能动画
    public static ImageAnimal getTuoShouSkillAnimals(String s){
        return tuoShouSkillAnimals.get(s);
    }



    //获取当前战斗角色的img
    public static ImageIcon getCurrPetAnimal(String s){
        return currPetAnimals.get(s);
    }

    /**
     * 初始化参战角色动画
     */
    public static void initPetImage(Pet pet){
        currPetAnimals.put(pet.getName()+"idle",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/idle.gif")));
        currPetAnimals.put(pet.getName()+"show",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/show.gif")));
        currPetAnimals.put(pet.getName()+"att1",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/att1.gif")));
        currPetAnimals.put(pet.getName()+"magic",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/magic.gif")));
        //if(Animals.getAction(pet.getName(),"magic1")!=null)currPetAnimals.put(pet.getName()+"magic1",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/magic1.gif")));
        currPetAnimals.put(pet.getName()+"dead",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/dead.gif")));
        if(new File(AllConfig.rootPath + "宠物/" + pet.getName() + "/att2.gif").exists()){
            currPetAnimals.put(pet.getName()+"att2",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + pet.getName() + "/att2.gif")));
        }
        try {
            petIcon.put(pet.getName(), ImageIO.read(new File(AllConfig.rootPath+"宠物/"+pet.getName()+"/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化角色idle动画
     */
    public static void initPetImage(String name){
        currPetAnimals.put(name+"idle",new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "宠物/" + name + "/idle.gif")));
    }


    /**
     * 初始化参战角色动画
     */
    static {

        String[] names = new String[]{"不屈意志", "催眠粉", "冰天雪地", "冰晶结界", "大地宽恕", "种子炸弹", "岩石爆", "恶魔之眼", "暴击枪", "果冻攻击", "水之波纹", "水晶盾", "泥浆弹", "火眼金睛", "灵蔓盾击", "炸弹", "热力爆弹", "爆", "白色光束", "种子机关枪", "空气切割", "穿风刺", "花瓣群舞", "蓝色水球", "超级吸取", "车裂", "迷之微笑", "音波攻击", "风之打击", "飞叶刀", "飞沙走石", "魔焰瞬击", "龙之爪牙"
        ,"泡沫攻击","光明增益","加强防御","黑暗增益","翅膀增益"};
        int[] fpss = new int[]{56, 50, 48, 56, 48, 55, 50, 50, 34, 71, 58, 32, 55, 41, 55, 43, 42, 37, 31, 67, 35, 28, 53, 80, 51, 61, 57, 41, 53, 42, 40, 32, 63,53,36,44,30,40};
        for (int i = 0; i < names.length; i++) {
            tuoShouSkillAnimals.put(names[i],new ImageAnimal(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "脱手技能/" + names[i] + ".gif")),fpss[i]));
            tuoShouSkillAnimals.put(names[i]+"R",new ImageAnimal(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "脱手技能/" + names[i] + "R.gif")),fpss[i]));
        }

    }



    static {

        String[] types = ConfigFile.types;
        for (String type: types) {
            try {
                typeLarge.put(type, zoomByScale(ImageIO.read(new File(AllConfig.rootPath+"UI/属性/"+type+".png")),1.15));
                typeSmall.put(type, zoomByScale(ImageIO.read(new File(AllConfig.rootPath+"UI/属性/"+type+".png")),0.55));
                //typeLarge.put(type, rotateImage180(ImageIO.read(new File(AllConfig.rootPath+"/UI/属性/"+type+".png"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String[] strings = new String[]{"物攻提升","物攻下降","魔攻提升","魔攻下降","物防提升","物防下降","魔防提升","魔防下降","速度提升","速度下降","闪避提升","闪避下降","命中提升","命中下降","冰冻开始","冰冻消失","寄生开始","寄生消失","剧毒开始","剧毒消失","恐惧开始","恐惧消失","麻痹开始","麻痹消失","魅惑开始",
                "睡眠开始","睡眠消失","中毒开始","中毒消失","诅咒开始","诅咒消失","束缚开始","束缚消失","烧伤开始","烧伤消失","混乱开始","混乱消失"};
        for (String s:strings) {
            Image image = Toolkit.getDefaultToolkit().createImage(AllConfig.rootPath + "buff/" + s + ".gif");
            statusAnimal.put(s,new ImageIcon(image));
            statuspx.put(s,(305-image.getWidth(null))/2);
        }
        System.out.println();




    }

    public static BufferedImage flipHorizontally(BufferedImage image) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }


    /**
     * 缩放图片
     * @param bufferedImage
     * @param scale     比率
     * @return
     * @throws IOException
     */
    public static BufferedImage zoomByScale(BufferedImage bufferedImage,double scale) throws IOException {
        //获取缩放后的长和宽
        int _width = (int) (scale * bufferedImage.getWidth());
        int _height = (int) (scale * bufferedImage.getHeight());
        //获取缩放后的Image对象
        Image _img = bufferedImage.getScaledInstance(_width, _height, BufferedImage.TYPE_4BYTE_ABGR);
        //新建一个和Image对象相同大小的画布
        BufferedImage image = new BufferedImage(_width, _height, BufferedImage.TYPE_4BYTE_ABGR);
        //获取画笔
        Graphics2D graphics = image.createGraphics();
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(_img, 0, 0, null);
        //释放资源
        graphics.dispose();

        return image;
    }






}
