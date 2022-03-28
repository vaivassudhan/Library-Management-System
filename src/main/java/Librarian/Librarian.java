package Librarian;

public class Librarian {
    private String Librarian_Id;
    private String Password;
    private String Name;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    private String Gender;
    private int role;

    public String getLibrarian_Id() {
        return Librarian_Id;
    }

    public void setLibrarian_Id(String librarian_Id) {
        Librarian_Id = librarian_Id;
    }
//
//    public String getPassword() {
//        return Password;
//    }
//
//    public void setPassword(String password) {
//        Password = password;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
