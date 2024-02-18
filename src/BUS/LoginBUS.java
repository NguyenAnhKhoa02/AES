package BUS;

import BUS.Objects.User;
import DAL.FileUsers;

import java.io.*;
import java.util.Objects;

public class LoginBUS {
    public FileUsers fileUsers;
    AES aes;
    public LoginBUS(){
        fileUsers = new FileUsers();
        aes = new AES();
    }

    public boolean checkLogin(
            String username,
            String password
    ) {
        BufferedReader bufferedReader;
        String pass;
        try {
            for (File f :
                    Objects.requireNonNull(fileUsers.file.listFiles())) {
                if(username.equals(f.getName())){
                    bufferedReader = new BufferedReader(new FileReader(fileUsers.file.getPath() + "\\" + username + "\\" + "password"));
                    pass = bufferedReader.readLine();
                    bufferedReader.close();

                    if(aes.textAES.decrypt(pass).equals(password)){
                        fileUsers.setPath(fileUsers.file.getPath() + "\\" + username);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

}
