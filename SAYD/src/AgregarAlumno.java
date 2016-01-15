import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;


public class AgregarAlumno extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApe1;
	private JTextField txtApe2;
	private JTextField txtFechaNac;
	private JTextField txtMens;
	private JTextField txtFechaIng;
	private JTextField txtEnc;
	private JTextField txtFechaPago;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarAlumno frame = new AgregarAlumno();
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
	
	
	
	public AgregarAlumno() {
		
		conn = conectionTest.conectaraDB(); // establishes connection
		setTitle("Agregar nuevo alumno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblAgregarNuevoAlumno = new JLabel("Agregar nuevo alumno");
		lblAgregarNuevoAlumno.setFont(new Font("Impact", Font.PLAIN, 20));
		lblAgregarNuevoAlumno.setBounds(39, 23, 196, 28);
		contentPane.add(lblAgregarNuevoAlumno);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(26, 83, 60, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido 1");
		lblApellido.setBounds(26, 110, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblApellido_1 = new JLabel("Apellido 2");
		lblApellido_1.setBounds(26, 135, 60, 14);
		contentPane.add(lblApellido_1);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(299, 83, 101, 14);
		contentPane.add(lblFechaNacimiento);
		
		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso");
		lblFechaIngreso.setBounds(299, 110, 101, 14);
		contentPane.add(lblFechaIngreso);
		
		JLabel lblEncargado = new JLabel("Encargado");
		lblEncargado.setBounds(299, 135, 101, 14);
		contentPane.add(lblEncargado);
		
		JLabel lblMensualidad = new JLabel("Mensualidad");
		lblMensualidad.setBounds(26, 160, 92, 14);
		contentPane.add(lblMensualidad);
		
		JLabel lblFechaDePago = new JLabel("Fecha de pago");
		lblFechaDePago.setBounds(299, 160, 92, 14);
		contentPane.add(lblFechaDePago);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(108, 80, 110, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApe1 = new JTextField();
		txtApe1.setBounds(108, 107, 110, 20);
		contentPane.add(txtApe1);
		txtApe1.setColumns(10);
		
		txtApe2 = new JTextField();
		txtApe2.setBounds(108, 132, 110, 20);
		contentPane.add(txtApe2);
		txtApe2.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(410, 80, 86, 20);
		contentPane.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		txtMens = new JTextField();
		txtMens.setBounds(108, 157, 60, 20);
		contentPane.add(txtMens);
		txtMens.setColumns(10);
		
		txtFechaIng = new JTextField();
		txtFechaIng.setBounds(410, 107, 86, 20);
		contentPane.add(txtFechaIng);
		txtFechaIng.setColumns(10);
		
		txtEnc = new JTextField();
		txtEnc.setBounds(410, 132, 110, 20);
		contentPane.add(txtEnc);
		txtEnc.setColumns(10);
		
		txtFechaPago = new JTextField();
		txtFechaPago.setBounds(410, 157, 60, 20);
		contentPane.add(txtFechaPago);
		txtFechaPago.setColumns(10);
		
		JButton btnRegistrarAlumno = new JButton("Registrar alumno");
		btnRegistrarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String Nombre = txtNombre.getText();
				String Apellido1 = txtApe1.getText();
				String Apellido2 = txtApe2.getText();
				String Mensualidad = txtMens.getText();
				String fechaNac = txtFechaNac.getText();
				String fechaIng = txtFechaIng.getText();
				String encargado = txtEnc.getText();
				String fechaPago = txtFechaPago.getText();
				String regular = "Regular";
				
				String queryInsAlumno = "Insert into Alumnos(Nombre, [Apellido 1], [Apellido 2], Mensualidad, FechaNac, Tipo, FechaIng, Encargado, FechaPago) values ('"+ Nombre + "', '" + Apellido1 + "', '" + Apellido2 + "'," + Mensualidad + ", '" + fechaNac + "',"+"'"+ regular+ "',"+"'" + fechaIng + "','" + encargado + "', '" + fechaPago+ "')";
				
				
				
					
				 
				
				try {
					Statement stmtINS = conn.createStatement(); // crea un statement
					stmtINS.executeUpdate(queryInsAlumno); // ejecuta un insert a la base de datos con el query queryInsPago que toma los parametros de pantalla
					JOptionPane.showMessageDialog(null, "Alumno nuevo registrado satisfactoriamente");
					txtNombre.setText("");
					txtApe1.setText("");
					txtApe2.setText("");
					txtMens.setText("");
					txtFechaNac.setText("");
					txtFechaIng.setText("");
					txtEnc.setText("");
					txtFechaPago.setText("");
					
				} catch (Exception i) {
					JOptionPane.showMessageDialog(null, "Debe ingresar los datos del alumno");
				}
				
				
				
			}
	});
		btnRegistrarAlumno.setBounds(26, 217, 130, 20);
		contentPane.add(btnRegistrarAlumno);
		
		JButton btnNuevoRegistro = new JButton("Nuevo Registro");
		btnNuevoRegistro.setBounds(120, 277, 115, 23);
		contentPane.add(btnNuevoRegistro);
		
		JButton btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
				close();
				
			}
		});
		btnMenuPrincipal.setBounds(276, 277, 115, 23);
		contentPane.add(btnMenuPrincipal);
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
