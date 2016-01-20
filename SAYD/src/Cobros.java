import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.sql.ResultSetMetaData;
import java.sql.Connection;


import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

public class Cobros extends JFrame {

	private JPanel contentPane;
	private JTextField nombreAtl;
	private JTextField apellido1;
	private JTextField txtMens;
	private JTextField txtFechadia;
	private JTextField txtMontoPago;
	private JTextField txtConceptoPago;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cobros frame = new Cobros();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	Connection conn = null;
	private JTextField txtApellido;
	private JTextField txtCodigo;
	private JTextField txtFechaMes;
	private JTextField txtFechaAnno;
	private JTextField txtfechaPago;
	private JTextField txtMontoUltPago;
	private JTextField txtConcUltPago;
	private JTable table;
	
	public Cobros() throws SQLException {
		
		conn = conectionTest.conectaraDB();
		setTitle("SAYD - Academia Oh Do Kwan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1084, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
			
		
		JLabel lblCobros = new JLabel("COBROS");
		lblCobros.setFont(new Font("Impact", Font.PLAIN, 30));
		lblCobros.setBounds(168, 23, 206, 38);
		contentPane.add(lblCobros);
		
		JButton btnBuscarAtleta = new JButton("Buscar atleta");
		btnBuscarAtleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombreAlumno = txtNombre.getText();
				String apellidoAlumno = txtApellido.getText();
				String queryalumno = "Select * from Alumnos where Nombre = '" + nombreAlumno + "'" + "and [Apellido 1] = '"+ apellidoAlumno+"'";
				String queryultpago = "Select Alumnos.Nombre,Alumnos.[Apellido 1],Pagos.Monto_pago,Pagos.Fecha_pago,Pagos.Concepto_pago from Alumnos inner join Pagos on Alumnos.Id_Alumno = Pagos.ID_Alumno Where Nombre = '" + nombreAlumno +"' and Fecha_pago < GETDATE()";
				String historialpagos = "Select "
						+ "	Alumnos.Nombre"
						+ "	,Alumnos.[Apellido 1]"
						+ "	,Pagos.Monto_pago"
						+ "	,Pagos.Concepto_pago"
						+ "	,Pagos.Fecha_pago "
						+ "from Alumnos inner join Pagos on Alumnos.Id_Alumno = Pagos.ID_Alumno "
						+ "Where"
						+ "	Nombre = '"
						+ nombreAlumno
						+"' "
						+" and "
						+"[Apellido 1]"
						+ "= '"
						+ apellidoAlumno
						+"'"
						+ " and Fecha_pago < GETDATE()";
				
				//System.out.println(historialpagos);
								
				try {
														
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs = stmt.executeQuery(queryalumno); // esta linea crea un result set con el query que definimos en la variable query
					System.out.println(rs);
					
					Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery(queryultpago);
					
					Statement stmt3 = conn.createStatement();
					ResultSet rs3 = stmt3.executeQuery(historialpagos);
					
					
					while (rs.next()) {
				        String Nombre = rs.getString("Nombre"); // crea el string nombre y le asigna el valor del result set que tiene la columna nombre
				        String Apellido1 = rs.getString("Apellido 1");
				        String Mensualidad = rs.getString("Mensualidad");
				        String Codigo = rs.getString("ID_Alumno");
				         System.out.println(Nombre + " " + Apellido1 + " " + "Mensualidad: " + " " + Mensualidad);
				         nombreAtl.setText(Nombre);
				         apellido1.setText(Apellido1);
				         txtMens.setText(Mensualidad);
				         txtCodigo.setText(Codigo);
				         
				         nombreAtl.setEditable(false);
				         apellido1.setEditable(false);
				         txtMens.setEditable(false);
				         txtCodigo.setEditable(false);
				         
				         
				    }
					
					while (rs2.next()) {
				         String montoultpago = rs2.getString("Monto_pago");
				         String fechaultpago = rs2.getString("Fecha_pago");
				         String concultpago = rs2.getString("Concepto_pago");
				         
				         
				         txtfechaPago.setText(fechaultpago);
				         txtMontoUltPago.setText(montoultpago);
				         txtConcUltPago.setText(concultpago);
				         
				         
				         txtfechaPago.setEditable(false);
				         txtMontoUltPago.setEditable(false);
				         txtConcUltPago.setEditable(false);
				         
				         
				         
				    }
					
					
					table.setModel(DbUtils.resultSetToTableModel(rs3)); // llena la tabla 2 con los resultados del result set. Utiliza el jar r2xml
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		btnBuscarAtleta.setBounds(339, 96, 122, 23);
		contentPane.add(btnBuscarAtleta);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 177, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 211, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblMensualidad = new JLabel("Mensualidad");
		lblMensualidad.setBounds(10, 248, 102, 14);
		contentPane.add(lblMensualidad);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(30, 286, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(30, 321, 46, 14);
		contentPane.add(lblMonto);
		
		JButton btnRegistrarPago = new JButton("Registrar pago");
		btnRegistrarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombreAlumno = txtNombre.getText();
				String apellidoAlumno = txtApellido.getText();
				String queryalumnos = "Select * from Alumnos where Nombre = '" + nombreAlumno + "'" + "and [Apellido 1] = '"+ apellidoAlumno+"'";
				
				try {
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs = stmt.executeQuery(queryalumnos); // esta linea crea un result set con el query que definimos en la variable query
					System.out.println(rs);
					
					while (rs.next()) {
				
						String Codigo = rs.getString("Id_Alumno");
						String FechaP = txtFechaAnno.getText() + txtFechaMes.getText() + txtFechadia.getText();
						String Mes = txtFechaMes.getText();
						String Anno = txtFechaAnno.getText();
						String Monto = txtMontoPago.getText();
						String Conc = txtConceptoPago.getText();
						
						
					String queryInsPago = "insert into Pagos (ID_Alumno, Fecha_pago, Monto_pago, Concepto_pago, mes, anno) Values (" + Codigo + "," + "'" + FechaP + "'," + Monto + ", '" + Conc + "'," + Mes+ "," + Anno + ")";
					
					Statement stmtINS = conn.createStatement(); // crea un statement 
					
					
					try {
						stmtINS.executeUpdate(queryInsPago); // ejecuta un insert a la base de datos con el query queryInsPago que toma los parametros de pantalla
						JOptionPane.showMessageDialog(null, "Pago registrado satisfactoriamente");
						txtNombre.setText("");
						txtApellido.setText("");
						nombreAtl.setText("");
						txtCodigo.setText("");
						apellido1.setText("");
						txtMens.setText("");
						txtMontoPago.setText("");
						txtConceptoPago.setText("");
						txtfechaPago.setText("");
						txtMontoUltPago.setText("");
						txtConcUltPago.setText("");
						
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Debe ingresar los datos del pago");
					}
					
				    }
							
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRegistrarPago.setBounds(10, 405, 122, 23);
		contentPane.add(btnRegistrarPago);
		
		JButton btnImprimirRecibo = new JButton("Imprimir recibo");
		btnImprimirRecibo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String odkReceiptTitle = "Academia de Taekwondo"; 
				String academia = "Oh Do Kwan";
				String odkReceiptSubtitle = "Recibo por dinero";
				String estudiante = txtNombre.getText() + " " + txtApellido.getText();
				String fechadepago = txtfechaPago.getText();
				String montopagado = txtMontoPago.getText();
				String conceptopago = txtConceptoPago.getText();
				String cuerporeceipt = conceptopago;
				
				JTextArea txtReceipt = new JTextArea();
				txtReceipt.setBounds(502, 11, 189, 180);
				contentPane.add(txtReceipt);
								
				txtReceipt.setText(odkReceiptTitle + "\n" + 
				academia + "\n" +
				odkReceiptSubtitle + "\n" +
				fechadepago + "\n" +
				"Recibi de : " + estudiante + "\n" +
				"La suma de " + montopagado + "\n" + 
				"Por concepto de: " + cuerporeceipt + "\n" + "\n" +
				"Ing. Edgar Arias Mora" + "\n" +
				"Cedula 1-0902-0561" + "\n" +
				"Licencia Federada CRC-1617"); 
				
				
				/**try {
				//	print code comes here
					
				}
				
				catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // end of catch */
			}
		}); 
		btnImprimirRecibo.setBounds(149, 405, 122, 23);
		contentPane.add(btnImprimirRecibo);
		
		JButton btnMenuPrincipal = new JButton("Menu principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
				close();
				
			}
		});
		btnMenuPrincipal.setBounds(281, 405, 122, 23);
		contentPane.add(btnMenuPrincipal);
		
		nombreAtl = new JTextField();
		nombreAtl.setBounds(134, 174, 86, 20);
		contentPane.add(nombreAtl);
		nombreAtl.setColumns(10);
		
		apellido1 = new JTextField();
		apellido1.setBounds(134, 208, 86, 20);
		contentPane.add(apellido1);
		apellido1.setColumns(10);
		
		txtMens = new JTextField();
		txtMens.setBounds(134, 245, 86, 20);
		contentPane.add(txtMens);
		txtMens.setColumns(10);
		
		JLabel lblConcepto = new JLabel("Concepto");
		lblConcepto.setBounds(30, 353, 102, 14);
		contentPane.add(lblConcepto);
		
		txtFechadia = new JTextField();
		txtFechadia.setText("dd");
		txtFechadia.setBounds(134, 283, 28, 20);
		contentPane.add(txtFechadia);
		txtFechadia.setColumns(10);
		
		txtMontoPago = new JTextField();
		txtMontoPago.setBounds(134, 318, 86, 20);
		contentPane.add(txtMontoPago);
		txtMontoPago.setColumns(10);
		
		txtConceptoPago = new JTextField();
		txtConceptoPago.setBounds(134, 350, 165, 20);
		contentPane.add(txtConceptoPago);
		txtConceptoPago.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setToolTipText("");
		txtNombre.setBounds(10, 97, 148, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setText("Apellido");
		txtApellido.setBounds(179, 97, 139, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(313, 174, 28, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(256, 177, 51, 14);
		contentPane.add(lblCodigo);
		
		txtFechaMes = new JTextField();
		txtFechaMes.setText("mm");
		txtFechaMes.setBounds(168, 283, 28, 20);
		contentPane.add(txtFechaMes);
		txtFechaMes.setColumns(10);
		
		txtFechaAnno = new JTextField();
		txtFechaAnno.setText("aaaa");
		txtFechaAnno.setBounds(206, 283, 38, 20);
		contentPane.add(txtFechaAnno);
		txtFechaAnno.setColumns(10);
		
		JLabel lblFechaUltimoPago = new JLabel("Ultimo pago");
		lblFechaUltimoPago.setBounds(256, 211, 112, 14);
		contentPane.add(lblFechaUltimoPago);
		
		txtfechaPago = new JTextField();
		txtfechaPago.setBounds(375, 208, 86, 20);
		contentPane.add(txtfechaPago);
		txtfechaPago.setColumns(10);
		
		JLabel lblMonto_1 = new JLabel("Monto");
		lblMonto_1.setBounds(256, 236, 85, 14);
		contentPane.add(lblMonto_1);
		
		txtMontoUltPago = new JTextField();
		txtMontoUltPago.setBounds(375, 233, 86, 20);
		contentPane.add(txtMontoUltPago);
		txtMontoUltPago.setColumns(10);
		
		JLabel lblConcepto_1 = new JLabel("Concepto");
		lblConcepto_1.setBounds(256, 261, 88, 14);
		contentPane.add(lblConcepto_1);
		
		txtConcUltPago = new JTextField();
		txtConcUltPago.setBounds(375, 258, 86, 20);
		contentPane.add(txtConcUltPago);
		txtConcUltPago.setColumns(10);
		
		JLabel lblHistorialDePagos = new JLabel("Historial de pagos");
		lblHistorialDePagos.setFont(new Font("Impact", Font.PLAIN, 16));
		lblHistorialDePagos.setBounds(677, 204, 181, 23);
		contentPane.add(lblHistorialDePagos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(484, 235, 549, 159);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAsistencia = new JButton("Registro Asistencia");
		btnAsistencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			RegistroAsistencia ra = new RegistroAsistencia();
			ra.setVisible(true);
			close();
			}
		});
		btnAsistencia.setBounds(427, 405, 165, 23);
		contentPane.add(btnAsistencia);
		
		
		
		
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
