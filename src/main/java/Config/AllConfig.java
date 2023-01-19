package Config;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.util.HashMap;

public class AllConfig{

    public static String rootPath = "F:/洛克王国资源/";
    //public static String rootPath = "../../洛克王国资源/";

    static {

        String path = rootPath+"宠物/";
        String[] list = new File(path).list();

        HashMap<String, Animal> animals = Animals.animals;

        for (String s:list) {

            String string = readOrWriteStringFromFile(path + s+"/config.txt", null);
            String[] s1 = string.split(" ");

            Animal animal = new Animal();
            HashMap<String, Action> actions = animal.getActions();
            actions.put("idle",new Action("idle.gif",0));
            actions.put("show",new Action("show.gif",25));
            actions.put("att1",new Action("att1.gif",Integer.parseInt(s1[0])));
            actions.put("magic",new Action("magic.gif",Integer.parseInt(s1[1])));
            actions.put("dead",new Action("dead.gif",Integer.parseInt(s1[2])));
            animals.put(s,animal);


        }


    }

    //获取gif的长度
    public static int gifFrameCount(File file) throws IOException {
        long length = file.length();
        byte[] data = new byte[(int)length];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(data);
        fileInputStream.close();
        ImageReader reader = (ImageReader) ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream ciis = ImageIO.createImageInputStream(new ByteArrayInputStream(data));
        reader.setInput(ciis, false);
        int noi = reader.getNumImages(true);
        ciis.close();
        return noi;
    }

    //从文件中读写字符串                                     //s 为 null则为读
    public static String readOrWriteStringFromFile(String filename,String write){
        try {
            if (write==null) {//如果要写的内容为空则为读取文件

                File file = new File(filename);
                if(!file.exists()){

                    String name = file.getParent();

                    String s = gifFrameCount(new File(name + "/att1.gif"))+" "+gifFrameCount(new File(name + "/magic.gif"))+" "+gifFrameCount(new File(name + "/dead.gif"));

                    readOrWriteStringFromFile(filename,s);

                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                StringBuilder stringBuilder = new StringBuilder();
                //7的阶乘
                byte[] bytes = new byte[5040];
                int len = -1;
                while ((len=bufferedInputStream.read(bytes))!=-1){
                    stringBuilder.append(new String(bytes,0,len));
                }
                bufferedInputStream.close();
                return  stringBuilder.toString();
            }else {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
                bufferedWriter.write(write);
                bufferedWriter.close();
                return null;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }


}
