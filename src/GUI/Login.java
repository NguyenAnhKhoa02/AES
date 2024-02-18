package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login extends JFrame implements MouseListener {
    public JButton loginBtn;
    public JButton registerBtn;
    public JTextField usernameTxt;
    public JPasswordField passwordTxt;
    public Login(){
        this.setSize(500,300);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("src/Server/Images/email.png").getImage());
        this.setTitle("LOGIN");

        JLabel root = new JLabel();
        root.setSize(this.getSize());
        root.setIcon(new ImageIcon("src\\Server\\Images\\loginBk.png"));
        this.add(root);

        JLabel usernameLb = new JLabel("Username");
        usernameLb.setSize(100,30);
        usernameLb.setLocation(120,50);
        root.add(usernameLb);

        usernameTxt = new JTextField();
        usernameTxt.setSize(200,30);
        usernameTxt.setLocation(200,50);
        root.add(usernameTxt);

        JLabel passwordLb = new JLabel("Password");
        passwordLb.setSize(100,30);
        passwordLb.setLocation(120,100);
        root.add(passwordLb);

        passwordTxt = new JPasswordField();
        passwordTxt.setSize(200,30);
        passwordTxt.setLocation(200,100);
        root.add(passwordTxt);

        loginBtn = new JButton("Login");;
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        loginBtn.setFocusable(false);
        loginBtn.setSize(130,30);
        loginBtn.setLocation(120,160);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginBtn.addMouseListener(this);
        root.add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setSize(130,30);
        registerBtn.setLocation(270,160);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        registerBtn.setFocusable(false);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.addMouseListener(this);
        root.add(registerBtn);
    }

    public boolean checkFullField(){
        if(usernameTxt.getText().isEmpty()) return false;
        if(passwordTxt.getText().isEmpty()) return false;
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == loginBtn){
            loginBtn.setContentAreaFilled(true);
            loginBtn.setBackground(Color.lightGray);
            loginBtn.setForeground(Color.white);
        }
        if(e.getSource() == registerBtn){
            registerBtn.setContentAreaFilled(true);
            registerBtn.setBackground(Color.lightGray);
            registerBtn.setForeground(Color.white);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == loginBtn){
            loginBtn.setContentAreaFilled(false);
            loginBtn.setForeground(Color.BLACK);
        }
        if(e.getSource() == registerBtn){
            registerBtn.setContentAreaFilled(false);
            registerBtn.setForeground(Color.black);
        }
    }
}