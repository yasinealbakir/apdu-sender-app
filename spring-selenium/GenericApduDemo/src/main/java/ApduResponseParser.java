import java.util.Arrays;

public class ApduResponseParser {
    public static int getRemainingLength(byte[] response) {
        // Örnek: Son iki byte kalan uzunluğu içeriyor
        if (response.length >= 2 && response[response.length - 2] == (byte) 0x61) {
            return response[response.length - 1] & 0xFF;
        }
        return 0;
    }

    public static byte[] extractActualData(byte[] response) {
        // Kart cevabının veri kısmını ayrıştır
        if (response.length > 2) {
            return Arrays.copyOf(response, response.length - 2);
        }
        return new byte[0];
    }

    public static boolean isFinalResponse(byte[] response) {
        // Tamamlanma durumunu kontrol et
        return response.length >= 2 && response[response.length - 2] == (byte) 0x90 && response[response.length - 1] == (byte) 0x00;
    }

    public static byte[] extractNextCommandInfo(byte[] response) {
        // Örnek: Yanıttan bir sonraki APDU komutu için gerekli bilgiyi çıkar
        // İlk iki byte komut oluşturmak için kullanılacak veriyi içeriyor olabilir
        if (response.length >= 2) {
            return Arrays.copyOf(response, 2);
        }
        return new byte[0];
    }
}
