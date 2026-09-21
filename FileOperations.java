import java.io.*;
import java.awt.*;

public class FileOperations { 

	FileInputStream fis;
	BufferedReader br;
	public File f;
	public void readAndappendToTextArea(String selectedFile, TextArea ta) throws Exception{
		ta.setText(""); // Clear the contents of the text area if the new file is being opened.
		try{
			String line  = "";
			openFile(selectedFile);
			if(selectedFile.endsWith(".mg")){
				StringBuilder data = new StringBuilder();
				Decrypt decrypt = new Decrypt(); // for the decryption of the key.
				DataInputStream dis = new DataInputStream(fis);
				StringBuilder encLine = new StringBuilder();
				while(true){
					try{
						char currentChar = dis.readChar();
						encLine.append(currentChar);
					}catch(EOFException e){
						ta.append(decrypt.decryptData(encLine.toString()));
						break;
					}
				}
			}else{
				while((line = br.readLine()) != null){
					ta.append(line + "\n");
				}	
			}
			fis.close();
			br.close();
		}catch(IOException e){
			throw e;
		}
	}

	private void openFile(String selectedFile) throws Exception{
		try{
			f = new File(selectedFile);
			fis = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fis));
		}catch(Exception e){
			throw e;
		}
	}

	public void saveFile(String savedFileName, TextArea ta) throws Exception{
		Encrypt encrypt = new Encrypt(ta.getText());
		String encryptedData = encrypt.encryptText();
		try{
			if(f == null){
				f = new File(savedFileName);
			}
			FileOutputStream fos = new FileOutputStream(f, false);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeChars(encryptedData);
			fos.close();
			dos.close();
		}catch(Exception e){
			throw e;
		}
	}
}