import java.util.Scanner;

public class Main {

    static Menu[] daftarMenu = {
        new Menu("Nasi Padang", 25000, "Makanan"),
        new Menu("Mie Ayam", 20000, "Makanan"),
        new Menu("Ayam Bakar", 30000, "Makanan"),
        new Menu("Sate", 28000, "Makanan"),
        new Menu("Es Teh", 5000, "Minuman"),
        new Menu("Jus Jeruk", 10000, "Minuman"),
        new Menu("Kopi", 8000, "Minuman"),
        new Menu("Air Mineral", 4000, "Minuman")
    };

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        tampilkanMenu();

        // Maksimal 4 pesanan
        System.out.print("Masukkan nomor menu ke-1: ");
        int p1 = input.nextInt();
        System.out.print("Jumlah: ");
        int j1 = input.nextInt();

        System.out.print("Masukkan nomor menu ke-2 (0 jika tidak ada): ");
        int p2 = input.nextInt();
        int j2 = 0;
        if (p2 != 0) {
            System.out.print("Jumlah: ");
            j2 = input.nextInt();
        }

        System.out.print("Masukkan nomor menu ke-3 (0 jika tidak ada): ");
        int p3 = input.nextInt();
        int j3 = 0;
        if (p3 != 0) {
            System.out.print("Jumlah: ");
            j3 = input.nextInt();
        }

        System.out.print("Masukkan nomor menu ke-4 (0 jika tidak ada): ");
        int p4 = input.nextInt();
        int j4 = 0;
        if (p4 != 0) {
            System.out.print("Jumlah: ");
            j4 = input.nextInt();
        }

        hitungDanCetakStruk(p1, j1, p2, j2, p3, j3, p4, j4);
    }

    public static void tampilkanMenu() {

        System.out.println("===== MENU MAKANAN =====");
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " +
                    daftarMenu[i].getNama() +
                    " - Rp " + daftarMenu[i].getHarga());
        }

        System.out.println("\n===== MENU MINUMAN =====");
        for (int i = 4; i < 8; i++) {
            System.out.println((i + 1) + ". " +
                    daftarMenu[i].getNama() +
                    " - Rp " + daftarMenu[i].getHarga());
        }

        System.out.println();
    }

    public static void hitungDanCetakStruk(
            int p1, int j1,
            int p2, int j2,
            int p3, int j3,
            int p4, int j4) {

        int subtotal = 0;

        subtotal += daftarMenu[p1 - 1].getHarga() * j1;

        if (p2 != 0)
            subtotal += daftarMenu[p2 - 1].getHarga() * j2;

        if (p3 != 0)
            subtotal += daftarMenu[p3 - 1].getHarga() * j3;

        if (p4 != 0)
            subtotal += daftarMenu[p4 - 1].getHarga() * j4;

        int diskon = 0;

        if (subtotal > 100000) {
            diskon = (int) (subtotal * 0.10);
        }

        // Promo beli 1 gratis 1 minuman jika subtotal > 50000
        if (subtotal > 50000) {
            if (daftarMenu[p1 - 1].getKategori().equals("Minuman") && j1 >= 2)
                subtotal -= daftarMenu[p1 - 1].getHarga();
        }

        int pajak = (int) (subtotal * 0.10);
        int service = 20000;

        int totalAkhir = subtotal - diskon + pajak + service;

        // Cetak Struk
        System.out.println("\n========= STRUK =========");

        System.out.println(daftarMenu[p1 - 1].getNama() +
                " x" + j1 +
                " = Rp " + (daftarMenu[p1 - 1].getHarga() * j1));

        if (p2 != 0)
            System.out.println(daftarMenu[p2 - 1].getNama() +
                    " x" + j2 +
                    " = Rp " + (daftarMenu[p2 - 1].getHarga() * j2));

        if (p3 != 0)
            System.out.println(daftarMenu[p3 - 1].getNama() +
                    " x" + j3 +
                    " = Rp " + (daftarMenu[p3 - 1].getHarga() * j3));

        if (p4 != 0)
            System.out.println(daftarMenu[p4 - 1].getNama() +
                    " x" + j4 +
                    " = Rp " + (daftarMenu[p4 - 1].getHarga() * j4));

        System.out.println("--------------------------");
        System.out.println("Subtotal      : Rp " + subtotal);
        System.out.println("Diskon        : Rp " + diskon);
        System.out.println("Pajak 10%     : Rp " + pajak);
        System.out.println("Service       : Rp " + service);
        System.out.println("Total Bayar   : Rp " + totalAkhir);
        System.out.println("==========================");


    }
}