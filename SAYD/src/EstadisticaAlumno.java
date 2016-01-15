import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import javax.swing.JOptionPane;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.html.AccessibleHTML.TableElementInfo.TableAccessibleContext;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Component;

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




public class EstadisticaAlumno extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadisticaAlumno frame = new EstadisticaAlumno();
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
	private JButton btnMenuPrincipal;
	private JTextField txtTotalMens;
	public EstadisticaAlumno() {
		
		conn = conectionTest.conectaraDB();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 879, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblOhDoKwan = new JLabel("Oh Do Kwan - Estadisticas de cobro");
		lblOhDoKwan.setFont(new Font("Impact", Font.PLAIN, 18));
		lblOhDoKwan.setBounds(29, 11, 265, 38);
		contentPane.add(lblOhDoKwan);
		
		JButton btnGenerar = new JButton("Generar Reporte");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String queryReporteGeneral = "Select Alumnos.Nombre"
						+",Alumnos.[Apellido 1]"
						+ ", Alumnos.Tipo"
						+ ", pagos.Fecha_pago"
						+ ", pagos.Mes"
						+ ", Concepto_pago"
						+ ", pagos.Monto_pago"
						+ " From Alumnos inner join Pagos"
						+ " on Alumnos.Id_Alumno = Pagos.ID_Alumno"
						+ " Where Monto_pago > 8000 and Monto_pago < 17000 and Fecha_pago > '2016-01-01' and Fecha_pago < GETDATE()"; 
				
			
				
						
					 String sumaMens = "Select sum(Pagos.Monto_pago) As 'sumapagos' "
						+ " From Alumnos inner join Pagos "
						+ " on Alumnos.Id_Alumno = Pagos.ID_Alumno"
						+ " Where Monto_pago > 8000 and Monto_pago < 17000 and Fecha_pago > '2016-01-01' and Fecha_pago < GETDATE()";			
				
				
				
				//JOptionPane.showMessageDialog(null, sumaMens);
						
						
				try {
					
					
					
					
					
					// crear un statement 
					Statement stmt = conn.createStatement(); 
					// crear un result set
					ResultSet rs1 = stmt.executeQuery(queryReporteGeneral); // esta linea crea un result set con el query que definimos en la variable query
					Statement stmt2 = conn.createStatement();
					
					ResultSet rs2 = stmt2.executeQuery(sumaMens);
					while (rs2.next()) {
						String totalSuma = rs2.getString("sumapagos"); 
						txtTotalMens.setText(totalSuma);
					}
				
				table.setModel(DbUtils.resultSetToTableModel(rs1)); // llena la tabla 2 con los resultados del result set. Utiliza el jar r2xml
							
			
				
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}	
				
		});
		btnGenerar.setBounds(29, 60, 181, 23);
		contentPane.add(btnGenerar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 112, 733, 341);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
				close();
			}
		});
		btnMenuPrincipal.setBounds(282, 60, 164, 23);
		contentPane.add(btnMenuPrincipal);
		
		txtTotalMens = new JTextField();
		txtTotalMens.setFont(new Font("Impact", Font.PLAIN, 11));
		txtTotalMens.setEditable(false);
		txtTotalMens.setEnabled(false);
		txtTotalMens.setBounds(594, 479, 181, 38);
		contentPane.add(txtTotalMens);
		txtTotalMens.setColumns(10);
	}
	
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
}
