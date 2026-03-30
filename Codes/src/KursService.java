public class KursService {

    public static double getKurs(String negara) {
        switch (negara) {
            case "Jepang": return 130;
            case "Korea": return 12;
            case "Amerika": return 15000;
            default: return 1;
        }
    }
}