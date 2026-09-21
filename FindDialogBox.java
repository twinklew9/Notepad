import java.awt.*;
import java.awt.event.*;

class FindDialogBox extends Dialog implements ActionListener{
	Notepad ref;
	TextField textField;
	FindDialogBox(Frame parent, String title, String labelText){
		super(parent, title, false);
		setSize(400, 250);
		setLayout(new FlowLayout());
		add(new Label(labelText));
		textField = new TextField(20);
		add(textField);
		Button b = new Button(title);
		add(b);
		b.addActionListener(this);
		this.ref = (Notepad)parent;
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                ref.resetVariable();
            }
        });
	}

	public void actionPerformed(ActionEvent ae){
		if(this.getTitle().equals("Find Next")){
			if(textField.getText().equals("")){
				new AlertDialogBox(ref, "Error", "Find field cannot be empty!").setVisible(true);
			}else{
				int index = ref.findText(textField.getText());
			}
		}
	}
}	