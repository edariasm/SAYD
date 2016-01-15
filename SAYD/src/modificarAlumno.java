import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;



public class modificarAlumno extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscaNombre;
	private JTextField txtBuscaApellido;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtApellido_2;
	private JTextField txtFechaNac;
	private JTextField txtFechaIng;
	private JTextField txtEncargado;
	private JTextField txtFechaPago;
	private JTextField txtMensualidad;
	private JTextField txtEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modificarAlumno frame = new modificarAlumno();
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
	private JLabel lblFechaDeNacimiento;
	private JLabel lblFechaDeIngreso;
	private JLabel lblFechaDePago;
	private JLabel lblNombre_1;
	private JLabel lblApellido;
	private JLabel lblApellido_1;
	private JLabel lblEncargado;
	private JLabel lblMensualidad;
	private JLabel lblEstado;
	private JButton btnEditar;
	private JButton btnNewButton;
	
	
	public modificarAlumno() {
		
		conn = conectionTest.conectaraDB();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModificarAlumno = new JLabel("Modificar Alumno");
		lblModificarAlumno.setFont(new Font("Impact", Font.PLAIN, 15));
		lblModificarAlumno.setBounds(10, 11, 143, 34);
		contentPane.add(lblModificarAlumno);
		
		txtBuscaNombre = new JTextField();
		txtBuscaNombre.setBounds(94, 56, 86, 20);
		contentPane.add(txtBuscaNombre);
		txtBuscaNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 59, 64, 14);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Apellido");
		lblNewLabel.setBounds(213, 59, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtBuscaApellido = new JTextField();
		txtBuscaApellido.setBounds(279, 56, 86, 20);
		contentPane.add(txtBuscaApellido);
		txtBuscaApellido.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nombreAlumno = txtBuscaNombre.getText();
				String apellidoAlumno = txtBuscaApellido.getText();
				String queryalumno = "Select * from Alumnos where Nombre = '" + nombreAlumno + "'" + "and [Apellido 1] = '"+ apellidoAlumno+"'";
				
				txtBuscaNombre.setEditable(false);
				txtBuscaApellido.setEditable(false);
				
				
				try {
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs = stmt.executeQuery(queryalumno); // esta linea crea un result set con el query que definimos en la variable query
					System.out.println(rs);
					
					while (rs.next()) {
				        String Nombre = rs.getString("Nombre"); // crea el string nombre y le asigna el valor del result set que tiene la columna nombre
				        String Apellido1 = rs.getString("Apellido 1");
				        String Apellido2 = rs.getString("Apellido 2");
				        String Mensualidad = rs.getString("Mensualidad");
				        String fechanacimiento =  rs.getString("FechaNac");
				        String fechaingreso =  rs.getString("FechaIng");
				        String fechapago =  rs.getString("FechaPago");
				        String encargado =  rs.getString("Encargado");
				        String mensualidad =  rs.getString("Mensualidad");
				        String tipo =  rs.getString("Tipo");
				        String idAlumnoact = rs.getString("id_alumno");
				        
				        System.out.println(Nombre + " " + Apellido1 + " " + "Mensualidad: " + " " + Mensualidad);
				        
				        txtNombre.setText(Nombre);
				        txtApellido.setText(Apellido1);
				        txtApellido_2.setText(Apellido2);
				        txtFechaNac.setText(fechanacimiento);
				        txtFechaIng.setText(fechaingreso);
				        txtFechaPago.setText(fechapago);
				        txtEncargado.setText(encargado);
				        txtMensualidad.setText(mensualidad);
				        txtEstado.setText(tipo);
				        		        
				        txtNombre.setEditable(false);
				        txtApellido.setEditable(false);
				        txtApellido_2.setEditable(false);
				        txtFechaNac.setEditable(false);
				        txtFechaIng.setEditable(false);
				        txtFechaPago.setEditable(false);
				        txtEncargado.setEditable(false);
				        txtMensualidad.setEditable(false);
				        txtEstado.setEditable(false);
				         
				         
				    }
					
					
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // End of catch
				
				
				
				
			}
		});
		btnBuscar.setBounds(406, 55, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblModificarDatosDel = new JLabel("Datos del Alumno");
		lblModificarDatosDel.setFont(new Font("Impact", Font.PLAIN, 15));
		lblModificarDatosDel.setBounds(10, 117, 383, 34);
		contentPane.add(lblModificarDatosDel);
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setBounds(10, 178, 122, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setText("Apellido 1");
		txtApellido.setBounds(173, 178, 122, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtApellido_2 = new JTextField();
		txtApellido_2.setText("Apellido 2");
		txtApellido_2.setBounds(343, 178, 133, 20);
		contentPane.add(txtApellido_2);
		txtApellido_2.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setText("Fecha Nac aaaa-mm-dd");
		txtFechaNac.setBounds(10, 239, 122, 20);
		contentPane.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		txtFechaIng = new JTextField();
		txtFechaIng.setText("Fecha ingreso");
		txtFechaIng.setBounds(173, 239, 122, 20);
		contentPane.add(txtFechaIng);
		txtFechaIng.setColumns(10);
		
		txtEncargado = new JTextField();
		txtEncargado.setText("Encargado");
		txtEncargado.setBounds(10, 311, 130, 20);
		contentPane.add(txtEncargado);
		txtEncargado.setColumns(10);
		
		txtFechaPago = new JTextField();
		txtFechaPago.setText("Fecha de pago");
		txtFechaPago.setBounds(343, 239, 133, 20);
		contentPane.add(txtFechaPago);
		txtFechaPago.setColumns(10);
		
		txtMensualidad = new JTextField();
		txtMensualidad.setText("Mensualidad");
		txtMensualidad.setBounds(173, 311, 122, 20);
		contentPane.add(txtMensualidad);
		txtMensualidad.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setText("Estado (Activo-Inactivo-Becado)");
		txtEstado.setBounds(343, 311, 181, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar datos");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 	txtNombre.setEditable(false);
			        txtApellido.setEditable(false);
			        txtApellido_2.setEditable(false);
			        txtFechaNac.setEditable(false);
			        txtFechaIng.setEditable(false);
			        txtFechaPago.setEditable(false);
			        txtEncargado.setEditable(false);
			        txtMensualidad.setEditable(false);
			        txtEstado.setEditable(false);
			        
			        String NombreAct = txtNombre.getText(); 
			        String Apellido1Act = txtApellido.getText();
			        String Apellido2Act = txtApellido_2.getText();
			        String MensualidadAct = txtMensualidad.getText();
			        String fechanacimientoAct =  txtFechaNac.getText();
			        String fechaingresoAct =  txtFechaIng.getText();
			        String fechapagoAct = txtFechaPago.getText();
			        String encargadoAct =  txtEncargado.getText();
			        String tipoAct =  txtEstado.getText();
			        
			        System.out.println(encargadoAct);
			        
			        String updateAlumno = "update Alumnos"
			        		+" set Nombre ='" + NombreAct+ "', [Apellido 1] = '" + Apellido1Act + "', [Apellido 2] = '" + Apellido2Act + "'"
			        		+", Mensualidad = '"+ MensualidadAct + "', FechaNac = '" + fechanacimientoAct +"', FechaIng = '" + fechaingresoAct + "'"
			        		+", FechaPago = '" + fechapagoAct + "', Encargado = '" + encargadoAct+ "', Tipo = '" + tipoAct + "'"
			        		+"where Nombre = '" + NombreAct +"' and [Apellido 1] = '" + Apellido1Act+ "'";
			        
			        
			        try {
						Statement stmtUPDATE = conn.createStatement(); // crea un statement
						stmtUPDATE.executeUpdate(updateAlumno); // ejecuta el update 
						JOptionPane.showMessageDialog(null, "Se han actualizado los datos");
						
						
					} catch (Exception i) {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
						JOptionPane.showMessageDialog(null, updateAlumno);
					}
			        
			        
			        
			        
				
			}
		});
		btnActualizar.setBounds(574, 341, 122, 23);
		contentPane.add(btnActualizar);
		
		lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 214, 110, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		lblFechaDeIngreso = new JLabel("Fecha de ingreso");
		lblFechaDeIngreso.setBounds(173, 214, 122, 14);
		contentPane.add(lblFechaDeIngreso);
		
		lblFechaDePago = new JLabel("Fecha de pago");
		lblFechaDePago.setBounds(343, 214, 133, 14);
		contentPane.add(lblFechaDePago);
		
		lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(10, 153, 122, 14);
		contentPane.add(lblNombre_1);
		
		lblApellido = new JLabel("Apellido 1");
		lblApellido.setBounds(173, 153, 122, 14);
		contentPane.add(lblApellido);
		
		lblApellido_1 = new JLabel("Apellido 2");
		lblApellido_1.setBounds(343, 153, 130, 14);
		contentPane.add(lblApellido_1);
		
		lblEncargado = new JLabel("Encargado");
		lblEncargado.setBounds(10, 286, 122, 14);
		contentPane.add(lblEncargado);
		
		lblMensualidad = new JLabel("Mensualidad");
		lblMensualidad.setBounds(173, 286, 122, 14);
		contentPane.add(lblMensualidad);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(343, 286, 46, 14);
		contentPane.add(lblEstado);
		
		btnEditar = new JButton("Editar datos");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				    txtNombre.setEditable(true);
			        txtApellido.setEditable(true);
			        txtApellido_2.setEditable(true);
			        txtFechaNac.setEditable(true);
			        txtFechaIng.setEditable(true);
			        txtFechaPago.setEditable(true);
			        txtEncargado.setEditable(true);
			        txtMensualidad.setEditable(true);
			        txtEstado.setEditable(true);
				
			}
		});
		btnEditar.setBounds(574, 149, 122, 23);
		contentPane.add(btnEditar);
		
		btnNewButton = new JButton("Menu Principal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
				close();
				
			}
		});
		btnNewButton.setBounds(550, 418, 146, 23);
		contentPane.add(btnNewButton);
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
	
}
