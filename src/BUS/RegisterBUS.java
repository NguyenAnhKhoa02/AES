package BUS;


import BUS.Objects.User;
import DAL.FileUsers;

import java.io.IOException;

public class RegisterBUS {
    FileUsers fileUsers;
    AES aes;

    public RegisterBUS(){
        fileUsers = new FileUsers();
        aes = new AES();
    }

    public boolean registUser(User user){
        String username = user.getUsername();
        try {
            user.setPassword(aes.textAES.encrypt(user.getPassword()));
            user.setFullName(aes.textAES.encrypt(user.getFullName()));
            user.setBirthDay(aes.textAES.encrypt(user.getBirthDay()));
            user.setGender(aes.textAES.encrypt(user.getGender()));
            user.setUsername(aes.textAES.encrypt(user.getUsername()));
            return fileUsers.saveToFile(user, username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
