package game;


import UI.MainFrame;
import UI.PetItemLabel;
import content.Pet;

public class FightStart {

    private static Pet[] pets1 = new Pet[6];
    private static Pet[] pets2 = new Pet[6];

    //这两个方法别用 别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用
    public static Pet[] getPets1() {
        return pets1;
    }
    //这两个方法别用 别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用别用
    public static Pet[] getPets2() {
        return pets2;
    }

    public volatile static boolean musicClose = false;

    public static boolean start(Pet[] pets1,Pet[] pets2,boolean connect) {

        FightStart.pets1 = pets1;
        FightStart.pets2 = pets2;




        //pets1[0].setNoexception(1000);
        //pets2[0].addMark("仇恨",2);

        //pets1[1] = new Pet("辉月土王","土",900,185,750,345,290,400,356,324,new String[]{"黑洞","圣辉地焰","耀阳颂","三清化土"},MainFrame.pawn1,MainFrame.bloodLine1);


        MainFrame.initUi();

        for (int i = 0; i <pets1.length; i++) {
            if(pets1[i]==null)break;
            pets1[i].setBloodLine(MainFrame.bloodLine1);
            pets1[i].setPawn(MainFrame.pawn1);
        }
        for (int i = 0; i <pets2.length; i++) {
            if(pets2[i]==null)break;
            pets2[i].setBloodLine(MainFrame.bloodLine2);
            pets2[i].setPawn(MainFrame.pawn2);
        }

        initPetLabels();

        musicClose = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!FightStart.musicClose) {
                    MPlayer.playCombat();
                }
            }
        }).start();


        if(connect){
            boolean b = MainFrame.connectStart(pets1[0], pets2[0]);
            musicClose=true;
            MPlayer.close();

            MainFrame.close();
            return b;
        }else {
            boolean start = MainFrame.start(pets1[0], pets2[0]);
            musicClose=true;
            MPlayer.close();
            MainFrame.close();
            return start;

        }


    }

    public static void initPetLabels(){
        for (int i = 0; i <6; i++) {
            MainFrame.petItemLabels[i] = new PetItemLabel(i);
            MainFrame.addCompant(MainFrame.petItemLabels[i],241+88*i,435,68,88);
        }
    }

    public static int getIndexPet(Pet[] pets,Pet pet){

        for (int i = 0; i <pets.length; i++) {
            if(pets[i]!=null&&pets[i]==pet){
                return i;
            }
        }
        return -1;
    }


}
