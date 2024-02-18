package BUS;

import BUS.Objects.Message;
import DAL.FileUsers;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageBUS {
    FileUsers fileUsers;
    AES aes;
    public MessageBUS(){
        fileUsers = new FileUsers();
        aes = new AES();
    }

    public boolean Send(
            String from,
            String to,
            String message,
            File file
    ){
        boolean checkUser = false;
        if(from.isEmpty() && message.isEmpty()) return false;
        String pathReceive;
        for (File user:
                Objects.requireNonNull(fileUsers.file.listFiles())) {
            if(user.getName().equals(to)){
                checkUser = true;
                pathReceive = user.getPath();
                break;
            }
        }
        if (!checkUser) return false;

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDate = localDateTime.format(dateTimeFormatter);

        Message messageObj = new Message();
        messageObj.setDate(aes.textAES.encrypt(formatDate));
        messageObj.setFrom(aes.textAES.encrypt(from));
        messageObj.setTo(aes.textAES.encrypt(to));
        messageObj.setMessage(aes.textAES.encrypt(message));

        File fileReceive = new File(fileUsers.file.getPath() + "\\" + to + "\\" + "received");

        try {
            if(file != null){
                File fileServer = new File("src/Server/Files" + "/" + file.getName());
                fileServer.createNewFile();
                aes.fileAES.encrypt(file,fileServer);
                messageObj.setFile(aes.textAES.encrypt(fileServer.getPath()));
            }

            this.fileUsers.saveMessageSend(messageObj);
            this.fileUsers.saveMessageReceive(messageObj,fileReceive);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public List<Message> getAllMessageSend(){
        List<Message> messageList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUsers.getPath() + "\\" + "send"));
            String lineMessage = bufferedReader.readLine();
            String[] details;
            while (lineMessage != null){
                details = lineMessage.split(" ");
                Message message = new Message(
                        aes.textAES.decrypt(details[0]),
                        aes.textAES.decrypt(details[1]),
                        aes.textAES.decrypt(details[2]),
                        aes.textAES.decrypt(details[3])
                );

                if(details.length == 5) message.setFile(aes.textAES.decrypt(details[4]));
                messageList.add(message);
                lineMessage = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return messageList;
    }

    public List<Message> getAllMessageReceived(){
        List<Message> messageList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUsers.getPath() + "\\" + "received"));
            String lineMessage = bufferedReader.readLine();
            String[] details;
            while (lineMessage != null){
                details = lineMessage.split(" ");
                Message message = new Message(
                        aes.textAES.decrypt(details[0]),
                        aes.textAES.decrypt(details[1]),
                        aes.textAES.decrypt(details[2]),
                        aes.textAES.decrypt(details[3])
                );

                if(details.length == 5) message.setFile(aes.textAES.decrypt(details[4]));
                messageList.add(message);
                lineMessage = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return messageList;
    }

    public void saveFile(File file, File directory){
        try {
            File fileSave = new File(directory + "\\" + file.getName());
            file.createNewFile();

            aes.fileAES.decrypt(file,fileSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
