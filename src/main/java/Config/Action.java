package Config;

public
class Action{

    //gif 地址
    String uri;
    int fps;
    //时长
    int time;


    public Action(String uri, int fps) {
        this.uri = uri;
        this.time = (int)((fps/24.0)*1000);

    }
    public Action(String uri, int time,int wuyong) {
        this.uri = uri;
        this.time = time;
    }


    public String getUri() {
        return uri;
    }

    public int getTime() {
        return time;
    }

}
