class Rekening {
    protected String nomorRekening;
    protected double saldo;

    public Rekening(String nomorRekening, double saldo) {
        this.nomorRekening = nomorRekening;
        this.saldo = saldo;
    }

    public void tampilkanInfo() {
        System.out.println("No Rekening: " + nomorRekening);
        System.out.println("Saldo: " + saldo);
    }

    protected boolean cekSaldo(double jumlah) {
        return saldo >= jumlah;
    }
}