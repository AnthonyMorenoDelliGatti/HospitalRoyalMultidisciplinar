package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class VistaPrincipal extends JFrame{

	private JPanel rootPanel;
	private JPanel cabecera;
	private JPanel centro;
	private Color colorCabecera;
	
	public VistaPrincipal() {
		rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));	
		
		cabecera = new JPanel();
		cabecera.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		colorCabecera = new Color(255, 194, 121);
		cabecera.setBackground(colorCabecera);
		
		centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.X_AXIS));	
		
		rootPanel.add(cabecera);
		rootPanel.add(centro);
		setContentPane(rootPanel);
		
		generarOpciones();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void generarOpciones() {
		generarBotonCabecera("..\\iconos\\atras.png");
		generarBotonCabecera("..\\iconos\\flecha-correcta.png");
		generarBotonCabecera("..\\iconos\\folder.png");
		generarBotonCabecera("..\\iconos\\upload-file.png");
	}

	private void generarBotonCabecera(String direccion) {
		JButton boton = new JButton();
		Icon icon = new ImageIcon(direccion);
		boton.setIcon(icon);
		boton.setBackground(colorCabecera);
		boton.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		boton.setBorder(emptyBorder);
		cabecera.add(boton);
	}
	
	public void agregarExplorador(JPanel jPanel) {
		centro.add(jPanel);
	}
	
	public void actualizarExplorador(JPanel explorer) {
		centro.remove(1); // borra el anterior explorador
		agregarExplorador(explorer);
	}

}
