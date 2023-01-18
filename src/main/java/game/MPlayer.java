package game;

import Config.AllConfig;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MPlayer {
    static volatile Player player;
    public MPlayer() {
    }
    public static void close(){
        player.close();
        player = null;
        Runtime.getRuntime().gc();
    }


    public static void playCombat() {
        try {
            if(player!=null)player.close();
            player = new Player(new FileInputStream(AllConfig.rootPath+"music/music_combat.mp3"));
            player.play();

        } catch (JavaLayerException | FileNotFoundException var1) {
            var1.printStackTrace();
        }

    }
}
