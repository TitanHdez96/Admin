package admin;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Iterator;

import javax.swing.*;

public class Window extends JFrame{
	GraphicsDevice gD = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	DisplayMode dM = gD.getDisplayMode();
	int width=dM.getWidth();
	int heigth=dM.getHeight();
	String idEmploy="";
	String del = null;
	public Window() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width/2,heigth/2);
		setLocationRelativeTo(null);
		
		Font f= new Font("Times New Roman", Font.BOLD, 20);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(54,89,100));
		panel.setLayout(null);
		int c=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","");
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from empleado");
			
			while(rs.next()) {
				//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9));
				JLabel empleado= new JLabel("EMPLEADO.- "+rs.getString(1)/*+"   NO_CONTROL.-"+ rs.getString(6)*/);
				empleado.setFont(f);
				empleado.setForeground(new Color(255,255,255));
				empleado.setBounds(0, c, 600, 20);
				panel.add(empleado);


				JButton edit= new JButton("Editar");
				edit.setFont(f);
				edit.setBounds(740,c,100,25);

				String nombre=rs.getString(1);
				String fecha = rs.getString(2);
				String correo = rs.getString(3);
				String pass = rs.getString(4);
				String dir = rs.getString(5);
				String id = rs.getString(6);
				String depar = rs.getString(7);
				String puesto = rs.getString(8);
				String banco = rs.getString(9);

				edit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new Edit(nombre,fecha,correo,pass,dir,id,depar,puesto,banco);
					}
				});


				JButton delete = new JButton("Eliminar");
				delete.setFont(f);
				delete.setBounds(600,c,135,25);



				idEmploy=rs.getString(6);
				delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							del = "DELETE FROM `empleado` WHERE `empleado`.`ID_EMPLEADO` = '"+idEmploy+"'";
							System.out.println(del);
							stmt.executeUpdate(del);
							panel.remove(delete);
							panel.remove(empleado);
							panel.validate();
							panel.repaint();
							JOptionPane.showMessageDialog(null,"Empleado Eliminado","Eliminar",JOptionPane.WARNING_MESSAGE);
						} catch (SQLException ex) {
							ex.printStackTrace();
						}

					}

				});
				c+=35;
				panel.add(delete);
				panel.add(edit);
			}
		}catch(Exception e) {
			System.out.println(e);
		}



		
		add(panel);
		
		validate();
		repaint();
	}
}
