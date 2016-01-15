import java.sql.*;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Asistencia extends JFrame {

	private JPanel contentPane;
	private JTextField textFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Asistencia frame = new Asistencia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	
	Connection conn = null;
	private JTable table;
	public Asistencia() throws SQLException  {
		conn = conectionTest.conectaraDB();
		String llenarquery = "Select Id_Alumno, Nombre, [Apellido 1] from Alumnos where Tipo = 'Regular' ";
		setTitle("SAYD - Academia Oh Do Kwan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	
			
		JLabel lblAsistencia = new JLabel("ASISTENCIA");
		lblAsistencia.setFont(new Font("Impact", Font.PLAIN, 30));
		lblAsistencia.setBounds(157, 23, 161, 38);
		contentPane.add(lblAsistencia);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.setBounds(206, 176, 41, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.setBounds(206, 267, 41, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblListaTotal = new JLabel("Lista de alumnos");
		lblListaTotal.setBounds(26, 159, 106, 14);
		contentPane.add(lblListaTotal);
		
		JLabel lblPresentes = new JLabel("Presentes");
		lblPresentes.setBounds(315, 159, 77, 14);
		contentPane.add(lblPresentes);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(167, 82, 29, 14);
		contentPane.add(lblFecha);
		
		textFecha = new JTextField();
		//Establecer fecha
		java.util.Date date = new java.util.Date();
		textFecha.setText(date.toString());
		textFecha.setBounds(206, 79, 86, 20);
		contentPane.add(textFecha);
		textFecha.setColumns(10);
		
		JButton btnGuardarAsistencia = new JButton("Guardar asistencia");
		btnGuardarAsistencia.setBounds(167, 366, 125, 23);
		contentPane.add(btnGuardarAsistencia);
		
		JButton btnMenuPrincipal = new JButton("Menu principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
			}
		});
		btnMenuPrincipal.setBounds(315, 444, 135, 23);
		contentPane.add(btnMenuPrincipal);
		
		JButton btnBuscarAlumnos = new JButton("Buscar alumnos");
		btnBuscarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("Clicked on this button");
				
				
				try {
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs = stmt.executeQuery(llenarquery); // esta linea crea un result set con el query que definimos en la variable query
					System.out.println(rs);
					
					while (rs.next()) {
				        String Nombre = rs.getString("Nombre"); // crea el string nombre y le asigna el valor del result set que tiene la columna nombre
				        String Apellido1 = rs.getString("Apellido 1");
				        System.out.println(Nombre + " " + Apellido1);
				        
				       // rellenar combo con los datos comboPresentes
				         
				         
				         
				    }
					
					//nombreAtl.setText(nombreatl);
					// the above line is the real deal
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // end of catch
				
				
				
				
				
				
			}
		});
		btnBuscarAlumnos.setBounds(167, 119, 125, 23);
		contentPane.add(btnBuscarAlumnos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 180, 106, 172);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}
}
