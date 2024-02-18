package BUS.Objects;

public class User {
    String fullName;
    String birthDay;
    String gender;
    String password;
    String username;

    public User(String fullName, String birthDay, String gender){
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public User(String fullName, String birthDay, String gender, String password, String username) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.password = password;
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
