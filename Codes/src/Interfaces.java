interface Transaksi {
    boolean prosesTransaksi(double jumlah);
}

interface TransaksiDigital extends Transaksi {
    boolean verifikasiDigital(String token);
}

interface LayananInternasional extends Transaksi {
    double konversiMataUang(double jumlah, double kurs);
}

interface TransferGlobal extends TransaksiDigital, LayananInternasional {
    boolean kirimGlobal(String negaraTujuan, double jumlah);
    void tampilkanInfo();
}