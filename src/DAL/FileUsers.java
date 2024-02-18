package DAL;

import BUS.AES;
import BUS.Objects.Message;
import BUS.Objects.User;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class FileUsers {
    public File file;
    File fileUser;
    FileWriter fileWriter;
    static String path;
    public FileUsers(){
        file = new File("src/Server/Users");
    }

    public void setPath(String pathUser){
        path = pathUser;
    }

    public String getPath(){return path;}

    public boolean saveToFile(User user, String username) throws IOException {

        for (File f:
                Objects.requireNonNull(file.listFiles())) {
            if(user.getUsername().equals(f.getName())) return false;
        }
        path = file.getPath() + "\\" + username;
        fileUser = new File(path);
        fileUser.mkdir();

        fileUser = new File(path +"\\" + "password");
        fileUser.createNewFile();
        fileWriter = new FileWriter(fileUser.getPath());
        fileWriter.write(user.getPassword());
        fileWriter.close();

        fileUser = new File(path + "\\" + "profile");
        fileUser.createNewFile();
        fileWriter = new FileWriter(fileUser.getPath(),true);
        fileWriter.write(user.getFullName() + " ");
        fileWriter.write(user.getBirthDay() + " ");
        fileWriter.write(user.getGender() + " ");
        fileWriter.write(user.getUsername());

        fileWriter.close();

        fileUser = new File(path + "\\" + "received");
        fileUser.createNewFile();

        fileUser = new File(path + "\\" + "send");
        fileUser.createNewFile();

        return true;
    }

    public boolean saveMessageSend(Message message) throws IOException {

        File fileMessage = new File(path + "\\" + "send");
        fileWriter = new FileWriter(fileMessage.getPath(), true);
        String messageWrite =
                message.getDate() + " " +
                        message.getFrom() + " " +
                        message.getTo() + " " +
                message.getMessage();
        if(message.getFile() != null)
            messageWrite +=  " " + message.getFile();

        fileWriter.write(messageWrite + "\n");
        fileWriter.close();
        return true;
    }

    public boolean saveMessageReceive(Message message, File userReceive) throws IOException {
        fileWriter = new FileWriter(userReceive.getPath(), true);
        String messageWrite =
                message.getDate() + " " +
                        message.getFrom() + " " +
                        message.getTo() + " " +
                        message.getMessage();
        if(message.getFile() != null)
            messageWrite +=  " " + message.getFile();

        fileWriter.write(messageWrite + "\n");
        fileWriter.close();
        return true;
    }
}
