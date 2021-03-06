import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class Screen extends JFrame{
    private JPanel TopPanel;
    private JPanel RightPanel;
    private JPanel LeftPanel;
    private JList listPeople;
    private JButton buttonNew;
    private JButton buttonSave;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textNumber;
    private JTextField textAddress;
    private JLabel LabelAge;
    private JTextField textBirth;
    private JLabel LableAge;
    private JLabel Birth;
    private JPanel Maine;
    private JLabel LablAge;
    private JPanel Picture;
    private JButton clearButton;
    private ArrayList<Person> people;
    private DefaultListModel listPeopleModel;

    Screen(){
        super("My fancy contacts projects");
        this.setContentPane(this.Maine);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        people = new ArrayList<Person>();
        listPeopleModel = new DefaultListModel();
        listPeople.setModel(listPeopleModel);
        buttonSave.setEnabled(false);

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person p = new Person(
                        textName.getText(),
                        textEmail.getText(),
                        textNumber.getText(),
                        textAddress.getText(),
                        textBirth.getText());
                people.add(p);
                refreshPeopleList();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  int personNumber = listPeople.getSelectedIndex();
                if (personNumber >= 0);
                Person p = people.get(personNumber);
                p.setName(textName.getText());
                p.setEmail(textEmail.getText());
                p.setPhonenumber(textNumber.getText());
                p.setAddress(textAddress.getText());
                p.setDateofbirth(textBirth.getText());
                refreshPeopleList();


            }
        });
        listPeople.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int personNumber = listPeople.getSelectedIndex();
                if (personNumber >= 0) {
                    Person p = people.get(personNumber);
                    textName.setText(p.getName());
                    textEmail.setText(p.getEmail());
                    textNumber.setText(p.getPhonenumber());
                    textAddress.setText(p.getAddress());
                    textBirth.setText(p.getDateofbirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    LabelAge.setText( Integer.toString(p.getAge()) +" years");
                    buttonSave.setEnabled(true);
                } else
                    buttonSave.setEnabled(false);
            }
        });



    }
    public void refreshPeopleList() {
        listPeopleModel.removeAllElements();
        System.out.println("Removing people");
        for (Person p : people) {
            System.out.println("Adding person to list" + p.getName());
            listPeopleModel.addElement(p.getName());
        }
    }
    public void addPerson (Person p) {
        people.add(p);
        refreshPeopleList();
    }

    public static void main(String[] args) throws IOException{
        Screen screen = new Screen();
        screen.setSize(750,500);
        screen.setVisible(true);

        screen.Picture.setLayout(new FlowLayout());
        BufferedImage myPicture = ImageIO.read(new File("Addressbook.png"));
        Image dmyPicture = myPicture.getScaledInstance(109, 63, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(dmyPicture));
        screen.Picture.add(picLabel);

        FileWriter fw = new FileWriter("Contacts.txt", true);

        fw.write("Sheldon Lee Cooper, sheldon@gmail.com, 555 0001, route 66,26/02/1980\n");
        fw.write("Howard Joel Wolowitz, howard@gmail.com, 555 0002, Trafalgar square,01/03/1981\n");
        fw.write("Bernadette Rostenkowski-Wolowitz, bernadette@gmail.com, 555 0002, Broadway 55,01/01/1984\n");
        fw.write("Rajesh Ramayan Koothrappali, raj@gmail.com, 555 0003, Piazza San Carlo 26,06/10/1981\n");
        fw.write("Penny Hofstadter, penny@gmail.com, 555 0004, Via XX Settembre,02/12/1985\n");
        fw.write("Leonard Hofstadter, leonard@gmail.com, 555 0004, No address available,17/05/1980\n");
        fw.write("Amy Farrah Fowler, amy@gmail.com, 555 0005, Spingbars Avenue 34,17/12/1979\n");


        fw.close();
        FileReader fr = new FileReader("Contacts.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            String[] arraypeople = line.split(",");
            Person p = new Person(arraypeople[0], arraypeople[1], arraypeople[2], arraypeople[3], arraypeople[4]);
            screen.addPerson(p);
        }
        br.close();
        fr.close();
/*
        Person sheldon = new Person("Sheldon Lee Cooper", "sheldon@gmail.com", "555 0001","route 66", "26/02/1980");
        Person howard = new Person("Howard Joel Wolowitz", "howard@gmail.com", "555 0002", "Trafalgar square", "01/03/1981");
        Person bernadette = new Person("Bernadette Rostenkowski-Wolowitz", "bernadette@gmail.com", "555 0002", "Broadway 55",
                "01/01/1984");
        Person raj = new Person("Rajesh Ramayan Koothrappali", "raj@gmail.com", "555 0003", "Piazza San Carlo 26", "06/10/1981");
        Person penny = new Person("Penny Hofstadter", "penny@gmail.com", "555 0004", "Via XX Settembre 59", "02/12/1985");
        Person leonard = new Person("Leonard Hofstadter", "leonard@gmail.com", "555 0004","No address available", "17/05/1980");
        Person amy = new Person("Amy Farrah Fowler", "amy@gmail.com", "555 0005","Spingbars Avenue 34", "17/12/1979");

        screen.addPerson(sheldon);
        screen.addPerson(howard);
        screen.addPerson(bernadette);
        screen.addPerson(raj);
        screen.addPerson(penny);
        screen.addPerson(leonard);
        screen.addPerson(amy);
        */

    }
}
