import javax.smartcardio.*;
import java.util.List;

public class CardCommunicationInterface {
    private CardChannel cardChannel;

    public CardCommunicationInterface() throws Exception {
        // Kart okuyucuya bağlan
        TerminalFactory terminalFactory = TerminalFactory.getDefault();
        CardTerminals terminals = terminalFactory.terminals();

        System.out.println("Available card readers: " + terminals.list());

        List<CardTerminal> terminalList = terminals.list();
        if (terminalList.isEmpty()) {
            throw new Exception("No card readers found. Please connect a card reader.");
        }

        // İlk terminali seç
        CardTerminal terminal = terminalList.get(0);
        System.out.println("Selected card reader: " + terminal.getName());

        if (!terminal.isCardPresent()) {
            throw new Exception("No card detected in the card reader. Please insert a card.");
        }

        // Kartı bağla ve kanal oluştur
        Card card = terminal.connect("*");
        this.cardChannel = card.getBasicChannel();
        System.out.println("Card connected: " + card);

        ATR atr = card.getATR();
        System.out.println("ATR:" + atr);
    }

    public byte[] transmit(byte[] command) throws CardException {
        // APDU komutunu gönder ve yanıt al
        CommandAPDU commandApdu = new CommandAPDU(command);
        ResponseAPDU responseApdu = cardChannel.transmit(commandApdu);

        return responseApdu.getBytes();
    }
}
