package client.ftp.listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ListenerCloseWindow implements ActionListener{

	private JFrame frame;
	
	public ListenerCloseWindow(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		System.exit(0);
	}
}
