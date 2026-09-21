public class Decrypt implements Constants{
    private int decryptKey(String encryptedLine){
        StringBuilder binary = new StringBuilder();
        int limit = Math.min(7, encryptedLine.length());

        for(int i = 0; i < limit; i++){
            if(encryptedLine.charAt(i) == Constants.PLACEHOLDER_CHAR){
                for (int j = i + 1; j < encryptedLine.length(); j++){
                    String currentCharBinary = Integer.toBinaryString(encryptedLine.charAt(j));
                    binary.append(currentCharBinary.length() > 8 ? 1 : 0);
                }
                break;
            }
            String currentCharBinary = Integer.toBinaryString(encryptedLine.charAt(i));
            binary.append(currentCharBinary.length() > 8 ? 1 : 0);
        }

        if(binary.length() > 7){
            binary.delete(7, binary.length());
        }

        return Integer.parseInt(binary.toString(), 2);
    }

    public String decryptData(String encryptedLine){
        int key = decryptKey(encryptedLine);
        int placeHolderCharIndex = encryptedLine.indexOf(Constants.PLACEHOLDER_CHAR);

        if(placeHolderCharIndex != -1){
            encryptedLine = encryptedLine.substring(0, placeHolderCharIndex);
        }

        StringBuilder decryptedData = new StringBuilder();

        for(int i = 0; i < encryptedLine.length(); i++){
            String binary = Integer.toBinaryString(encryptedLine.charAt(i) - key);
            if (binary.length() == 15) {
                int character = Integer.parseInt(binary.substring(7), 2);
                decryptedData.append((char) character);
            }else{
                decryptedData.append((char) (encryptedLine.charAt(i) - key));
            }
        }

        return decryptedData.toString();
    }
}