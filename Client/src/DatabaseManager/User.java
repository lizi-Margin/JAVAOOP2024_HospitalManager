package src.DatabaseManager;
public class User {
    private int id;

    public User(){
        setId(-1);
    }
    public void setId (int thisId){
        this.id = thisId;
    }
    public int getId (){
        return this.id;
    }
}
