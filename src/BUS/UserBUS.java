package BUS;

import BUS.AES;
import BUS.Objects.User;
import DAL.FileUsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserBUS {
    FileUsers fileUsers;
    AES aes;
    public UserBUS(){
        fileUsers = new FileUsers();
        aes = new AES();
    }

    public User getUser(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUsers.getPath() + "\\" + "profile"));
            String[] infos = bufferedReader.readLine().split(" ");
            return new User(
                    aes.textAES.decrypt(infos[0]),
                    aes.textAES.decrypt(infos[1]),
                    aes.textAES.decrypt(infos[2]),
                    null,
                    aes.textAES.decrypt(infos[3])
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
