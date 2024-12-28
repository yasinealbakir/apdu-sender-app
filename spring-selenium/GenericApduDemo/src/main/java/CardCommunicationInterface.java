import javax.smartcardio.*;

public class CardCommunicationInterface {
    private CardChannel cardChannel;

    public CardCommunicationInterface() throws Exception {
        // Kart okuyucuya bağlan
        TerminalFactory terminalFactory = TerminalFactory.getDefault();
        CardTerminals terminals = terminalFactory.terminals();
        CardTerminal terminal = terminals.list().get(0); // İlk terminali seç

        // Kartı bağla ve kanal oluştur
        Card card = terminal.connect("*");
        this.cardChannel = card.getBasicChannel();
        System.out.println("Card connected: " + card);
    }

    public byte[] transmit(byte[] command) throws CardException {
        // APDU komutunu gönder ve yanıt al
        CommandAPDU commandApdu = new CommandAPDU(command);
        ResponseAPDU responseApdu = cardChannel.transmit(commandApdu);

        return responseApdu.getBytes();
    }
}
