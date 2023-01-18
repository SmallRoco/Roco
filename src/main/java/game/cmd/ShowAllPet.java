package game.cmd;

public class ShowAllPet implements CmdInterface{

    @Override
    public String getName() {
        return "查看所有宠物";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void fun(String s) {

    }
}
