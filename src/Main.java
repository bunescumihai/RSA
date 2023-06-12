import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("RSA");
        f.setSize(1500, 600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn = new JButton("Criptare/Decriptare");
        btn.setBounds(40,500,150,50);



        JTextArea textPentruCriptare = new JTextArea();
        textPentruCriptare.setBounds(30,30, 450,300);
        textPentruCriptare.setLineWrap(true);
        JTextArea textCriptat = new JTextArea();
        textCriptat.setBounds(510,30, 450,300);
        textCriptat.setEditable(false);
        textCriptat.setLineWrap(true);
        JTextArea textDeCriptat = new JTextArea();
        textDeCriptat.setBounds(990,30, 450,300);
        textDeCriptat.setEditable(false);
        textDeCriptat.setLineWrap(true);


        JLabel jCaractereCriptate = new JLabel();
        jCaractereCriptate.setBounds(250, 360, 200, 30);
        JLabel jTimpCriptare = new JLabel();
        jTimpCriptare.setBounds(250, 410, 200, 30);
        JLabel jPAndQ = new JLabel();
        jPAndQ.setBounds(250, 460, 200, 30);
        JLabel jN = new JLabel();
        jN.setBounds(250, 510, 200, 30);
        JLabel jFiDeN =  new JLabel();
        jFiDeN.setBounds(500, 360, 200, 30);
        JLabel jD = new JLabel();
        jD.setBounds(500, 410, 200, 30);
        JLabel jCheiaPublica = new JLabel();
        jCheiaPublica.setBounds(500, 460, 200, 30);
        JLabel jChaiaPrivata = new JLabel();
        jChaiaPrivata.setBounds(500, 510, 200, 30);

        StringBuilder pentruCriptat = new StringBuilder();

        for(int i = 0; i < 40000; i++){
            pentruCriptat.append((char) i);
        }

        System.out.println(pentruCriptat);
        RSA rsa = new RSA(pentruCriptat.toString());

        System.out.println("p = " + rsa.getP());
        System.out.println("q = " + rsa.getQ());
        System.out.println("n = " + rsa.getN());
        System.out.println("fi(n) = " + rsa.getFiDeN());
        System.out.println("e = " + rsa.getE());
        System.out.println("d = " + rsa.getD());
        System.out.println(rsa.getCaractere());
        System.out.println(rsa.getCaracterMaxim());
        System.out.println(rsa.getTextCriptat().toString());
        System.out.println(rsa.getTextDecriptat());
        System.out.println("CPrivata(" + rsa.getD() + ", " + rsa.getN() + ")");
        System.out.println("CPublica(" + rsa.getE() + ", " + rsa.getN() + ")");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RSA rsa = new RSA(textPentruCriptare.getText());
                jCaractereCriptate.setText("Nr. caractere criptate = " + rsa.getNrCaractereCriptate());
                jTimpCriptare.setText("t = ");
                jPAndQ.setText("p = " + rsa.getP() + ", q =  " + rsa.getQ());
                jN.setText("n = " + rsa.getN());
                jFiDeN.setText("fi(n) = " + rsa.getFiDeN());
                jD.setText("d = " + rsa.getD());
                jCheiaPublica.setText("CPublica(" + rsa.getE() + ", " + rsa.getN() + ")");
                jChaiaPrivata.setText("CPrivata(" + rsa.getD() + ", " + rsa.getN() + ")");
                textCriptat.setText(rsa.getTextCriptat().toString());
                textDeCriptat.setText(rsa.getTextDecriptat());
            }
        });



        f.add(jCaractereCriptate);
        f.add(jTimpCriptare);
        f.add(jPAndQ);
        f.add(jN);
        f.add(jFiDeN);
        f.add(jD);
        f.add(jCheiaPublica);
        f.add(jChaiaPrivata);
        f.add(textPentruCriptare);
        f.add(textCriptat);
        f.add(textDeCriptat);
        f.add(btn);
        f.setLayout(null);
        f.setVisible(true);
        f.hashCode()
    }
}
