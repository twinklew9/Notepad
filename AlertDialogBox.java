import java.awt.*;
import java.awt.event.*;

class AlertDialogBox extends Dialog{
	TextField textField;
	AlertDialogBox(Frame parent, String title, String labelText){
		super(parent, title, true);
		setSize(250, 250);
		setLayout(new FlowLayout());
		setResizable(false);
		add(new Label(labelText));

		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
        Toolkit.getDefaultToolkit().beep();
	}
}	