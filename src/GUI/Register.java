package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.Pageable;

public class Register extends JFrame {
    private CustomKeyListener customKeyListener;
    public JTextField fullNameTxt;
    public JComboBox<String> genderCb;
    public JTextField dayTxt, monthTxt, yearTxt;
    public JTextField usernameTxt;
    public JPasswordField passwordPF, repeatPasswordPF;
    public JButton registerBtn;
    private JLabel root;
    public Register(){
        this.setSize(500,400);
        customKeyListener = new CustomKeyListener();

        root = new JLabel();
        root.setSize(this.getSize());
        root.setIcon(new ImageIcon("src/Server/Images/registerBk.png"));
        this.add(root);

        JLabel fullNameLb = new JLabel("Full name");
        fullNameLb.setSize(100,30);
        fullNameLb.setLocation(100,20);
        root.add(fullNameLb);

        fullNameTxt = new JTextField();
        fullNameTxt.setSize(200,30);
        fullNameTxt.setLocation(180,20);
        root.add(fullNameTxt);

        JLabel dayLb = new JLabel("Day");
        dayLb.setSize(30,30);
        dayLb.setLocation(100,70);
        root.add(dayLb);

        dayTxt = new JTextField();
        dayTxt.setSize(30,30);
        dayTxt.setLocation(130,70);
        dayTxt.addKeyListener(customKeyListener);
        root.add(dayTxt);

        JLabel monthLb = new JLabel("Month");
        monthLb.setSize(50,30);
        monthLb.setLocation(200,70);
        root.add(monthLb);

        monthTxt = new JTextField();
        monthTxt.addKeyListener(customKeyListener);
        monthTxt.setSize(30,30);
        monthTxt.setLocation(240,70);
        root.add(monthTxt);

        JLabel yearLb = new JLabel("Year");
        yearLb.setSize(50,30);
        yearLb.setLocation(300,70);
        root.add(yearLb);

        yearTxt = new JTextField();
        yearTxt.setSize(50,30);
        yearTxt.setLocation(330,70);
        yearTxt.addKeyListener(customKeyListener);
        root.add(yearTxt);

        JLabel genderLb = new JLabel("Gender");
        genderLb.setSize(50,30);
        genderLb.setLocation(100, 120);
        root.add(genderLb);

        genderCb = new JComboBox<String>();
        genderCb.addItem("Male");
        genderCb.addItem("Female");
        genderCb.addItem("Girls, guys, in-between");
        genderCb.setSize(220,30);
        genderCb.setLocation(160,120);
        root.add(genderCb);

        JLabel usernameLb = new JLabel("Username");
        usernameLb.setSize(100,30);
        usernameLb.setLocation(100,175);
        root.add(usernameLb);

        usernameTxt = new JTextField();
        usernameTxt.setSize(210,30);
        usernameTxt.setLocation(170,175);
        root.add(usernameTxt);

        JLabel passwordLB = new JLabel("Password");
        passwordLB.setSize(100,30);
        passwordLB.setLocation(100,230);
        root.add(passwordLB);

        passwordPF = new JPasswordField();
        passwordPF.setSize(210,30);
        passwordPF.setLocation(170,230);
        root.add(passwordPF);

        JLabel repeatPasswordJL = new JLabel("Repeat");
        repeatPasswordJL.setSize(100,30);
        repeatPasswordJL.setLocation(100,280);
        root.add(repeatPasswordJL);

        repeatPasswordPF = new JPasswordField();
        repeatPasswordPF.setSize(230, 30);
        repeatPasswordPF.setLocation(150,280);
        root.add(repeatPasswordPF);

        registerBtn = new JButton("Register");
        registerBtn.setSize(280,30);
        registerBtn.setLocation(100,330);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.setContentAreaFilled(false);
        registerBtn.setFocusable(false);
        registerBtn.setForeground(Color.white);
        root.add(registerBtn);

        this.setResizable(false);
    }

    public boolean checkIsFullField(){
        if(fullNameTxt.getText().isEmpty()) return false;
        if(dayTxt.getText().isEmpty()) return false;
        if(monthTxt.getText().isEmpty()) return false;
        if(yearTxt.getText().isEmpty()) return false;
        if(usernameTxt.getText().isEmpty()) return false;
        if(passwordPF.getText().isEmpty()) return false;
        if(repeatPasswordPF.getText().isEmpty()) return false;

        return true;
    }

    public boolean checkPassAndRepeat(){
        if(!passwordPF.getText().equals(repeatPasswordPF.getText())) return false;
        return true;
    }

    public boolean checkYear(){
        if(yearTxt.getText().length() != 4) return false;
        return true;
    }

    public void setDefault(){
        fullNameTxt.setText("");
        dayTxt.setText("");
        monthTxt.setText("");
        yearTxt.setText("");
        genderCb.setSelectedIndex(0);
        passwordPF.setText("");
        repeatPasswordPF.setText("");
    }

    class CustomKeyListener implements KeyListener{
        private String day = "";
        private String month = "";
        private String year = "";
        private String temp;
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {


        }

        @Override
        public void keyReleased(KeyEvent e) {
            /*day*/
            if(e.getSource() == dayTxt){

                if(e.getKeyCode() >= 48 && e.getKeyCode() <= 57)
                    day += e.getKeyChar();

                if(e.getKeyCode() == 8 && !day.isEmpty()){
                    day = day.substring(0,day.length()-1);
                }

                if(day.isEmpty()) return;

                if(Integer.parseInt(day) > 31) {
                    JOptionPane.showMessageDialog(root,
                            "Maximum of day in month is 31",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    day = day.substring(0,day.length()-1);
                }
                dayTxt.setText("");
                dayTxt.setText(day);
            }

            /*month*/
            if(e.getSource() == monthTxt){
                if(e.getKeyCode() >= 48 && e.getKeyCode() <= 57)
                    month += e.getKeyChar();

                if(e.getKeyCode() == 8 && !month.isEmpty()){
                    month = month.substring(0,month.length()-1);
                }

                if(month.isEmpty()) return;

                if(Integer.parseInt(month) > 12) {
                    JOptionPane.showMessageDialog(root,
                            "A year only have 12 months",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    month = month.substring(0,month.length()-1);
                }
                monthTxt.setText("");
                monthTxt.setText(month);
            }

            /*year*/
            if(e.getSource() == yearTxt){
                if(e.getKeyCode() >= 48 && e.getKeyCode() <= 57)
                    year += e.getKeyChar();

                if(e.getKeyCode() == 8 && !year.isEmpty()){
                    year = year.substring(0,year.length()-1);
                }

                if(year.isEmpty()) return;

                if(year.length() > 4) {
                    JOptionPane.showMessageDialog(root,
                            "A year only have 4 digits",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    year = year.substring(0,year.length()-1);
                }
                yearTxt.setText("");
                yearTxt.setText(year);
            }
        }
    }
}
