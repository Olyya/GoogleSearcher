package myproject.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Olya on 08/06/16.
 */
public class Gui implements ActionListener {
    JLabel lable = new JLabel("Input whatever you want to find");
    JTextField textfield = new JTextField(20);
    JButton searchButton = new JButton("Search");

     public void openwindow() {
        JFrame window = new JFrame("Google searcher");
        Container contentPane = window.getContentPane();
        contentPane.setSize(500,500);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        searchButton.addActionListener(this);

        contentPane.add(lable);
        contentPane.add(textfield);
        contentPane.add(searchButton);

        window.pack();
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WorkwithWebDriver workDrive = null;
        TextArea mytest = null;
        try {
            String url = "https://www.google.com.ua/";
            String searching = textfield.getText();
            System.out.println(searching);
            workDrive = new WorkwithWebDriver();

            workDrive.openBrowser();
            workDrive.openPage(url);
            workDrive.enterSearchWords(searching);

            String lastchance = null;
            mytest = new TextArea();

            try {
                List<String> titles = workDrive.findHeaderofArticle(); //titles >> need show it
                for (int i = 0; i < titles.size(); i++) {
                    lastchance = titles.get(i).toString();
//                System.out.println("lastchance - " + lastchance);
                    mytest.append(lastchance + "\n");
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("Problem with listener");
        } finally {
            workDrive.closeBrowser();
            Frame res = new Frame("result");
            JOptionPane.showMessageDialog(res, mytest);
        }
    }
}
