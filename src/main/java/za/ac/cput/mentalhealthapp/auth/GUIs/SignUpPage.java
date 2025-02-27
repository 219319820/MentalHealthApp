package za.ac.cput.mentalhealthapp.auth.GUIs;

import za.ac.cput.mentalhealthapp.DAO.StudentDatabase;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SignUpPage extends JFrame {

    private JPanel frame;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = new Dimension(360,600);

    public SignUpPage(){

        int width = ss.width/2 - frameSize.width/2;
        int hight = ss.height/2 - frameSize.height/2;


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(width,hight,360,600);
        setResizable(false);
        frame = new JPanel();
        frame.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(frame);
        frame.setLayout(null);

        // DISPLAY BACK BUTTON
        JButton btnBack = new JButton("Back");
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBounds(2,5, 70, 25);
        frame.add(btnBack);

        // DISPLAYS BIG TITLE
        JLabel title = new JLabel("Affirmation");
        title.setFont(new Font("Arial",Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setBounds(65,50,360,50);
        frame.add(title);

        // DISPLAYS SIGNUP TEXT
        JLabel signUpTitle = new JLabel("Sign Up");
        signUpTitle.setFont(new Font("Arial",Font.BOLD, 18));
        signUpTitle.setBounds(145,100,360,50);
        frame.add(signUpTitle);

        // NAME LABEL
        JLabel firstName = new JLabel("First Name:");
        firstName.setFont(new Font("", Font.PLAIN,18));
        firstName.setBounds(20, 150, 100, 30);
        frame.add(firstName);

        // NAME TEXT FIELD
        final JTextField firstNameTx = new JTextField("");
        firstNameTx.setBounds(20, 180, 130, 30);
        frame.add(firstNameTx);

        // LAST NAME LABEL
        JLabel lastName = new JLabel("Last Name:");
        lastName.setFont(new Font("", Font.PLAIN,18));
        lastName.setBounds(180, 150, 100, 30);
        frame.add(lastName);

        // LAST NAME TEXT FIELD
        final JTextField lastNameTx = new JTextField("");
        lastNameTx.setBounds(180, 180, 140, 30);
        frame.add(lastNameTx);

        // EMAIL LBL
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("", Font.PLAIN,18));
        email.setBounds(20, 230, 50, 30);
        frame.add(email);

        // EMAIL DOMAIN LABEL
        JLabel emaildb = new JLabel("@mycput.ac.za");
        emaildb.setFont(new Font("", Font.PLAIN,18));
        emaildb.setBounds(100, 260, 150, 30);
        frame.add(emaildb);

        // EMAIL TEXT FIELD
        final JFormattedTextField emailTx = new JFormattedTextField(NumberFormat.getIntegerInstance());
        emailTx.setToolTipText("Student email");
        emailTx.setBounds(20, 260, 80, 30);
        frame.add(emailTx);

        // PASSWORD LBL
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("", Font.PLAIN,18));
        password.setBounds(20, 300, 200, 30);
        frame.add(password);

        // PASSWORD TEXT FIELD
        final JPasswordField passwordTx = new JPasswordField("");
        passwordTx.setBounds(20, 330, 160, 30);
        frame.add(passwordTx);

        // CONFIRM PASSWORD LBL
        final JLabel cnfPassword = new JLabel("Confirm Password:");
        cnfPassword.setFont(new Font("", Font.PLAIN,18));
        cnfPassword.setBounds(20, 370, 200, 30);
        frame.add(cnfPassword);

        // CONFIRM PASSWORD TEXT FIELD
        final JPasswordField cnfPasswordTx = new JPasswordField("");
        cnfPasswordTx.setBounds(20, 400, 160, 30);
        frame.add(cnfPasswordTx);

        // ERROR MESSAGE LBL
        final JLabel errorLbl = new JLabel("");
        errorLbl.setFont(new Font("", Font.PLAIN,15));
        errorLbl.setForeground(Color.RED);
        errorLbl.setBounds(20, 435, 270, 30);
        frame.add(errorLbl);

        // CREATE/CONTINUE ACCOUNT BUTTON
        JButton btnContinue = new JButton("Continue");
        btnContinue.setBackground(new Color(0,162,232));
        btnContinue.setForeground(Color.WHITE);
        btnContinue.setFont(new Font("Arial",Font.BOLD, 21));
        btnContinue.setBounds(117, 470,125, 40);
        frame.add(btnContinue);

        // CHECK BOX
        final JCheckBox pwCheckBox = new JCheckBox("",false);
        pwCheckBox.setBackground(new Color(0,162,232));
        pwCheckBox.setBounds(200, 335,20,20);
        frame.add(pwCheckBox);

        // CHECK BOX
        final JCheckBox checkBox = new JCheckBox("",false);
        checkBox.setBackground(new Color(0,162,232));
        checkBox.setBounds(200, 405,20,20);
        frame.add(checkBox);


        // DISPLAY BACKGROUND IMAGE (BLUE AND WHITE)
        BufferedImage bgImage = null;
        try {
            bgImage = ImageIO.read(new File("bg_image2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(bgImage));
        background.setBounds(0,0, 360, 600);
        frame.add(background);

        // LIMITS EMAIL TO ONLY INPUT 9 CHARACTERS - THE LENGTH OF A CPUT STUDENT NUMBER
        emailTx.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (emailTx.getText().length() >= 9 ) // limit textField to 9 characters
                    e.consume();
            }
        });

        // LIMITS EMAIL TO ONLY NUMERIC CHARACTERS
        emailTx.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String value = emailTx.getText();
                int l = value.length();

                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')||e.getKeyCode() ==KeyEvent.VK_BACK_SPACE) {
                    emailTx.setEditable(true);
                }
                else {
                    emailTx.setEditable(false);
                }
            }
        });



        // PASSWORD REVEALED IF CHECKED
        pwCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pwCheckBox.isSelected()){
                    passwordTx.setEchoChar((char) 0);
                }
                else {
                    passwordTx.setEchoChar('•');
                }
            }
        });

        // CONFIRM PASSWORD REVEALED IF CHECKED
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()){
                    cnfPasswordTx.setEchoChar((char) 0);
                }
                else {
                    cnfPasswordTx.setEchoChar('•');
                }
            }
        });

        // BACK BUTTON FUNCTIONS
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.setVisible(true);
            }
        });

        final StudentDatabase studentDatabase = new StudentDatabase();

        // CONTINUE BUTTON FUNCTIONS
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = firstNameTx.getText();
                String lastName = lastNameTx.getText();
                String email = emailTx.getText();
                char[] password = passwordTx.getPassword();
                char[] cnfPassword = cnfPasswordTx.getPassword();
                StringTokenizer st = new StringTokenizer(email,"@");
                String studentID;

                String decryptedPassword = Arrays.toString(passwordTx.getPassword());
                String decryptedCNFPassword = Arrays.toString(cnfPasswordTx.getPassword());

                //errorLbl.setText("One or more fields ar empty");

                if ((firstName.equals("")) || (lastName.equals("")) || (email.equals("")) ){
                    errorLbl.setText("One or more fields are empty!!");
                }
                else if (email.length()<9){
                    errorLbl.setText("Invalid Cput student email");
                }
                else if (password.length == 0 || cnfPassword.length == 0){
                    errorLbl.setText("One or both Password fields are empty");

                }
                else if (!(decryptedPassword.equals(decryptedCNFPassword))){
                    errorLbl.setText("Passwords don't match");

                }
                else if (StudentDatabase.checkUserExistence(email)){
                    errorLbl.setText("User Already Exists");
                }


                else {

                    errorLbl.setText("");

                    studentID = st.nextToken();
                    email = emailTx.getText()+"@mycput.ac.za";

                    studentDatabase.connectToDatabase();

                    studentDatabase.insertToTable(
                            studentID,
                            firstName,
                            lastName,
                            email,
                            password
                    );

                    JOptionPane.showMessageDialog(frame, "Account created successfully.\n" +
                            firstName+" "+lastName+"\n"+email);

                    firstNameTx.setText("");
                    lastNameTx.setText("");
                    emailTx.setText("");
                    passwordTx.setText("");
                    cnfPasswordTx.setText("");

                }
            }
        });
    }

   /* private String insertData(String studentID, String fName, String lNAme, String email, String password){
        String insert_values_statement = "insert into STUDENT_DETAILS values('" + studentID + "','" + fName + "','" + lNAme + "','" +
                email + "','" + password + "')";
        return insert_values_statement;
    }*/
}