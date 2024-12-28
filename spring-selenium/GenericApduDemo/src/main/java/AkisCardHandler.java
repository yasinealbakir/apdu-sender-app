import java.util.Arrays;

public class AkisCardHandler implements IApduHandler {
    private CardCommunicationInterface cardInterface;

    public AkisCardHandler() throws Exception {
        this.cardInterface = new CardCommunicationInterface();
    }

    @Override
    public byte[] sendApdu(byte[] command) throws Exception {
        System.out.println("Sending APDU command: " + bytesToHex(command));

        // Kartla APDU komutunu gönder
        byte[] response = cardInterface.transmit(command);

        System.out.println("Received APDU response: " + bytesToHex(response));
        validateResponse(response);

        return response;
    }

    private void validateResponse(byte[] response) throws Exception {
        if (response == null || response.length < 2) {
            throw new Exception("Invalid APDU response: " + Arrays.toString(response));
        }

        // Örnek: SW1/SW2 = 6A 82 bir hata durumunu işaret edebilir
        byte sw1 = response[response.length - 2];
        byte sw2 = response[response.length - 1];

        if (sw1 == (byte) 0x6A && sw2 == (byte) 0x82) {
            throw new Exception("File not found (SW1/SW2 = 6A 82)");
        }
    }

    // Debug için yardımcı metot: Byte dizisini hexadecimal string olarak gösterir
    private String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X ", b));
        }
        return hex.toString().trim();
    }
}
