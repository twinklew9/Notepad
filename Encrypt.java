public class Encrypt{
	private StringBuilder text;
	private int key;
	Encrypt(String text){
		this.text = new StringBuilder(text);
		key = (int)Math.floor(Math.random() * 121) + 1;
	}

	public String encryptText(){
		String keyInBinary = convertKeyToBinary();
		StringBuilder embeddedData = embedData(keyInBinary);
		StringBuilder mergedString;
		if(text.length() >= 7){
			mergedString = new StringBuilder(embeddedData.toString() + text.substring(embeddedData.length()));
		}else{
			mergedString = new StringBuilder(embeddedData.toString() + text);
		}
		StringBuilder encryptedText = new StringBuilder();
		for(int i = 0; i < mergedString.length(); i++){
			if(mergedString.charAt(i) != Constants.PLACEHOLDER_CHAR){
				encryptedText.append((char)((mergedString.charAt(i) + key)));
			}else{
				encryptedText.append((char)((mergedString.charAt(i))));
			}
		}
		return encryptedText.toString();
	}

	private StringBuilder embedData(String keyInBinary){
		StringBuilder output = new StringBuilder();
		int limit = Math.min(7, text.length());

		int i = 0;
		for(i = 0; i < limit; i++){
			String currentCharInBinary = padTo15Bits(Integer.toBinaryString(text.charAt(i)));
            output.append((char) Integer.parseInt(replaceFirstBit(currentCharInBinary, keyInBinary.charAt(i)), 2));
		}
		if(limit < 7){
			output.append(Constants.PLACEHOLDER_CHAR);
			for(; i < keyInBinary.length(); i++){
				String currentCharInBinary = padTo15Bits(Integer.toBinaryString(Constants.DUMMY_CHAR));
				output.append((char) Integer.parseInt(replaceFirstBit(currentCharInBinary, keyInBinary.charAt(i)), 2));
			}
		}
		return output;
	}

	private String convertKeyToBinary(){
		String keyInBinary = Integer.toBinaryString(key);
		while(keyInBinary.length() < 7){
			keyInBinary = 0 + keyInBinary;
		}
		return keyInBinary;
	}

	private String padTo15Bits(String binaryString){
        while (binaryString.length() < 15) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }

	private String replaceFirstBit(String binaryString, char newBit){
        return newBit + binaryString.substring(1);
    }
}
