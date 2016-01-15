import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.html.AccessibleHTML.TableElementInfo.TableAccessibleContext;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class ReporteAlumnos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtApellido1;
	private JTextField nombreCompleto;
	private JTextField txtDeEnero;
	private JTextField txtMensualidad;
	private JTextField txtUltimoPago;
	private JTextField txtConcepto;
	private JTextField txtAsistencia;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteAlumnos frame = new ReporteAlumnos();
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
	private JTable table_1;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JTable table_2;
	private JTable table_3;
	
	
	
	
	
	
	public ReporteAlumnos() {
		
		conn = conectionTest.conectaraDB();
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1500, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblReporteAlumnos = new JLabel("Reportes de alumnos");
		lblReporteAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReporteAlumnos.setBounds(10, 11, 194, 30);
		contentPane.add(lblReporteAlumnos);
		
		JButton btnBuscarAlumno = new JButton("Buscar alumno");
		btnBuscarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombreAlumno = txtNombre.getText();
				String apellidoAlumno = txtApellido1.getText();
				String querybuscarAlumno = "Select * "
						+ "from Alumnos "
						+ "where Nombre = '" + nombreAlumno + "'" + "and [Apellido 1] = '"+ apellidoAlumno+"'";
				String queryultpago = "Select Alumnos.Nombre,Alumnos.[Apellido 1],Pagos.Monto_pago,Pagos.Fecha_pago,Pagos.Concepto_pago "
						+ "from Alumnos inner join Pagos on Alumnos.Id_Alumno = Pagos.ID_Alumno "
						+ "Where Nombre = '" + nombreAlumno +"' and Fecha_pago < GETDATE()";
				String queryAsistencia = "Select Count(Asistencia.Fecha) "
						+ "from Alumnos inner join Asistencia	on Alumnos.Id_Alumno = Asistencia.ID_Alumno "
						+ "where Nombre = '" + nombreAlumno +"' and [Apellido 1] = '"+apellidoAlumno+"' and Fecha < GETDATE()";
				String queryAsistDetalle = "Select Asistencia.Fecha, alumnos.nombre, alumnos.[Apellido 1] "
						+ "from Alumnos inner join Asistencia  on Alumnos.Id_Alumno = Asistencia.ID_Alumno "
						+ "where Nombre = '"+nombreAlumno+"' and [Apellido 1] = '"+apellidoAlumno+"' and Fecha < GETDATE()";
				String querypruebas = "select Alumnos.Nombre, "
						+ "Alumnos.[Apellido 1], "
						+ "Mediciones.FechaPrueba, "
						+ "Mediciones.LadoALado, "
						+ "Mediciones.Seguidillas, "
						+ "Mediciones.SaltoLargo, "
						+ "Mediciones.Abs, "
						+ "Mediciones.Peso, "
						+ "Mediciones.Talla "
						+ "from Alumnos inner join Mediciones on Alumnos.Id_Alumno = Mediciones.ID_Alumno Where "
						+ "Nombre = '"
						+ nombreAlumno
						+"' and [Apellido 1] = '"
						+ apellidoAlumno
				        + "' and FechaPrueba < GETDATE()";
				
				try {
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs = stmt.executeQuery(querybuscarAlumno); // esta linea crea un result set con el query que definimos en la variable query
					
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(queryultpago);
					
					Statement stmt3 = conn.createStatement();
					ResultSet rs3 = stmt3.executeQuery(queryAsistencia);
					
					Statement stmt4 = conn.createStatement();
					ResultSet rs4 = stmt4.executeQuery(queryAsistDetalle);
					
					Statement stmt5 = conn.createStatement();
					ResultSet rs5 = stmt5.executeQuery(querypruebas);
				 
					while (rs.next()) {
						
				        String Nombre = rs.getString("Nombre"); // crea el string nombre y le asigna el valor del result set que tiene la columna nombre
				        String Apellido1 = rs.getString("Apellido 1");
				        String nombreCompleto1 = Nombre + " " + Apellido1;
				        String Mensualidad = rs.getString("Mensualidad");
				         
				        nombreCompleto.setText(nombreCompleto1);
				        txtMensualidad.setText(Mensualidad);
				         
				    }
					
					while (rs2.next()) {
				         String montoultpago = rs2.getString("Monto_pago");
				         String concultpago = rs2.getString("Concepto_pago");
				         txtUltimoPago.setText(montoultpago);
				         txtConcepto.setText(concultpago);
				         
				    }
					
					 while (rs3.next()) {
						int reslt = rs3.getInt(1);
						String resultado = String.valueOf(reslt);
						txtAsistencia.setText(resultado);
									         
				  }
					
					
					
					 	table_2.setModel(DbUtils.resultSetToTableModel(rs4)); // llena la tabla 2 con los resultados del result set. Utiliza el jar r2xml
					 	table_3.setModel(DbUtils.resultSetToTableModel(rs5));
					 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 
			} 
			
			
		});
		
		btnBuscarAlumno.setBounds(348, 60, 138, 23);
		contentPane.add(btnBuscarAlumno);
		
		table = new JTable();
		table.setBounds(34, 263, 149, -143);
		table.setBackground(Color.white);
		table.setOpaque(true);
		contentPane.add(table);
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setBounds(31, 61, 121, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido1 = new JTextField();
		txtApellido1.setText("Apellido 1");
		txtApellido1.setBounds(180, 61, 110, 20);
		contentPane.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		JLabel lblNombreCompleto = new JLabel("Nombre completo");
		lblNombreCompleto.setBounds(34, 92, 194, 14);
		contentPane.add(lblNombreCompleto);
		
		nombreCompleto = new JTextField();
		nombreCompleto.setEnabled(false);
		nombreCompleto.setBounds(34, 106, 194, 20);
		contentPane.add(nombreCompleto);
		nombreCompleto.setColumns(10);
		
		JLabel lblFechaInicial = new JLabel("Inicio del reporte");
		lblFechaInicial.setBounds(34, 199, 118, 14);
		contentPane.add(lblFechaInicial);
		
		txtDeEnero = new JTextField();
		txtDeEnero.setEnabled(false);
		txtDeEnero.setText("4 de enero 2016");
		txtDeEnero.setBounds(162, 196, 103, 20);
		contentPane.add(txtDeEnero);
		txtDeEnero.setColumns(10);
		
		JLabel lblMensualidad = new JLabel("Mensualidad");
		lblMensualidad.setBounds(34, 137, 86, 14);
		contentPane.add(lblMensualidad);
		
		txtMensualidad = new JTextField();
		txtMensualidad.setEnabled(false);
		txtMensualidad.setBounds(34, 156, 86, 20);
		contentPane.add(txtMensualidad);
		txtMensualidad.setColumns(10);
		
		txtUltimoPago = new JTextField();
		txtUltimoPago.setEnabled(false);
		txtUltimoPago.setBounds(152, 156, 86, 20);
		contentPane.add(txtUltimoPago);
		txtUltimoPago.setColumns(10);
		
		JLabel lblUltimoPago = new JLabel("Ultimo pago");
		lblUltimoPago.setBounds(152, 137, 86, 14);
		contentPane.add(lblUltimoPago);
		
		JLabel lblNewLabel = new JLabel("Concepto");
		lblNewLabel.setBounds(267, 137, 159, 14);
		contentPane.add(lblNewLabel);
		
		txtConcepto = new JTextField();
		txtConcepto.setEnabled(false);
		txtConcepto.setBounds(267, 156, 279, 20);
		contentPane.add(txtConcepto);
		txtConcepto.setColumns(10);
		
		JLabel lblAsistenciaDuranteEl = new JLabel("Asistencia durante el periodo");
		lblAsistenciaDuranteEl.setBounds(85, 263, 186, 14);
		contentPane.add(lblAsistenciaDuranteEl);
		
		txtAsistencia = new JTextField();
		txtAsistencia.setEnabled(false);
		txtAsistencia.setBounds(130, 283, 53, 20);
		contentPane.add(txtAsistencia);
		txtAsistencia.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Detalle de asistencia");
		lblDetalle.setBounds(100, 314, 149, 14);
		contentPane.add(lblDetalle);
		
		JLabel lblComptenciasDuranteEl = new JLabel("Comptencias durante el periodo");
		lblComptenciasDuranteEl.setBounds(431, 263, 209, 14);
		contentPane.add(lblComptenciasDuranteEl);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setBounds(477, 283, 53, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblDetalle_1 = new JLabel("Detalle de Competencias");
		lblDetalle_1.setBounds(450, 314, 159, 14);
		contentPane.add(lblDetalle_1);
		
		JLabel lblMedicionesFisicas = new JLabel("Mediciones fisicas");
		lblMedicionesFisicas.setBounds(809, 263, 138, 14);
		contentPane.add(lblMedicionesFisicas);
		
		JLabel lblDetalle_2 = new JLabel("Detalle de mediciones");
		lblDetalle_2.setBounds(809, 314, 127, 14);
		contentPane.add(lblDetalle_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 338, 289, 245);
		contentPane.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(699, 363, 606, 210);
		contentPane.add(scrollPane_1);
		
		table_3 = new JTable();
		scrollPane_1.setViewportView(table_3);
		
		JButton btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
				close();
				
			}
		});
		btnMenuPrincipal.setBounds(496, 60, 121, 23);
		contentPane.add(btnMenuPrincipal);
		
	
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column"
			}
		));
		
		
	}
	
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
	
	
}
