import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setTitle("SAYD - Academia Oh Do Kwan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 296);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblOhDoKwan = new JLabel(" Academia Oh Do Kwan");
		lblOhDoKwan.setForeground(Color.RED);
		lblOhDoKwan.setFont(new Font("Impact", Font.PLAIN, 20));
		lblOhDoKwan.setBounds(59, 123, 230, 22);
		contentPane.add(lblOhDoKwan);
		
		JLabel lblSayd = new JLabel("SAYD");
		lblSayd.setFont(new Font("Impact", Font.PLAIN, 45));
		lblSayd.setBounds(59, 11, 192, 66);
		contentPane.add(lblSayd);
		
		JLabel lblSistemaAdministrativoY = new JLabel("Sistema Administrativo y Deportivo");
		lblSistemaAdministrativoY.setFont(new Font("Impact", Font.PLAIN, 15));
		lblSistemaAdministrativoY.setBounds(59, 88, 237, 14);
		contentPane.add(lblSistemaAdministrativoY);
		
		JButton btnMantenimiento = new JButton("Mantenimiento");
		btnMantenimiento.addActionListener(new ActionListener() {
									
			public void actionPerformed(ActionEvent arg0) {
				
				
				//JOptionPane.showMessageDialog(null, "hello", null, JOptionPane.INFORMATION_MESSAGE);
				Mantenimiento m = new Mantenimiento();
				m.setVisible(true);
				close();
				
			}
				
			}
			
			
		);
		btnMantenimiento.setBounds(61, 179, 144, 23);
		contentPane.add(btnMantenimiento);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EstadisticaAlumno ea = new EstadisticaAlumno();
				ea.setVisible(true);
				close();
				
				}
				
		
		});
		btnReportes.setBounds(229, 179, 164, 23);
		contentPane.add(btnReportes);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/ODKLogo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(316, 0, 192, 174);
		contentPane.add(lblNewLabel);
	}
	
	public void close(){

		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

		}
	
}
