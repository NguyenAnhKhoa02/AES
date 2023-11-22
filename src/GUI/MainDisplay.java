package GUI;

import BUS.MessageBUS;
import BUS.Objects.Message;
import BUS.Objects.User;
import BUS.RegisterBUS;
import BUS.UserBUS;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MainDisplay extends JFrame {

    private RightMenu rightMenu;

    private Content content;

    private JPanel currentPanel;

    private CustomActionListener customActionListener;

    private MessageBUS messageBUS;
    public MainDisplay(){
        this.setSize(1000,600);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("src/Server/Images/email.png").getImage());
        this.setTitle("Main");

        customActionListener = new CustomActionListener();
        messageBUS = new MessageBUS();

        rightMenu = new RightMenu();
        rightMenu.profileBtn.addActionListener(customActionListener);
        rightMenu.newBtn.addActionListener(customActionListener);
        rightMenu.recieveBtn.addActionListener(customActionListener);
        rightMenu.sendBtn.addActionListener(customActionListener);
        this.add(rightMenu);

        content =  new Content();

        currentPanel = content.profilePanel;
        this.add(content);

        this.setLayout(null);
    }

    public void setInfo(){
        rightMenu.nameUserLb.setText("WELCOME: " + new UserBUS().getUser().getUsername().toUpperCase());
        content.createProfilePanel();
        currentPanel = content.profilePanel;
    }

    class CustomActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            currentPanel.setVisible(false);
            if(e.getSource() == rightMenu.profileBtn) currentPanel = content.profilePanel;
            else if (e.getSource() == rightMenu.newBtn) currentPanel =  content.newPane;
            else if (e.getSource() == rightMenu.recieveBtn) currentPanel = content.receivePane;
            else if (e.getSource() == rightMenu.sendBtn) currentPanel = content.sendPane;
            currentPanel.setVisible(true);


        }
    }

}

class RightMenu extends JPanel{
    private JLabel root;
    public JLabel nameUserLb;
    public JButton profileBtn;
    public JButton newBtn;
    public JButton recieveBtn;
    public JButton sendBtn;

    public RightMenu(){
        this.setSize(300,600);

        root = new JLabel();
        root.setSize(this.getSize());
        root.setIcon(new ImageIcon("src/Server/Images/rightBk.png"));
        root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
        this.add(root);

        Dimension maxDimension = new Dimension(root.getWidth(),40);

        nameUserLb = new JLabel();
        nameUserLb.setFont(new Font("Arial",Font.BOLD,20));
        nameUserLb.setText("Welcome: ");
        nameUserLb.setMaximumSize(new Dimension(root.getWidth(),50));
        root.add(nameUserLb);

        profileBtn = new JButton("Profile");
        profileBtn.setMaximumSize(maxDimension);
        profileBtn.setContentAreaFilled(false);
        profileBtn.setFocusable(false);
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        root.add(profileBtn);

        newBtn = new JButton("New");
        newBtn.setMaximumSize(maxDimension);
        newBtn.setContentAreaFilled(false);
        newBtn.setFocusable(false);
        newBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        root.add(newBtn);

        recieveBtn = new JButton("Received");
        recieveBtn.setMaximumSize(maxDimension);
        recieveBtn.setContentAreaFilled(false);
        recieveBtn.setFocusable(false);
        recieveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        root.add(recieveBtn);

        sendBtn = new JButton("Sent");
        sendBtn.setMaximumSize(maxDimension);
        sendBtn.setFocusable(false);
        sendBtn.setContentAreaFilled(false);
        sendBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        root.add(sendBtn);

        this.setLayout(null);
    }
}

class Content extends JPanel{

    public ProfilePanel profilePanel;
    public NewPane newPane;
    public ReceivePane receivePane;
    public SendPane sendPane;
    private final UserBUS userBUS;
    private MessageBUS messageBUS;
    public Content(){
        this.setSize(700,600);
        this.setLocation(300,0);
        this.setLayout(null);
        userBUS = new UserBUS();
        messageBUS = new MessageBUS();

        newPane = new NewPane();
        newPane.setVisible(false);
        this.add(newPane);

//        receivePane = new ReceivePane();
//        receivePane.setVisible(false);
//        this.add(receivePane);
//
    }

    public void createProfilePanel(){
        profilePanel = new ProfilePanel();
        this.add(profilePanel);

        sendPane = new SendPane();
        this.add(sendPane);

        receivePane = new ReceivePane();
        this.add(receivePane);
    }

    public class Message extends JPanel implements ActionListener{
        public JTextField fromTxt;
        public JTextField toTxt;
        public JTextArea messageArea;
        public JLabel fileSendLb;
        public File fileSend;
        public JButton sendBtn;
        public JButton insertBtn;
        private JButton deleteButton;
        public JButton backButton;
        public Message(){
            this.setSize(700,600);
            this.setLayout(null);
            Font font = new Font("Arial",Font.PLAIN,15);

            insertBtn = new JButton(new ImageIcon("src/Server/Images/attachment.png"));
            insertBtn.addActionListener(this);
            insertBtn.setSize(30,30);
            insertBtn.setLocation(20,0);
            insertBtn.setContentAreaFilled(false);
            insertBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            insertBtn.setBorderPainted(false);
            insertBtn.setToolTipText("Attachment");
            this.add(insertBtn);

            sendBtn = new JButton(new ImageIcon("src/Server/Images/send.png"));
            sendBtn.setSize(30,30);
            sendBtn.setLocation(this.getWidth() - sendBtn.getWidth() - 30 ,0);
            sendBtn.setContentAreaFilled(false);
            sendBtn.setBorderPainted(false);
            sendBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sendBtn.setToolTipText("Send");
            this.add(sendBtn);

            JLabel fromLb = new JLabel("From");
            fromLb.setFont(font);
            fromLb.setLocation(20,40);
            fromLb.setSize(50,20);
            this.add(fromLb);

            fromTxt = new JTextField();
            fromTxt.setSize(600,20);
            fromTxt.setLocation(70,40);
            this.add(fromTxt);

            JLabel toLb = new JLabel("To");
            toLb.setFont(font);
            toLb.setSize(50,20);
            toLb.setLocation(20,70);
            this.add(toLb);

            toTxt = new JTextField();
            toTxt.setSize(600,20);
            toTxt.setLocation(70,70);
            this.add(toTxt);

            JLabel messageLb = new JLabel("Message");
            messageLb.setFont(font);
            messageLb.setSize(100,20);
            messageLb.setLocation(20,100);
            this.add(messageLb);

            messageArea = new JTextArea();
            messageArea.setSize(650,350);
            messageArea.setLocation(20,120);
            this.add(messageArea);

            fileSendLb = new JLabel();
            fileSendLb.setSize(200,30);
            fileSendLb.setLocation(20,500);
            fileSendLb.setFont(font);
            this.add(fileSendLb);

            deleteButton = new JButton(new ImageIcon("src/Server/Images/close.png"));
            deleteButton.setSize(30,30);
            deleteButton.setLocation(250,500);
            deleteButton.setBorderPainted(false);
            deleteButton.setContentAreaFilled(false);
            deleteButton.setVisible(false);
            deleteButton.addActionListener(this);
            deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            this.add(deleteButton);

            backButton = new JButton(new ImageIcon("src/Server/Images/back.png"));
            backButton.setSize(35,35);
            backButton.setContentAreaFilled(false);
            backButton.setBorderPainted(false);
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            backButton.setLocation(20,0);
            backButton.setVisible(false);
            backButton.setToolTipText("Back");
            this.add(backButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == insertBtn){
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JFileChooser jFileChooser = new JFileChooser();
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

                    jFileChooser.setMultiSelectionEnabled(true);
                    jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    jFileChooser.setFileHidingEnabled(false);

                    if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        fileSend = jFileChooser.getSelectedFile();
                        fileSendLb.setText(fileSend.getName());
                        deleteButton.setVisible(true);
                    }
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                         InstantiationException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(e.getSource() == deleteButton){
                fileSendLb.setText("");
                fileSend = null;
                deleteButton.setVisible(false);
            }
        }

        public void setViewMode(BUS.Objects.Message message){
            sendBtn.setVisible(false);
            insertBtn.setVisible(false);
            backButton.setVisible(true);

            fromTxt.setText(message.getFrom());
            toTxt.setText(message.getTo());
            messageArea.setText(message.getMessage());
            if(message.getFile() != null){
                File file = new File(message.getFile());
                fileSendLb.setText(file.getName());
                fileSendLb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                fileSendLb.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        try{
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            JFileChooser jFileChooser = new JFileChooser();
                            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

                            jFileChooser.showSaveDialog(null);

                            if(jFileChooser.getSelectedFile() == null) return;

                            messageBUS.saveFile(file, jFileChooser.getSelectedFile());

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Save succed!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE,
                                    new ImageIcon("src/Server/Images/success.png"));
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                desktop.open(jFileChooser.getSelectedFile());

                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                                 IllegalAccessException error) {
                            throw new RuntimeException(error);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }

            messageArea.setEditable(false);
            fromTxt.setEditable(false);
            toTxt.setEditable(false);
        }

        public void setFromName(String fromName){
            fromTxt.setText(fromName);
            fromTxt.setEditable(false);
        }
    }

    class ProfilePanel extends JPanel{
        public JLabel fullNameLb;
        public JLabel birthdayLb;
        public JLabel genderLb;
        public ProfilePanel(){
            this.setSize(700,600);
            User user = userBUS.getUser();

            GroupLayout groupLayout = new GroupLayout(this);
            this.setLayout(groupLayout);

            Font font = new Font("Arial",Font.PLAIN,20);

            JLabel fullNameLbTitle = new JLabel("FullName: ");
            fullNameLbTitle.setFont(font);

            fullNameLb = new JLabel(user.getFullName());
            fullNameLb.setFont(font);

            JLabel birthdayLbTitle = new JLabel("Birthday: ");
            birthdayLbTitle.setFont(font);

            birthdayLb = new JLabel(user.getBirthDay());
            birthdayLb.setFont(font);

            JLabel genderLbTitle = new JLabel("Gender:");
            genderLbTitle.setFont(font);

            genderLb = new JLabel(user.getGender());
            genderLb.setFont(font);

            groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(fullNameLbTitle)
                                        .addComponent(birthdayLbTitle)
                                        .addComponent(genderLbTitle)
                        )
                        .addGroup(
                                groupLayout.createParallelGroup()
                                        .addComponent(fullNameLb)
                                        .addComponent(birthdayLb)
                                        .addComponent(genderLb)
                        )

            );

            groupLayout.setVerticalGroup(
                    groupLayout.createSequentialGroup()
                            .addGroup(
                                    groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(fullNameLbTitle)
                                            .addComponent(fullNameLb)
                                            .addGap(50)
                            )
                            .addGroup(
                                    groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(birthdayLbTitle)
                                            .addComponent(birthdayLb)
                                            .addGap(50)
                            )
                            .addGroup(
                                    groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(genderLbTitle)
                                            .addComponent(genderLb)
                                            .addGap(50)
                            )
            );
        }

    }

    class NewPane extends JPanel implements ActionListener{
        public Message message;
        private User user;
        public NewPane(){
            this.setSize(700,600);

            this.setLayout(null);

            user = userBUS.getUser();

            message = new Message();
            message.setLocation(0,0);
            message.sendBtn.addActionListener(this);
            message.setFromName(user.getUsername());
            this.add(message);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == message.sendBtn){

                boolean isSend = messageBUS.Send(
                        message.fromTxt.getText(),
                        message.toTxt.getText(),
                        message.messageArea.getText(),
                        message.fileSend
                );

                if(!isSend){
                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid data!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(
                        this,
                        "Send success!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE,
                        new ImageIcon("src/Server/Images/success.png"));
                message.fromTxt.setText("");
                message.toTxt.setText("");
                message.messageArea.setText("");
                message.fileSendLb.setText("");
                message.deleteButton.setVisible(false);
                sendPane.update();
            }

        }
    }


    class ReceivePane extends JPanel{
        private JLabel root;
        private List<JLabel> labelList = new ArrayList<>();
        private List<BUS.Objects.Message> messageList = new ArrayList<>();
        private final CustomerMouseListener customMouseListener;
        private Message messageGui;
        public ReceivePane(){
            this.setSize(700,600);
            this.setLayout(null);
            customMouseListener = new CustomerMouseListener(this);

            root = new JLabel();
            root.setLayout(null);
            root.setSize(this.getSize());
            root.setLocation(0,0);
            this.add(root);

            update();
            this.setVisible(false);
        }

        public void update(){
            for (JLabel label :
                    labelList) {
                root.remove(label);
            }

            labelList.clear();
            messageList.clear();
            messageList = messageBUS.getAllMessageReceived();
            Collections.reverse(messageList);
            int y = 0;

            for (BUS.Objects.Message message:
                    messageList) {
                JLabel showMessage = new JLabel(message.getDate() + "       FROM: " + message.getFrom());
                showMessage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                showMessage.setLocation(0,y);
                showMessage.setSize(700,40);
                showMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                showMessage.addMouseListener(customMouseListener);
                labelList.add(showMessage);
                root.add(showMessage);
                y+=40;
            }
        }


        class CustomerMouseListener implements MouseListener,ActionListener{
            private ReceivePane receivePane;
            public CustomerMouseListener(ReceivePane receivePane){this.receivePane = receivePane;}

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                for (BUS.Objects.Message message:
                        messageList) {
                    if(
                            message.getDate()
                                    .equals(label.getText()
                                            .split("FROM:")[0]
                                            .trim())
                    ){
                        messageGui = new Message();
                        messageGui.setViewMode(message);
                        messageGui.backButton.addActionListener(this);
                        receivePane.root.setVisible(false);
                        receivePane.add(messageGui);
                        return;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                messageGui.setVisible(false);
                receivePane.root.setVisible(true);
            }
        }
    }

    class SendPane extends JPanel{
        public List<JLabel> labelList = new ArrayList<>();
        private List<BUS.Objects.Message> messageList = new ArrayList<>();
        private final JLabel root;
        private CustomMouseLister customMouseListener;
        private Message messageGui;
        public SendPane(){
            this.setSize(700,600);
            this.setLayout(null);
            customMouseListener = new CustomMouseLister(this);

            root = new JLabel();
            root.setLayout(null);
            root.setSize(this.getSize());
            root.setLocation(0,0);
            this.add(root);

            update();
            this.setVisible(false);
        }

        public void update(){
            for (JLabel label :
                    labelList) {
                root.remove(label);
            }

            labelList.clear();
            messageList.clear();
            messageList = messageBUS.getAllMessageSend();
            Collections.reverse(messageList);
            int y = 0;

            for (BUS.Objects.Message message:
                    messageList) {
                JLabel showMessage = new JLabel(message.getDate() + "       To: " + message.getTo());
                showMessage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                showMessage.setLocation(0,y);
                showMessage.setSize(700,40);
                showMessage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                showMessage.addMouseListener(customMouseListener);
                labelList.add(showMessage);
                root.add(showMessage);
                y+=40;
            }
        }

        class CustomMouseLister implements MouseListener, ActionListener{
            private SendPane sendPane;
            public CustomMouseLister(SendPane sendPane){
                this.sendPane = sendPane;
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                for (BUS.Objects.Message message:
                     messageList) {
                    if(
                            message.getDate()
                                    .equals(label.getText()
                                            .split("To:")[0]
                                            .trim())
                    ){
                        messageGui = new Message();
                        messageGui.setViewMode(message);
                        messageGui.backButton.addActionListener(this);
                        sendPane.root.setVisible(false);
                        sendPane.add(messageGui);
                        return;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                messageGui.setVisible(false);
                sendPane.root.setVisible(true);
            }
        }
    }
}