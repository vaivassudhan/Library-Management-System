package Fine;

public class Fine {
    private int Group_Id;
    private float Fine_Per_Day;
    private float Damage_Fine;

    public float getDamage_Fine() {
        return Damage_Fine;
    }

    public void setDamage_Fine(float damage_Fine) {
        Damage_Fine = damage_Fine;
    }



    public int getGroup_Id() {
        return Group_Id;
    }

    public void setGroup_Id(int group_Id) {
        Group_Id = group_Id;
    }

    public float getFine_Per_Day() {
        return Fine_Per_Day;
    }

    public void setFine_Per_Day(float fine_Per_Day) {
        Fine_Per_Day = fine_Per_Day;
    }
}
