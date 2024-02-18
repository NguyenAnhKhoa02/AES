package BUS.Objects;

import java.io.File;

public class Message {
    String from;
    String to;
    String message;
    String fileUrl;
    String date;
    public Message(){}
    public Message(String date,String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFile() {
        return fileUrl;
    }

    public void setFile(String file) {
        this.fileUrl = file;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
