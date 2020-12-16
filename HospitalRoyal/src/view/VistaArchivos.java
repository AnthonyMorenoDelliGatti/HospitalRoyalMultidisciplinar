package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ListenerBotonModificarNombre;
import controller.ListenerDescargar;
import controller.ListenerEliminar;
import controller.ListenerModificarNombre;
import controller.ListenerArchivo;
import modelo.Archivo;

public class VistaArchivos{

	public VistaArchivos() {}
	
	public JPanel visualizarListado(ArrayList<Archivo> archivos) {
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));	
		GridLayout experimentLayout = new GridLayout(0, 3, 5, 5);

		
		cabecera(rootPanel, experimentLayout);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));	
		generarListado(panel, experimentLayout, archivos);

		JPopupMenu menu = new JPopupMenu();
		menu.add(new JMenuItem("Crear Carpeta"));

		rootPanel.setComponentPopupMenu(menu);

		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rootPanel.add(scrollPane);
		
		return rootPanel;
	}

	private void generarListado(JPanel rootPanel, GridLayout experimentLayout, ArrayList<Archivo> archivos) {
		JPanel panel;
		Collections.sort(archivos);
		for (Archivo i : archivos) {
			panel = new JPanel();
			panel.setLayout(experimentLayout);
			
			JLabel l = obtenerIcono(i);
			panel.add(l);
			
			JTextField nombre = generarNombre(panel, i);
			
			panel.add(new JLabel(i.getUltFechaModificacion()));
			
			panel.addMouseListener(new ListenerArchivo(panel, i));

			JPopupMenu menu = generarMenu(nombre, i);

			
			panel.setComponentPopupMenu(menu);
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			rootPanel.add(panel);

		}
	}

	private JPopupMenu generarMenu(JTextField nombre, Archivo archivo) {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem item = new JMenuItem("Cambiar nombre");
		item.addActionListener(new ListenerBotonModificarNombre(nombre));
		menu.add(item);
		
		JMenuItem item2 = new JMenuItem("Descargar");
		item.addActionListener(new ListenerDescargar(archivo));
		menu.add(item2);
		
		JMenuItem item3 = new JMenuItem("Eliminar");
		item3.addActionListener(new ListenerEliminar(archivo));
		menu.add(item3);
		return menu;
	}

	private JTextField generarNombre(JPanel panel, Archivo i) {
		JTextField nombre = new JTextField(10);
		nombre.setText(i.getNombre());
		panel.add(nombre);
		nombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ListenerModificarNombre listener = new ListenerModificarNombre(i, nombre);
		nombre.addActionListener(listener);
		nombre.addFocusListener(listener);
		nombre.setEditable(false);
		
		return nombre;
	}

	private JLabel obtenerIcono(Archivo i) {
		String direcIcono;
		if(i.getIsCarpeta() == 1) {
			direcIcono = "..\\VentanaCliente\\iconos\\carpeta.png";
		}
		else {
			direcIcono  = "..\\VentanaCliente\\iconos\\text-document.png";
		}
		Icon icon = new ImageIcon(direcIcono);
		JLabel l = new JLabel(icon);
		return l;
	}

	private void cabecera(JPanel rootPanel, GridLayout experimentLayout) {
		JPanel panel = new JPanel();
		panel.setLayout(experimentLayout);
		panel.add(new JLabel(""));
		panel.add(new JLabel("Nombre"));
		panel.add(new JLabel("Fecha modificaci�n"));
		rootPanel.add(panel);
	}

}