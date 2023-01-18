

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    public static HashMap<Integer,User> users = new HashMap<>();
    public static HashMap<String,Battle> battles = new HashMap<>();

    private static volatile String battleName = null;
    public static void setBattleName(String name){
        while (battleName!=null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        battleName = name;
    }


    public static void main(String[] args) {


        ExecutorService ex =  Executors.newFixedThreadPool(3);

        try {
            ServerSocket serverSocket = new ServerSocket(8082);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (battleName != null) {
                            ex.execute(battles.get(battleName));
                            battleName = null;
                        }else {
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();

            int count = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                //socket.setSoTimeout(15000);
                User user = new User(socket, count++);
                users.put(count,user);
                System.out.println(socket.getInetAddress().getHostAddress());
                ex.execute(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class User implements Runnable{

    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    int id;

    public User(Socket socket,int id) {
        this.id = id;
        this.socket = socket;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void clear(){
        try {
            if(socket!=null) {
                socket.close();
                socket = null;
            }if(objectInputStream!=null) {
                objectInputStream.close();
                objectInputStream = null;
            }if(objectOutputStream!=null) {
                objectOutputStream.close();
                objectOutputStream = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    public int getId() {
        return id;
    }

    public boolean isClosed(){
        return socket.isClosed();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void write(String s){
        try {
            objectOutputStream.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
            clear();
        }
    }
    public String read(){
        try {
            String s = (String) objectInputStream.readObject();
            return s;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            clear();

        }
        return null;
    }
    public String readEx() throws IOException, ClassNotFoundException {

        String s = (String) objectInputStream.readObject();
        return s;
    }

    @Override
    public void run() {


        String s = (String) read();

        if(s.contains("enterBattle")){
            String s1 = s.split(":")[1];
            Battle battle = GameServer.battles.get(s1);
            if(battle!=null){

                if(battle.getUser2()!=null){
                    write("fill");
                    clear();
                    return;
                }

                try {
                    String s2 = battle.getUser1().readEx();
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("前一个人已经退出");
                    battle.setUser1(this);
                    return;
                }

                        //战斗开始
                System.out.println("战斗开始");
                battle.addUser(this);
                battle.getUser2().read();
                GameServer.setBattleName(s1);


                return;
            }else {
                GameServer.battles.put(s1,new Battle(this,s1));
            }
        }


    }
}

class Battle implements Runnable{

    User user1;
    User user2;

    String id;
    public Battle(User user1,String id) {
        this.user1 = user1;
        this.id = id;
    }

    public void addUser(User user2){
        this.user2 = user2;
    }


    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    @Override
    public void run() {


        user1.write("battleStart");
        user2.write("battleStart");

        String user1Attribute = user1.read();
        String user2Attribute = user2.read();

        user1.write(user2Attribute);
        user2.write(user1Attribute);

        user1.write("0");
        user2.write("1");


        byte[] bytes = new byte[1024];
        for (int i = 0; i <1024; i++) {
            bytes[i] = (byte) (Math.random()*100);
        }
        String s = new String(bytes, StandardCharsets.UTF_8);

        user1.write(s);
        user2.write(s);

        while (true){
            String read1 = null;
            try {
                read1 = user1.readEx();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                user2.write("run");
                clear();
                return;
            }
            System.out.println("read1:"+read1);
            String read2 = null;
            try {
                read2 = user2.readEx();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                user1.write("run");
                clear();
                return;
            }
            System.out.println("read2:"+read2);
            if("gameOver".equals(read1)||"gameOver".equals(read2)){
                if("gameOver".equals(read1)&&"gameOver".equals(read2)){
                    clear();
                    return;
                }else {
                    user1.write("数据异常");
                    user2.write("数据异常");
                    clear();
                    return;
                }
            }
            user1.write(read2);
            user2.write(read1);
        }


    }

    public void clear(){
        if(user2!=null)user2.clear();
        if(user1!=null)user1.clear();
        GameServer.battles.remove(id);
    }
}
