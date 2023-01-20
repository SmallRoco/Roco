

import Config.AllConfig;
import Utils.GifDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * 用来将 从swf导出gif剪切一下，让宠物的右下角靠墙
 */
public class CutGif {

    public static int getMaxX(BufferedImage bufferedImage){
        for(int i = bufferedImage.getWidth()-1;i>=0;i--){
            for (int j = 0; j <bufferedImage.getHeight(); j++) {
                //System.out.println(bufferedImage.getRGB(i,j));
                if(bufferedImage.getRGB(i,j)!=0){
                    return i;
                }
            }
        }
        return 0;
    }
    public static int getMaxY(BufferedImage bufferedImage){
        for(int i = bufferedImage.getHeight()-1;i>=0;i--){
            for (int j = 0; j <bufferedImage.getWidth(); j++) {
                if(bufferedImage.getRGB(j,i)!=0){
                    return i;
                }
            }
        }
        return 0;
    }

    /*public static BufferedImage subImg(BufferedImage bufferedImage,int w,int h){
        BufferedImage re = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graphics = re.getGraphics();

        for (int i = 0; i <w; i++) {
            for (int j = 0; j < h; j++) {

                if(bufferedImage.getRGB(i,j)!=0){
                    graphics.setColor(new Color(bufferedImage.getRGB(i,j)));
                }
            }
        }
    }*/



    public static void main(String[] args) {//非程序入口，测试用
        String path = "F:\\洛克王国资源\\宠物\\魅影幽兰\\";

        String[] nas = new String[]{"idle","show","att1","magic","dead"};
        for (int i = 0; i < 5; i++) {


            GifDecoder gifDecoder = new GifDecoder();

            try {
                gifDecoder.read(new FileInputStream(new File(path+nas[i]+".gif")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedImage[] pic = new BufferedImage[gifDecoder.getFrameCount()];

            int stand = 0;
            if(i==1){
                stand = 24;
            }
            BufferedImage first = gifDecoder.getFrame(stand);
            int maxX = getMaxX(first);
            int maxY = getMaxY(first);

            for (int j = 0; j < pic.length; j++) {
                System.out.println(nas[i]+":"+j);
                BufferedImage frame = gifDecoder.getFrame(j);
                pic[j] = frame.getSubimage(0, 0, maxX, maxY);
            }


            imgsToGif(pic, path+nas[i]+".gif", 42);    //执行静态函数(imgList,输出路径，时间间隔)
            System.out.println("结束！");


        }
    }




    /**
     * @param pic String[] 多个jpg文件名 包含路径
     * @param newPic String 生成的gif文件名 包含路径
     * @param playTime int 播放的延迟时间
     * @Description 把多张图片合成一张（将新图片插入到gif图末尾中）
     */
    public synchronized static void imgsToGif(BufferedImage[] pic, String newPic, int playTime) {
        try {
            AnimatedGifEncoder e = new AnimatedGifEncoder();
            e.setRepeat(0);
            e.start(new FileOutputStream(newPic));
            for (int i = 0; i < pic.length; i++) {
                e.setDelay(playTime); //设置播放的延迟时间
                //把该颜色设置透明
                e.setTransparent(new Color(0,0,0,0),true);
                e.addFrame(pic[i]);  //添加到帧中
            }
            e.finish();
        } catch (Exception e) {
            System.out.println( "jpgToGif Failed:");
            e.printStackTrace();
        }
    }


}
