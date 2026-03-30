public class Main {

    public static void main(String[] args) {

        ProtokolKeamanan keamanan = new ProtokolKeamanan("SERVER-01");

        System.out.println("=== SIMULASI 2 USER ===");

        // USER 1 (BERHASIL)
        jalankanSkenario(
                keamanan,
                "12345",
                1000,
                "12345_KEY_A1_SERVER-01", // token benar
                new int[]{1, 2, 3, 4, 0}
        );

        // USER 2 (GAGAL)
        jalankanSkenario(
                keamanan,
                "67890",
                500,
                "TOKEN_RANDOM_HAHAHAHA", // token salah
                new int[]{1, 2, 0}
        );
    }

    public static void jalankanSkenario(
            ProtokolKeamanan keamanan,
            String noRek,
            double saldo,
            String tokenInput,
            int[] menuList
    ) {

        RekeningValas user = new RekeningValas(noRek, saldo, keamanan);

        System.out.println("\n=== USER: " + noRek + " ===");

        // tampilkan token sekali
        String tokenAsli = user.getTokenAsli();
        System.out.println("Token Anda: " + tokenAsli);
        System.out.println("INGAT TOKEN INI, TIDAK AKAN DITUNJUKKAN LAGI\n");

        // simulasi user input (hardcoded)
        System.out.println("Input token: " + tokenInput);

        if (!user.verifikasiDigital(tokenInput)) {
            System.out.println("Akses ditolak.");
            return;
        }

        for (int pilihan : menuList) {
            prosesMenu(user, pilihan);
        }
    }

    public static void prosesMenu(TransferGlobal user, int pilihan) {

        switch (pilihan) {
            case 1:
                transaksiLokal(user, 200);
                break;

            case 2:
                transferGlobal(user, "Jepang", 300);
                break;

            case 3:
                konversi(user, "Jepang", 300);
                break;

            case 4:
                tampilInfo(user);
                break;

            case 0:
                System.out.println("Keluar...");
                break;

            default:
                System.out.println("Menu tidak valid.");
        }
    }

    public static double getKurs(String negara) {
        switch (negara) {
            case "Jepang": return 130;
            case "Korea": return 12;
            case "Amerika": return 15000;
            default: return 1;
        }
    }

    public static void transaksiLokal(TransferGlobal user, double jumlah) {
        System.out.println("[Transaksi Lokal] " + jumlah);
        user.prosesTransaksi(jumlah);
    }

    public static void transferGlobal(TransferGlobal user, String negara, double jumlah) {
        System.out.println("[Transfer Global] ke " + negara + " sebesar " + jumlah);
        user.kirimGlobal(negara, jumlah);
    }

    public static void konversi(TransferGlobal user, String negara, double jumlah) {

        double kurs = getKurs(negara);
        double hasil = user.konversiMataUang(jumlah, kurs);

        System.out.println("[Konversi " + negara + "]");
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Kurs: " + kurs);
        System.out.println("Hasil: " + hasil);
    }

    public static void tampilInfo(TransferGlobal user) {
        ((Rekening) user).tampilkanInfo();
    }
}