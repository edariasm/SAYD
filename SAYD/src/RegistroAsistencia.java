import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.util.Date;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegistroAsistencia extends JFrame {

	private JPanel contentPane;
	private JTextField NombreAl;
	private JTextField ApellidoAl;
	private JTable tablaDatosGen;
	private JTextField txtFechaHoy;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroAsistencia frame = new RegistroAsistencia();
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
	
	Connection conn = null; // set the connection
	
	
	public RegistroAsistencia() {
		
		conn = conectionTest.conectaraDB(); // establishes connection
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Date fechahoy = new Date();
		
		
		
		JLabel lblAsistencia = new JLabel("Control de Asistencia Diaria");
		lblAsistencia.setBounds(10, 11, 183, 33);
		contentPane.add(lblAsistencia);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 55, 46, 14);
		contentPane.add(lblNombre);
		
		NombreAl = new JTextField();
		NombreAl.setBounds(76, 52, 86, 20);
		contentPane.add(NombreAl);
		NombreAl.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(20, 95, 46, 14);
		contentPane.add(lblApellido);
		
		ApellidoAl = new JTextField();
		ApellidoAl.setBounds(76, 92, 86, 20);
		contentPane.add(ApellidoAl);
		ApellidoAl.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 222, 559, 113);
		contentPane.add(scrollPane);
		
		tablaDatosGen = new JTable();
		scrollPane.setViewportView(tablaDatosGen);
		
		JPanel Foto = new JPanel();
		Foto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Foto.setBounds(403, 11, 229, 171);
		contentPane.add(Foto);
		Foto.setLayout(null);
		
		JLabel labelFoto = new JLabel("");
		labelFoto.setBounds(28, 11, 175, 149);
		//Image img = new ImageIcon(this.getClass().getResource("/ODKLogo.png")).getImage();

		labelFoto.setIcon(new ImageIcon("C:\\Users\\301759\\Documents\\ODK\\SAYD\\gabrielaarias.png"));
		Foto.add(labelFoto);
		validate();
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(20, 380, 46, 14);
		contentPane.add(lblFecha);
		
		txtFechaHoy = new JTextField();
		txtFechaHoy.setBounds(64, 377, 129, 20);
		contentPane.add(txtFechaHoy);
		txtFechaHoy.setColumns(10);
		txtFechaHoy.setText(fechahoy.toString());
		
		JButton btnRegistrarAsistencia = new JButton("Registrar Asistencia");
		btnRegistrarAsistencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRegistrarAsistencia.setBounds(10, 429, 189, 23);
		contentPane.add(btnRegistrarAsistencia);
		
		JLabel lblAlerta = new JLabel("Alerta: Tiene pagos pendientes");
		lblAlerta.setForeground(Color.RED);
		lblAlerta.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAlerta.setBounds(255, 349, 486, 140);
		lblAlerta.setVisible(false);
		contentPane.add(lblAlerta);
		
		JButton btnBuscarAlumno = new JButton("Buscar Alumno");
		btnBuscarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombreAlumno = NombreAl.getText();
				String apellidoAlumno = ApellidoAl.getText();
				String queryalumno = "select "
						+ "Alumnos.Nombre"
						+ ",Alumnos.[Apellido 1]"
						+ ",Alumnos.[Apellido 2]"
						+ ",Alumnos.Mensualidad"
						+ ",Pagos.Mes"
						+ ",Pagos.Fecha_pago"
						+ ",Pagos.Monto_pago"
						+ " From Alumnos inner join Pagos"
						+ " on Alumnos.Id_Alumno = Pagos.ID_pago"
						+ " Where Nombre = '"+ nombreAlumno +"' and [Apellido 1] = '" + apellidoAlumno +"' and Monto_pago > 8000";
				JOptionPane.showMessageDialog(null, queryalumno);
				
				try {
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs1 = stmt.executeQuery(queryalumno); // esta linea crea un result set con el query que definimos en la variable query
					tablaDatosGen.setModel(DbUtils.resultSetToTableModel(rs1));
					
				} catch (Exception i) {
					JOptionPane.showMessageDialog(null, "A ocurrido un error de conexion " + i);
				}	
				
				 
				
				
			}
		});
		btnBuscarAlumno.setBounds(202, 91, 129, 23);
		contentPane.add(btnBuscarAlumno);
	}
}
