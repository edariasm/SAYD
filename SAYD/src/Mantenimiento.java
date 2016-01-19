import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Mantenimiento extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mantenimiento frame = new Mantenimiento();
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
	public Mantenimiento() {
		setTitle("SAYD - Academia Oh Do Kwan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
	
		
		JLabel lblMantenimiento = new JLabel("MANTENIMIENTO");
		lblMantenimiento.setFont(new Font("Impact", Font.PLAIN, 30));
		lblMantenimiento.setBounds(168, 23, 206, 38);
		contentPane.add(lblMantenimiento);
		
		JButton btnCobros = new JButton("Cobros");
		btnCobros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				Cobros c = new Cobros();
				c.setVisible(true);
				close();
				
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCobros.setBounds(31, 108, 130, 23);
		contentPane.add(btnCobros);
		
		JButton btnNuevoAlumno = new JButton("Nuevo Alumno");
		btnNuevoAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarAlumno agregar = new AgregarAlumno();
				agregar.setVisible(true);
				close();
				
			}
		});
		btnNuevoAlumno.setBounds(31, 189, 130, 23);
		contentPane.add(btnNuevoAlumno);
		
		JButton btnMediciones = new JButton("Mediciones");
		btnMediciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReporteAlumnos ra = new ReporteAlumnos();
				ra.setVisible(true);
				close();
			}
		});
		btnMediciones.setBounds(375, 108, 145, 23);
		contentPane.add(btnMediciones);
		
		JButton btnModificarAlumno = new JButton("Modificar Alumno");
		btnModificarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modificarAlumno MAl = new modificarAlumno();
				MAl.setVisible(true);
				close();
			}
		});
		btnModificarAlumno.setBounds(201, 189, 130, 23);
		contentPane.add(btnModificarAlumno);
		
		JButton btnAsistencia = new JButton("Asistencia");
		btnAsistencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistroAsistencia ra = new RegistroAsistencia();
				ra.setVisible(true);
				close();
				
			}
		});
		btnAsistencia.setBounds(201, 108, 130, 23);
		contentPane.add(btnAsistencia);
		
		JButton btnRegresar = new JButton("Menu Principal");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
				close();
			}
		});
		btnRegresar.setBounds(375, 189, 145, 23);
		contentPane.add(btnRegresar);
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}

}
