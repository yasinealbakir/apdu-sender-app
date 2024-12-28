public interface IApduHandler {
    byte[] sendApdu(byte[] command) throws Exception;
}
