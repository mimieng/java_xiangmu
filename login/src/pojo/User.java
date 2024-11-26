package pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String psw;
    private String count;
    private int id;
    public  User(){

    }
    public User(String name, String psw, String count, int id) {
        this.name = name;
        this.psw = psw;
        this.count = count;
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
