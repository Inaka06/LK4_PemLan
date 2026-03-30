public class ProtokolKeamanan {

    private String serverId;

    private final String[] SECRET_KEYS = {
            "KEY_A1", "KEY_B2", "KEY_C3", "KEY_D4", "KEY_E5"
    };

    private static int userCounter = 0;

    public ProtokolKeamanan(String serverId) {
        this.serverId = serverId;
    }

    public String assignKey() {
        int index = userCounter % 5;
        userCounter++;
        return SECRET_KEYS[index];
    }

    public String generateToken(String noRek, String secretKey) {
        return noRek + "_" + secretKey + "_" + serverId;
    }
}