import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mediciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mediciones frame = new Mediciones();
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
	public Mediciones() {
		setTitle("SAYD - Academia Oh Do Kwan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblMediciones = new JLabel("MEDICIONES");
		lblMediciones.setFont(new Font("Impact", Font.PLAIN, 30));
		lblMediciones.setBounds(168, 23, 206, 38);
		contentPane.add(lblMediciones);
		
		JButton btnBuscarAtleta = new JButton("Buscar atleta");
		btnBuscarAtleta.setBounds(30, 121, 122, 23);
		contentPane.add(btnBuscarAtleta);
		
		JList list = new JList();
		list.setBounds(168, 124, 98, 20);
		contentPane.add(list);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(40, 185, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(39, 209, 57, 14);
		contentPane.add(lblApellido);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(40, 234, 46, 14);
		contentPane.add(lblFecha);
		
		JLabel lblLadoALado = new JLabel("Lado a lado");
		lblLadoALado.setBounds(40, 266, 56, 14);
		contentPane.add(lblLadoALado);
		
		JLabel lblSeguidillas = new JLabel("Seguidillas");
		lblSeguidillas.setBounds(40, 291, 56, 14);
		contentPane.add(lblSeguidillas);
		
		JLabel lblSaltoLargo = new JLabel("Salto largo");
		lblSaltoLargo.setBounds(40, 316, 66, 14);
		contentPane.add(lblSaltoLargo);
		
		JLabel lblAbs = new JLabel("Abdominales");
		lblAbs.setBounds(40, 341, 66, 14);
		contentPane.add(lblAbs);
		
		JButton btnGuardarDatos = new JButton("Guardar datos");
		btnGuardarDatos.setBounds(30, 435, 109, 23);
		contentPane.add(btnGuardarDatos);
		
		JButton btnRegresar = new JButton("Menu principal");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
			}
		});
		btnRegresar.setBounds(343, 435, 137, 23);
		contentPane.add(btnRegresar);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(40, 366, 46, 14);
		contentPane.add(lblPeso);
		
		JLabel lblTalla = new JLabel("Talla");
		lblTalla.setBounds(40, 391, 46, 14);
		contentPane.add(lblTalla);
		
		
	}
}
