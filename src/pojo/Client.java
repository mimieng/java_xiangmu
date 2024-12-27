package pojo;

public class Client {
    private int id;        // Client ID (Primary Key in database)
    private String name;   // Client's name
    private String password;  // Client's password
    private String gender;   // Client's gender

    // Constructor for creating a new client (without id)
    public Client(String name, String password, String gender) {
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    // Constructor for an existing client (with id)
    public Client(int id, String name, String password, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public Client() {

    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Client{id=" + id + ", name='" + name + "', password='" + password + "', gender='" + gender + "'}";
    }
}
