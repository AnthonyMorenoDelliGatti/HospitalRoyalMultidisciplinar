package client.menu.listener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import client.controller.Client;
import client.controller.Methods;
import client.ftp.listener.ListenerClose;
import client.ftp.listener.ListenerCreateFolder;
import client.ftp.listener.ListenerReturn;
import client.ftp.listener.ListenerReturnForward;
import client.ftp.listener.ListenerSubir;
import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.menu.view.StartMenuView;
import client.model.ArchivoFtp;
import client.model.Paths;

public class ListenerUserFTP implements ActionListener {

	private Paths paths;
	private FTPClient client;
	private FTPWindow ftpWindow;
	private String user;
	private VistaArchivos explorer;
	private Methods method;
	private StartMenuView vStartMenu;
	private String password;
	private DataOutputStream outputStream;

	public ListenerUserFTP(Paths paths, FTPClient client, FTPWindow ftpWindow, String user, VistaArchivos explorer,
			Methods method, StartMenuView vStartMenu, String password, DataOutputStream outputStream) {
		this.paths = paths;
		this.client = client;
		this.ftpWindow = ftpWindow;
		this.user = user;
		this.explorer = explorer;
		this.method = method;
		this.vStartMenu = vStartMenu;
		this.password = password;
		this.outputStream = outputStream;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		exists(client);
		try {
			client.changeWorkingDirectory(user);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			paths.setPathLimit(client.printWorkingDirectory());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ArrayList<ArchivoFtp> archivos = new ArrayList<>();
		ftpWindow = new FTPWindow(client, user, explorer, method, vStartMenu);
		explorer = new VistaArchivos(client, archivos, method, ftpWindow, password, outputStream, paths, false);
		method.cargarDatosLista(client, ftpWindow, explorer);
		ftpWindow.setVisible(true);
		ftpWindow.setLocationRelativeTo(null);

		// se introducen los listener a los botones
		ftpWindow.getButtons().get(0).addActionListener(
				new ListenerReturn(client,method,ftpWindow,explorer,paths));
		ftpWindow.getButtons().get(1).addActionListener(
				new ListenerReturnForward(client,method,ftpWindow,explorer,paths));
		ftpWindow.getButtons().get(2).setVisible(false);
		ftpWindow.getButtons().get(3)
				.addActionListener(new ListenerSubir(client, user, ftpWindow, explorer, method, outputStream));
		vStartMenu.setVisible(false);

		ftpWindow.getButtons().get(4).addActionListener(new ListenerClose(ftpWindow, vStartMenu));
		

	}

	public void exists(FTPClient client) {
		boolean hasDirectory = false;
		FTPFile[] files;
		try {
			files = client.listFiles();
			for (int i = 0; i < files.length; i++) {
				int type = files[i].getType();
				if (type == 1) {
					if (files[i].getName().equals(user)) {
						hasDirectory = true;
					}
				}
			}
			if (!hasDirectory) {
				client.makeDirectory("/" + user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}