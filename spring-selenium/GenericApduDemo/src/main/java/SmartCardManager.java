import java.util.ArrayList;
import java.util.List;

public class SmartCardManager implements IApduHandler {

    private IApduHandler apduHandler;

    public SmartCardManager(IApduHandler apduHandler) {
        this.apduHandler = apduHandler;
    }

    @Override
    public byte[] sendApdu(byte[] command) throws Exception {
        List<byte[]> responseParts = new ArrayList<>();
        boolean isComplete = false;

        while (!isComplete) {
            // İlk komut gönderiliyor
            byte[] response = apduHandler.sendApdu(command);

            // Cevap içerisindeki veriyi ayrıştır ve ekle
            responseParts.add(ApduResponseParser.extractActualData(response));

            // Yanıtı analiz ederek bir sonraki adımı belirle
            int remainingLength = ApduResponseParser.getRemainingLength(response);
            if (remainingLength > 0) {
                // Daha fazla veri almak için komut oluştur
                command = createGetMoreDataCommand(remainingLength);
            } else if (!ApduResponseParser.isFinalResponse(response)) {
                // Yanıt, bir sonraki özel APDU komutunun gönderilmesini gerektiriyorsa
                command = createNextApduCommand(response);
            } else {
                // İşlem tamamlandı
                isComplete = true;
            }
        }

        return mergeResponses(responseParts);
    }

    private byte[] createNextApduCommand(byte[] response) {
        // Yanıta bağlı olarak bir sonraki APDU komutunu oluştur
        // Örnek: Response verisinden işlem ID'si veya başka bilgi alınıp yeni komut oluşturulabilir
        byte[] nextCommand = new byte[] { (byte) 0x00, (byte) 0xA5, (byte) response[0], (byte) response[1] };
        System.out.println("Next APDU Command: " + bytesToHex(nextCommand));
        return nextCommand;
    }

    private byte[] createGetMoreDataCommand(int remainingLength) {
        // Daha fazla veri almak için APDU komutu
        return new byte[] { (byte) 0x00, (byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) remainingLength };
    }

    private byte[] mergeResponses(List<byte[]> responseParts) {
        // Tüm parçaları birleştir
        int totalLength = responseParts.stream().mapToInt(part -> part.length).sum();
        byte[] mergedResponse = new byte[totalLength];

        int currentIndex = 0;
        for (byte[] part : responseParts) {
            System.arraycopy(part, 0, mergedResponse, currentIndex, part.length);
            currentIndex += part.length;
        }
        return mergedResponse;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X ", b));
        }
        return hex.toString();
    }
}
