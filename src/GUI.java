import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

import BUS.LoginBUS;
import BUS.Objects.User;
import BUS.RegisterBUS;
import DAL.FileUsers;
import GUI.*;

import javax.swing.*;

public class GUI{
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private Login login;
    private Register register;
    private MainDisplay mainDisplay;
    private CustomerListener customerListener;
    private CustomerWindowsListener customerWindowsListener;

    public GUI() {
        customerListener = new CustomerListener();
        customerWindowsListener = new CustomerWindowsListener();

        /*login*/
        login = new Login();
        login.setLocation((int)dimension.getWidth() / 2 - login.getWidth() / 2,
                         (int)dimension.getHeight() / 2 - login.getHeight() / 2);
        login.registerBtn.addActionListener(customerListener);
        login.loginBtn.addActionListener(customerListener);
        login.addWindowListener(customerWindowsListener);
        login.setVisible(true);
        login.setVisible(true);

        /*register*/
        register = new Register();

        register.registerBtn.addActionListener(customerListener);
        register.addWindowListener(customerWindowsListener);

        register.setLocation((int)dimension.getWidth()/2 - register.getWidth()/2,
                             (int)dimension.getHeight()/2 - register.getHeight()/2);
        register.setVisible(false);

    }

    class CustomerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            /*login*/
            /*
            register button
             */
            if(e.getSource() == login.registerBtn) {
                login.setVisible(false);
                register.setVisible(true);
            }

            /*
            login button
             */
            if(e.getSource() == login.loginBtn){
                if(!login.checkFullField()){
                    JOptionPane.showMessageDialog(
                            login,
                            "Username or password is not empty",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LoginBUS loginBUS = new LoginBUS();
                boolean checkUser = loginBUS.checkLogin(
                        login.usernameTxt.getText(),
                        login.passwordTxt.getText()
                );

                if(checkUser){
                    JOptionPane.showMessageDialog(
                            login,
                            "Login successed!",
                            "Success",
                            JOptionPane.ERROR_MESSAGE,
                            new ImageIcon("src\\Server\\Images\\success.png"));
                    mainDisplay = new MainDisplay();

                    mainDisplay.addWindowListener(customerWindowsListener);

                    mainDisplay.setLocation((int)dimension.getWidth() / 2 - mainDisplay.getWidth()/2,
                            (int)dimension.getHeight()/2 - mainDisplay.getHeight()/2);
                    mainDisplay.setInfo();
                    login.setVisible(false);
                    mainDisplay.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(
                            login,
                            "Username or password is incorrect!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            /*register*/
            /*
            register button
             */
            if(e.getSource() == register.registerBtn){
                if(!register.checkIsFullField()){
                    JOptionPane.showMessageDialog(
                            register,
                            "Must full field all data",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                if(!register.checkPassAndRepeat()){
                    JOptionPane.showMessageDialog(
                            register,
                            "Password and repeat-password is incorrect",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                if(!register.checkYear()){
                    JOptionPane.showMessageDialog(
                            register,
                            "Year must have 4 digits",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                RegisterBUS registerBUS = new RegisterBUS();
                boolean regisgerBool = registerBUS.registUser(new User(
                        register.fullNameTxt.getText(),
                        register.dayTxt.getText()+"/"+register.monthTxt.getText()+"/"+register.yearTxt.getText(),
                        Objects.requireNonNull(register.genderCb.getSelectedItem()).toString(),
                        register.passwordPF.getText(),
                        register.usernameTxt.getText()));

                if(!regisgerBool){
                    JOptionPane.showMessageDialog(
                            register,
                            "User has existed",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                else {
                    JOptionPane.showMessageDialog(
                            register,
                            "Regist succesed",
                            "Error",
                            JOptionPane.ERROR_MESSAGE,
                            new ImageIcon("src\\Server\\Images\\success.png")
                    );
                    return;
                }
            }
        }
    }

    class CustomerWindowsListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            /*login*/
            if(e.getSource() == login) System.exit(0);

            /*register*/
            if(e.getSource() == register){
                register.setVisible(true);
                login.setVisible(true);
            }

            /*main display*/
            if(e.getSource() == mainDisplay){
                mainDisplay.setVisible(true);
                login.setVisible(true);
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}