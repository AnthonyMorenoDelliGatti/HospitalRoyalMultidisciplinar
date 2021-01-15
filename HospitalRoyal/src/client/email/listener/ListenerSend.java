package client.email.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import client.email.view.NewEmailView;

import client.model.Archive;

import client.menu.view.SplashEmail;



public class ListenerSend implements ActionListener{
	String mail;
	String password;
	NewEmailView view;
	ArrayList<Archive> archives;
	public ListenerSend(String mail, String password, NewEmailView view, ArrayList<Archive> archives) {
		this.mail = mail;
		this.password = password;
		this.view = view;
		this.archives = archives;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SplashEmail splash = new SplashEmail();
		splash.toFront();
		SendThread thread = new SendThread(mail, password, view, splash);
		thread.start();
	}

}
