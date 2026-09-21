import java.awt.*;
import java.awt.event.*;

class ReplaceDialogBox extends Dialog implements ActionListener {
    private Notepad ref;
    private TextField findTextField;
    private TextField replaceTextField;
    int foundIndex = -1;
    
    ReplaceDialogBox(Frame parent, String title) {
        super(parent, title, false);
        this.ref = (Notepad)parent;
        setSize(400, 350);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 2, 0, 2);

        add(new Label("Find what:"), gbc);
        gbc.gridy++;
        findTextField = new TextField(20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(findTextField, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        add(new Label("Replace with:"), gbc);
        gbc.gridy++;
        replaceTextField = new TextField(20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(replaceTextField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 4;
        
        gbc.insets = new Insets(3, 2, 3, 2);        
        Button findButton = new Button("Find Next");
        findButton.addActionListener(this);
        add(findButton, gbc);

        gbc.gridy++;

        Button replaceButton = new Button("Replace");
        replaceButton.addActionListener(this);
        add(replaceButton, gbc);

        gbc.gridy++;
        Button replaceAllButton = new Button("Replace All");
        replaceAllButton.addActionListener(this);
        add(replaceAllButton, gbc);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                ref.resetVariable();
                // foundIndex = -1;
            }
        });
    }

    
    public void actionPerformed(ActionEvent ae) {
    	if(ae.getActionCommand().equals("Find Next")){
            if(findTextField.getText().equals("")){
                new AlertDialogBox(ref, "Error", "Find field cannot be empty!").setVisible(true);
            }else{
                foundIndex = ref.findText(findTextField.getText());
            }
        }else if(ae.getActionCommand().equals("Replace")){
    		if(findTextField.getText().equals("")){
    			new AlertDialogBox(ref, "Error", "Find field cannot be empty!").setVisible(true);
    		}else{
    			ref.replaceText(findTextField.getText(), replaceTextField.getText(), foundIndex);
    		}
            foundIndex = ref.findText(findTextField.getText());
    	}else if(ae.getActionCommand().equals("Replace All")){
            if(findTextField.getText().equals("")){
                new AlertDialogBox(ref, "Error", "Find field cannot be empty!").setVisible(true);
            }else{
                ref.replaceAllText(findTextField.getText(), replaceTextField.getText());
            }
        }
    }
}
