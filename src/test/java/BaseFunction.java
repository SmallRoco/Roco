import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class BaseFunction {



    public static void main(String[] args) {
        BaseFunction baseFunction = new BaseFunction();


        for (int i = 100; i < 3097; i++) {
            System.out.println(i);
            baseFunction.downLoadFileFromUrl("https://res.17roco.qq.com/res/combat/icons/"+i+"-.png","F:\\洛克王国资源\\宠物\\图标\\"+i+".png");
        }
    }

    //线程休眠
    public void sleep(long l){
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //测试执行时间
    public long startTime = 0;
    public void showTime(){
        long l = System.currentTimeMillis();
        if(startTime==0){
            startTime = l;
        }
        System.out.println("----------------------------------");
        System.out.println(l);
        System.out.println(l-startTime);
        System.out.println("----------------------------------");

    }

    //从url上下载文件
    public void downLoadFileFromUrl(String url,String filepathname){

        URLConnection connection = null;
        try {
            connection = new URL(url).openConnection();
            InputStream inputStream = connection.getInputStream();

            BufferedInputStream bin = new BufferedInputStream(inputStream);
            BufferedOutputStream bot = new BufferedOutputStream(new FileOutputStream(filepathname));

            byte[] bytes = new byte[10240];
            int len = -1;
            while ((len=bin.read(bytes))!=-1){
                bot.write(bytes,0,len);
            }
            bin.close();
            bot.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    //从文件中读写字符串                                     //s 为 null则为读
    public String readOrWriteStringFromFile(String filename,String write){
        try {
            if (write==null) {//如果要写的内容为空则为读取文件
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
                StringBuilder stringBuilder = new StringBuilder();
                                    //7的阶乘
                byte[] bytes = new byte[5040];
                int len = -1;
                while ((len=bufferedInputStream.read(bytes))!=-1){
                    stringBuilder.append(new String(bytes));
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

    public byte[] readOrWriteBytesFromFile(String filename,byte[] bytes){
        try {
            if (bytes==null) {//如果要写的内容为空则为读取文件
                File file = new File(filename);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                //7的阶乘
                bytes = new byte[(int)file.length()];

                bufferedInputStream.read(bytes);
                bufferedInputStream.close();

                return  bytes;
            }else {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
                return null;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
    /**
     *1. 从剪切板获得文字。
     */
    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        Transferable clipTf = sysClip.getContents(null);

        if (clipTf != null) {
            // 检查内容是否是文本类型
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    ret = (String) clipTf
                            .getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }

    /**
     * 2.将字符串复制到剪切板。
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }

}
