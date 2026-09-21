import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Notepad extends Frame implements ActionListener{
	TextArea ta;
	FileOperations fileOps = new FileOperations();
	HashMap<String, Boolean> extensions;
	Notepad(){
		super("Notepad");
		setSize(500, 500);
		setVisible(true);

		ta = new TextArea();
		add(ta);
		ta.requestFocus();
		MenuBar mbar = new MenuBar();
		setMenuBar(mbar);

		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");
		mbar.add(fileMenu);
		mbar.add(editMenu);
		mbar.add(helpMenu);
		
		// File Menu  
		MenuItem new1 = new MenuItem("New");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem exit = new MenuItem("Exit");
		MenuItem dash = new MenuItem("-");
			
		// EditMenu objects
		MenuItem cut = new MenuItem("Cut");
		MenuItem copy = new MenuItem("Copy");
		MenuItem paste = new MenuItem("Paste");
		MenuItem find = new MenuItem("Find");
		MenuItem replace = new MenuItem("Replace");
		
		// HelpMenu object
		MenuItem about = new MenuItem("About");

		fileMenu.add(new1);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(dash);
		fileMenu.add(exit);
		
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(find);
		editMenu.add(replace);

		helpMenu.add(about);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});

		new1.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		find.addActionListener(this);
		replace.addActionListener(this);
		
		ta.addKeyListener(new KeyAdapter(){
			// I think there is no reason to make custom checking
			// of the copy and paste events.
			// as they are handled by the text area.
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_N && e.isControlDown()){
					newWindow();
				}else if(e.getKeyCode() == KeyEvent.VK_O && e.isControlDown()){
					open();
				}else if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()){
					save();
				}else if(e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()){
					invokeFindDialog();
				}else if(e.getKeyCode() == KeyEvent.VK_H && e.isControlDown()){
					invokeReplaceDialog();
				}else if(e.getKeyCode() == KeyEvent.VK_W && e.isControlDown()){
					exit();
				}
			}
		});
		extensions = new HashMap<String, Boolean>();
		initExtensionMap();
	}

	private void initExtensionMap(){
		extensions.put("txt", true);
        extensions.put("mg", true);
        extensions.put("java", true);
        extensions.put("c", true);
        extensions.put("cpp", true);
        extensions.put("py", true);
        extensions.put("php", true);
        extensions.put("js", true);
        extensions.put("html", true);
        extensions.put("css", true);
        extensions.put("xml", true);
        extensions.put("log", true);
	}

	StringBuilder cutOrCopyText = new StringBuilder();
	static final String path = "cd E:\\Java\\Advance Java\\Homeworks\\Notepad && java Notepad";
	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("New")){
			newWindow();
		}else if(ae.getActionCommand().equals("Open")){
			open();	
		}else if(ae.getActionCommand().equals("Save")){
			save();	
		}else if(ae.getActionCommand().equals("Cut")){
			cut();		
		}else if(ae.getActionCommand().equals("Copy")){
			copy();
		}else if(ae.getActionCommand().equals("Paste")){
			paste();
		}else if(ae.getActionCommand().equals("Find")){
			invokeFindDialog(); 
		}else if(ae.getActionCommand().equals("Replace")){
			invokeReplaceDialog(); 
		}
	}

	private void newWindow(){
		try{
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c", path);
			builder.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void open(){
		FileDialog fd = new FileDialog(this, "Open a file", FileDialog.LOAD);
		fd.setMultipleMode(false);
		fd.setVisible(true);
		try{
			if(fd.getFile() != null){
				String fileExtension = fd.getFile().substring(fd.getFile().lastIndexOf(".") + 1);
				if(extensions.containsKey(fileExtension)){
					fileOps.readAndappendToTextArea(fd.getDirectory() + fd.getFile(), ta);
					setTitle(fd.getFile() + " - Notepad");
					ta.setCaretPosition(0);	
				}else{
					new AlertDialogBox(this, "Error", "Cannot open " + fd.getFile()).setVisible(true);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void save(){
		if(fileOps.f != null){
			try{
				fileOps.saveFile(fileOps.f.getPath(), ta);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			FileDialog fd = new FileDialog(this, "Save file", FileDialog.SAVE);
			fd.setFile("Untitled.mg");
			fd.setVisible(true);
			if(fd.getFile() != null){
				try{
					fileOps.saveFile(fd.getDirectory() + fd.getFile(), ta);
					setTitle(fd.getFile() + " - Notepad");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	private void exit(){
		System.exit(0);
	}

	private void cut(){
		if(!ta.getSelectedText().equals("")){
			cutOrCopyText.setLength(0);
			cutOrCopyText.insert(0, ta.getSelectedText());
			ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
		}
	}

	private void copy(){
		if(!ta.getSelectedText().equals("")){
			cutOrCopyText.setLength(0);
			cutOrCopyText.insert(0, ta.getSelectedText());
		}
	}

	private void paste(){
		if(cutOrCopyText.length() != 0){
			if(!ta.getSelectedText().equals("")){
				ta.replaceRange(cutOrCopyText.toString(), ta.getSelectionStart(), ta.getSelectionEnd());
			}else{
				ta.insert(cutOrCopyText.toString(), ta.getCaretPosition());
			}
		}
	}


	FindDialogBox findDialog = new FindDialogBox(this, "Find Next", "Find what: ");
	ReplaceDialogBox replaceDialog = new ReplaceDialogBox(this, "Replace");
	
	private void invokeFindDialog(){
		if(replaceDialog.isVisible()){
			replaceDialog.dispose();
		}
		if(!ta.getText().equals("")){ // making sure that the Dialog gets invoked only if the text is present.
			if(!findDialog.isVisible()){
				findDialog.setVisible(true);
			}
		}
	}

	private void invokeReplaceDialog(){
		if(findDialog.isVisible()){
			findDialog.dispose();
		}
		if(!ta.getText().equals("")){ // making sure that the Dialog gets invoked only if the text is present.
			if(!replaceDialog.isVisible()){
				replaceDialog.setVisible(true);
			}
		}
	}

	int prevIndex = -1;
	protected int findText(String textToFind){
		String text = ta.getText().replaceAll("\n", "");
		if(!text.equals("")){ // ensuring if the text is present, as the Dialog is modeless, user can change the text. 
			int index = -1;
			if(prevIndex != -1){
				index = searchForText(text, textToFind, prevIndex + 1);
			}
			if(index == -1){
				index = searchForText(text, textToFind); 
			}
			if(index != -1){
				ta.select(index, index + textToFind.length());
				ta.requestFocus();
				prevIndex = index;
				return index;
			}else{
				new AlertDialogBox(this, "Error", "The given text was not found!").setVisible(true);
			}
		}
		return -1;
	}

	// Purely done to reset the variable of the prevIndex. This method is called by the FindDialogBox class, 
	// once the Dialog box has been disposed off. 
	protected void resetVariable(){
		prevIndex = -1;
	}

	// protected void replaceText(String textToFind, String replaceText){
	// 	if(!ta.getText().equals("")){
	// 		String text = ta.getText().replaceAll("\n", "");
	// 		int index = searchForText(text, textToFind);
	// 		if(prevIndex != -1){
	// 			index = searchForText(text, textToFind, prevIndex + 1);
	// 		}
	// 		if(index == -1){
	// 			index = searchForText(text, textToFind); 
	// 		}
	// 		if(index != -1){
	// 			ta.replaceRange(replaceText, index, index + textToFind.length());
	// 		}else{
	// 			new AlertDialogBox(this, "Error", "The given text was not found!").setVisible(true);
	// 		}
	// 	}
	// }

	protected void replaceText(String textToFind, String replaceText, int index){
		if(!ta.getText().equals("")){
			String text = ta.getText().replaceAll("\n", "");
			if(index != -1){
				ta.replaceRange(replaceText, index, index + ta.getSelectedText().length());
			}
		}
	}

	protected void replaceAllText(String findText, String replaceText){
		if(!ta.getText().equals("")){
			String text = ta.getText();
			int index = searchForText(text, findText);
			if(index != -1){
				if(findText.equals(".") || findText.equals("*") || findText.equals("+")){
					ta.setText(text.replaceAll("\\" + findText, replaceText));
				}else{
					ta.setText(text.replaceAll(findText, replaceText));
				}
			}else{
				new AlertDialogBox(this, "Error", "The given text was not found!").setVisible(true);
			}
		}
	}

	private int searchForText(String haystack, String needle){
		return haystack.indexOf(needle);
	}

	private int searchForText(String haystack, String needle, int index){
		return haystack.indexOf(needle, index);
	}

	public static void main(String[] args) {
		new Notepad();
	}
}