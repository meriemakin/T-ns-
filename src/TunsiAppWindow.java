import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TunsiAppWindow extends JFrame {

    JTextArea textfieldOldTunsi;
    JTextArea textfieldModernTunsi;
    JButton transliterateButton;

    public TunsiAppWindow() {
        this.getContentPane().setLayout(null);
        this.initWindow();
        this.addWindowListener(new WindowListener()
        {
            public void windowClosed(WindowEvent arg0)
            {
            }
            public void windowActivated(WindowEvent e)
            {

            }
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
            public void windowDeactivated(WindowEvent e)
            {

            }
            public void windowDeiconified(WindowEvent e)
            {

            }
            public void windowIconified(WindowEvent e)
            {

            }
            public void windowOpened(WindowEvent e)
            {

            }
        });
    }

    protected void initWindow()
    {
        Font font = new Font("Default", Font.PLAIN, 20);
        textfieldOldTunsi = new JTextArea("أُمّي سيسي تُكنِس تُكنِس لقات فلَيِّس قالِت: \"آش نِشري بيه؟ آش نِشري بيه؟ زَعمَة نِشري قَدّيد نطَيِّب كُسكسي بنين لبنَيّتي؟\". أَيَّ مشات لِلزَزَّار وُ شرات قَدِّيد فاوَح وُ طَيّبِت كُسكسي زَيّنِتُ في تِبسي وُ حَطِّتُُ فوق طاولِةْ الكوجينَة.");
        textfieldOldTunsi.setFont(font);
        JScrollPane scrollTextFieldOldTunsi=new JScrollPane(textfieldOldTunsi);
        scrollTextFieldOldTunsi.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textfieldOldTunsi.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        transliterateButton = new JButton("Transliterate");
        textfieldModernTunsi = new JTextArea();
        textfieldModernTunsi.setFont(font);
        JScrollPane scrollTextFieldModernTunsi=new JScrollPane(textfieldModernTunsi);
        transliterateButton = new JButton("Transliterate");
        transliterateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                  transliterateClicked();
            }

        });

        int xOrigin = 5;
        int yOrigin = 10;
        int widthTextFieldOldTunsi = 440;
        int heightTextFieldOldTunsi = 200;
        int widthTextFieldModernTunsi = widthTextFieldOldTunsi;
        int heightTextFieldModernTunsi = heightTextFieldOldTunsi;
        int widthTransliterateButton = 150;
        int heightTransliterateButton = 20;
        int gap = 20;

        textfieldOldTunsi.setLineWrap(true);
        textfieldModernTunsi.setLineWrap(true);
        scrollTextFieldOldTunsi.setBounds(xOrigin, yOrigin, widthTextFieldOldTunsi, heightTextFieldOldTunsi);
        scrollTextFieldModernTunsi.setBounds(xOrigin, yOrigin+heightTextFieldOldTunsi+gap, widthTextFieldModernTunsi,
                heightTextFieldModernTunsi);
        transliterateButton.setBounds(xOrigin,yOrigin+heightTextFieldOldTunsi+heightTextFieldModernTunsi+2*gap,
                widthTransliterateButton,heightTransliterateButton);
        this.getContentPane().add(scrollTextFieldOldTunsi);
        this.getContentPane().add(scrollTextFieldModernTunsi);
        this.getContentPane().add(transliterateButton);
        this.pack();

        this.setTitle("Tunisian Transliteration (© Dr. Meriem Akin 2020)");
        this.setBounds(xOrigin, yOrigin, widthTextFieldOldTunsi+yOrigin+gap,
                (heightTextFieldOldTunsi+heightTextFieldModernTunsi+heightTransliterateButton+5*gap));

    }

    public void transliterateClicked()
    {
        TextTunsi text = new TextTunsi();
        text.setTextOldTunsi(textfieldOldTunsi.getText());
        text.setTextModernTunsi();
        textfieldModernTunsi.setText(new String(text.getTextModernTunsi()));
    }

}
