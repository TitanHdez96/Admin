package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Edit extends JFrame {
    GraphicsDevice gD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    DisplayMode dM = gD.getDisplayMode();
    int width=dM.getWidth();
    int heigth=dM.getHeight();
    public Edit(String nombre,String fecha,String correo,String pass,String dir,String id,String depar,String puesto,String banco)  {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(width/3,heigth/2);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from empleado");

        } catch (Exception e) {
            System.out.println(e);

        }
    }
}
