package Config;

public class AttributeAdd {

    private String[] skillName;
    private int[] data = new int[8];

    public AttributeAdd(int[] data,String[] skillName) {
        this.data = data;
        this.skillName = skillName;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String[] getSkillName() {
        return skillName;
    }

    public void setSkillName(String[] skillName) {
        this.skillName = skillName;
    }
}
