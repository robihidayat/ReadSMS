package one.enix.smsforward;

public class MessageForm {
    final String umi;
    final String type;
    final String source;
    final String body;
    final String destination;
    final String encoding;
    final String receivedAt;
    final Integer version;

    public MessageForm(String umi, String type, String source, String body, String destination, String encoding, String receivedAt, Integer version) {
        this.umi = umi;
        this.type = type;
        this.source = source;
        this.body = body;
        this.destination = destination;
        this.encoding = encoding;
        this.receivedAt = receivedAt;
        this.version = version;
    }
}
