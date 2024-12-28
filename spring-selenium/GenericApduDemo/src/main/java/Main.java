public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // AKİS kart handler oluştur
            IApduHandler akisHandler = new AkisCardHandler();

            // Örnek bir APDU komutu
            byte[] apduCommand = new byte[] { (byte) 0x00, (byte) 0xA4, (byte) 0x04, (byte) 0x00 };

            // APDU komutunu gönder ve yanıtı al
            byte[] response = akisHandler.sendApdu(apduCommand);

            System.out.println("Full response: " + bytesToHex(response));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X ", b));
        }
        return hex.toString().trim();
    }

}
