//Huỳnh Hữu Phước
package GUI;

import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUI_Clock extends Thread {
	private JLabel lblClock;
	private JLabel lblDate;

	public GUI_Clock(JLabel lblClock, JLabel lblDate) {
		this.lblClock = lblClock;
		this.lblDate = lblDate;
	}

	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss aa");
		SimpleDateFormat Ddf = new SimpleDateFormat("dd/MM/yyyy");
		while (true) {
			Date now = new Date();
			String st = sdf.format(now);
			String d = Ddf.format(now);
			lblClock.setText(st);
			lblDate.setText(d);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				// TODO: handle exception
			}
		}
	}
	
}
