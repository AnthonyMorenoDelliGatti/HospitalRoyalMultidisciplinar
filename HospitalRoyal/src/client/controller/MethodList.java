package client.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import client.ftp.view.FTPWindow;
import client.ftp.view.VistaArchivos;
import client.model.ArchivoFtp;



public class MethodList {
	
	public MethodList() {

	}
	public void cargarDatosLista(FTPClient client, FTPWindow view, VistaArchivos explorer) {
		try {
			ArrayList<ArchivoFtp> archivos = new ArrayList<>();
			FTPFile[] fileList = client.listFiles();
			if(fileList.length <= 0) {
				archivos.add(new ArchivoFtp("This folder is empty", "", 0, ""));
			}
			if(fileList.length > 0) {
			for (int i = 0; i < fileList.length; i++) {
				String nameFile = fileList[i].getName();
				int isDirectory = 0;
				if (fileList[i].isDirectory()) {
					isDirectory = 1;
				}
				String path = client.printWorkingDirectory();
				if(!path.equals("/")) {
					path=path+"/";
				}
				String time = client.getModificationTime(path + nameFile);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
				String lastModification = "";
				try {
					String timePart = time.split(" ")[0];
					Date modificationTime = dateFormat.parse(timePart);
					Calendar calendarModification = Calendar.getInstance(TimeZone.getDefault());
					calendarModification.setTime(modificationTime);
					lastModification = "" + calendarModification.get(Calendar.DAY_OF_MONTH) + "/"
							+ (calendarModification.get(Calendar.MONTH)+1) + "/" + calendarModification.get(Calendar.YEAR)
							+ " " + (calendarModification.get(Calendar.HOUR)+1) + ":"
							+ calendarModification.get(Calendar.MINUTE) + ":"
							+ calendarModification.get(Calendar.SECOND);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				archivos.add(new ArchivoFtp(nameFile, lastModification, isDirectory, (path + nameFile)));
				view.getCentro().removeAll();
				view.addExplorer(explorer.visualizarListado(archivos));
				//view.pack();
			}
			}else {
				view.getCentro().removeAll();
				view.addExplorer(explorer.visualizarListado(archivos));
				//view.pack();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}