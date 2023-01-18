package Utils;

import Config.AllConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        String[] list = new File(AllConfig.rootPath + "脱手技能").list();
        ArrayList<String> arrayList = new ArrayList<>();
        int[] ints = new int[list.length];

        for (int i=0;i<list.length;i++) {
            String name = list[i];
            if(name.contains("R")){continue;}


            arrayList.add(name);
            GifDecoder gifDecoder = new GifDecoder();

            try {
                gifDecoder.read(new FileInputStream(new File(AllConfig.rootPath+"脱手技能/"+name+".gif")));
                ints[i] = gifDecoder.getFrameCount();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println(arrayList);
        System.out.println(Arrays.toString(ints));
    }
}
