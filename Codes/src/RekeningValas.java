class RekeningValas extends Rekening implements TransferGlobal {

    private ProtokolKeamanan keamanan;
    private String secretKey;

    private int attempt = 0;

    public RekeningValas(String nomorRekening, double saldo  , ProtokolKeamanan keamanan) {
        super(nomorRekening, saldo);
        this.keamanan = keamanan;
        this.secretKey = keamanan.assignKey();
    }

    @Override
    public boolean verifikasiDigital(String token) {

        if (attempt >= 3) {
            System.out.println("Akun diblokir.");
            return false;
        }

        String tokenAsli = keamanan.generateToken(nomorRekening, secretKey);

        if (tokenAsli.equals(token)) {
            System.out.println("Verifikasi berhasil.");
            attempt = 0;
            return true;
        } else {
            attempt++;
            System.out.println("Token salah (" + attempt + "/3)");

            if (attempt >= 3) {
                System.out.println("Akun sekarang diblokir.");
            }

            return false;
        }
    }

    public String getTokenAsli() {
        return keamanan.generateToken(nomorRekening, secretKey);
    }

    @Override
    public boolean prosesTransaksi(double jumlah) {
        if (!cekSaldo(jumlah)) {
            System.out.println("Saldo tidak cukup!");
            return false;
        }

        saldo -= jumlah;
        System.out.println("Transaksi berhasil: " + jumlah);
        return true;
    }

    @Override
    public double konversiMataUang(double jumlah, double kurs) {
        return jumlah * kurs;
    }

    @Override
    public boolean kirimGlobal(String negaraTujuan, double jumlah) {
        System.out.println("Mengirim ke " + negaraTujuan);

        if (!prosesTransaksi(jumlah)) {
            System.out.println("Transfer gagal.");
            return false;
        }

        System.out.println("Transfer internasional berhasil!");
        return true;
    }
}