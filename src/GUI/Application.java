//Huỳnh Hữu Phước
package GUI;

import javax.swing.SwingUtilities;

public class Application {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUI_KhoiDong().setVisible(true));
	}
}
