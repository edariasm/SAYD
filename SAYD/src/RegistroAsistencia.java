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
import java.awt.Toolkit;
import java.util.Date;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.util.Calendar;

public class RegistroAsistencia extends JFrame {

	private JPanel contentPane;
	private JTextField NombreAl;
	private JTextField txtApellidoAl;
	private JTable tablaDatosGen;
	private JTextField txtFechaHoy;
	String pathImagen = "C:\\Users\\301759\\Documents\\ODK\\SAYD\\ODK.png"; 
	
	
	
	
	
	

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
	private JTable tablePagos;
	private JTextField txtFechadeHoy;
	
	
	public RegistroAsistencia() {
		
		conn = conectionTest.conectaraDB(); // establishes connection
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		
		
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
		
		txtApellidoAl = new JTextField();
		txtApellidoAl.setBounds(76, 92, 86, 20);
		contentPane.add(txtApellidoAl);
		txtApellidoAl.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 222, 278, 113);
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
		//labelFoto.setIcon(new ImageIcon("C:\\Users\\301759\\Documents\\ODK\\SAYD\\gabrielaarias.png"));
		labelFoto.setIcon(new ImageIcon(pathImagen));
		Foto.add(labelFoto);
		validate();
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(20, 380, 46, 14);
		contentPane.add(lblFecha);
		
		
		
		JButton btnRegistrarAsistencia = new JButton("Registrar Asistencia");
		btnRegistrarAsistencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				try {
					
					
					
					 
					String nombreAlumno = NombreAl.getText();
					String todayDate = txtFechadeHoy.getText();
					String ApellidoAl = txtApellidoAl.getText();

					String queryID = "Select Id_alumno from Alumnos where Nombre = '"+ nombreAlumno + "' and [Apellido 1] = '" + ApellidoAl +"'";
					
					Statement stmtINS = conn.createStatement(); 
					ResultSet rs3 = stmtINS.executeQuery(queryID);
					
					while (rs3.next()) {
				        String idAlumno = rs3.getString("ID_Alumno");
				        String queryInsAsistencia = "Insert into Asistencia(Id_Alumno, Fecha) values (" + idAlumno + ",'"+todayDate+"')";				 
						stmtINS.executeUpdate(queryInsAsistencia); 
						JOptionPane.showMessageDialog(null, "Feliz entrenamiento ");
						
				        
				              
				    }
					
					
					
				} catch (Exception i) {
					//JOptionPane.showMessageDialog(null, "Ocurrio un error de conexion al insertar"+ i);
				}
				
				
			
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
				String apellidoAlumno = txtApellidoAl.getText();
				String queryalumno = "select "
						+ "Alumnos.Nombre"
						+ ",Alumnos.[Apellido 1]"
						+ ",Alumnos.[Apellido 2]"
						+ ",Alumnos.Mensualidad"
						+ " From Alumnos"
						+ " Where Nombre = '"+ nombreAlumno +"' and [Apellido 1] = '" + apellidoAlumno +"'";
				
				String queryPagos =  "select "
						+ " Pagos.Mes "
						+ ",Pagos.Fecha_pago"
						+ ",Pagos.Monto_pago"
						+ " From Alumnos inner join Pagos"
						+ " on Alumnos.Id_Alumno = Pagos.ID_Alumno"
						+ " Where Nombre = '"+ nombreAlumno +"' and [Apellido 1] = '" + apellidoAlumno +"' and Monto_pago > 8000";
				
				
				pathImagen = "C:\\Users\\301759\\Documents\\ODK\\SAYD\\" +nombreAlumno+apellidoAlumno+".jpg";
				labelFoto.setIcon(new ImageIcon(pathImagen));
				Foto.add(labelFoto);
				validate();
				
				
				try {
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs1 = stmt.executeQuery(queryalumno); // esta linea crea un result set con el query que definimos en la variable query
					//IdAlumno = rs1.getString("id_alumno");
					tablaDatosGen.setModel(DbUtils.resultSetToTableModel(rs1));
					
					
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(queryPagos); // esta linea crea un result set con el query que definimos en la variable query
					tablePagos.setModel(DbUtils.resultSetToTableModel(rs2));
					
					
					
				} catch (Exception i) {
					//JOptionPane.showMessageDialog(null, " Error de conexion " + i);
				}	
				
				 
				
				
			}
		});
		btnBuscarAlumno.setBounds(202, 91, 129, 23);
		contentPane.add(btnBuscarAlumno);
		
		JLabel lblDatosDelAtleta = new JLabel("Datos del Atleta");
		lblDatosDelAtleta.setBounds(20, 197, 104, 14);
		contentPane.add(lblDatosDelAtleta);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(344, 221, 343, 114);
		contentPane.add(scrollPane_1);
		
		tablePagos = new JTable();
		scrollPane_1.setViewportView(tablePagos);
		
		JLabel lblHistorialDelPagos = new JLabel("Historial del pagos");
		lblHistorialDelPagos.setBounds(346, 197, 202, 14);
		contentPane.add(lblHistorialDelPagos);
		
		txtFechadeHoy = new JTextField();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String sDate= sdf.format(date);
		String year1 = sDate.toString();
		txtFechadeHoy.setText(year1);
		txtFechadeHoy.setBounds(60, 377, 86, 20);
		contentPane.add(txtFechadeHoy);
		txtFechadeHoy.setColumns(10);
		
		JButton btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
				close();
			}
		});
		btnMenuPrincipal.setBounds(10, 535, 189, 23);
		contentPane.add(btnMenuPrincipal);
		
		JButton btnRealizarPago = new JButton("Realizar Pago");
		btnRealizarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Cobros cc;
				try {
					cc = new Cobros();
					cc.setVisible(true);
					close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRealizarPago.setBounds(255, 535, 183, 23);
		contentPane.add(btnRealizarPago);
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
