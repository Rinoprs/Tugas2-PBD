import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    static Menu[] daftarMenu = new Menu[20];
    static int jumlahMenu = 0;

    public static void main(String[] args) {

        // Data awal menu (minimal 4 makanan & 4 minuman)
        tambahMenuAwal();

        while (true) {
            System.out.println("\n=== SISTEM RESTORAN ===");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Manajemen Menu (Pemilik)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = input.nextInt();
            input.nextLine();

            if (pilih == 1) {
                menuPelanggan();
            } else if (pilih == 2) {
                manajemenMenu();
            } else if (pilih == 3) {
                System.out.println("Terima kasih!");
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tambahMenuAwal() {
        daftarMenu[jumlahMenu++] = new Menu("Nasi Goreng", 25000, "Makanan");
        daftarMenu[jumlahMenu++] = new Menu("Mie Ayam", 20000, "Makanan");
        daftarMenu[jumlahMenu++] = new Menu("Ayam Bakar", 30000, "Makanan");
        daftarMenu[jumlahMenu++] = new Menu("Sate Ayam", 28000, "Makanan");

        daftarMenu[jumlahMenu++] = new Menu("Es Teh", 10000, "Minuman");
        daftarMenu[jumlahMenu++] = new Menu("Jus Alpukat", 15000, "Minuman");
        daftarMenu[jumlahMenu++] = new Menu("Kopi Hitam", 12000, "Minuman");
        daftarMenu[jumlahMenu++] = new Menu("Air Mineral", 8000, "Minuman");
    }

    static void tampilMenu() {
        System.out.println("\n=== DAFTAR MENU ===");

        System.out.println("\n--- MAKANAN ---");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].kategori.equalsIgnoreCase("Makanan")) {
                System.out.println((i + 1) + ". " + daftarMenu[i].nama + " - Rp " + daftarMenu[i].harga);
            }
        }

        System.out.println("\n--- MINUMAN ---");
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].kategori.equalsIgnoreCase("Minuman")) {
                System.out.println((i + 1) + ". " + daftarMenu[i].nama + " - Rp " + daftarMenu[i].harga);
            }
        }
    }

    static void menuPelanggan() {
        String[] pesanan = new String[50];
        int[] jumlah = new int[50];
        int totalItem = 0;

        while (true) {
            tampilMenu();
            System.out.print("\nMasukkan nama menu (atau 'selesai'): ");
            String pilih = input.nextLine();

            if (pilih.equalsIgnoreCase("selesai")) {
                break;
            }

            boolean ditemukan = false;

            for (int i = 0; i < jumlahMenu; i++) {
                if (daftarMenu[i].nama.equalsIgnoreCase(pilih)) {
                    System.out.print("Jumlah: ");
                    int jml = input.nextInt();
                    input.nextLine();

                    pesanan[totalItem] = daftarMenu[i].nama;
                    jumlah[totalItem] = jml;
                    totalItem++;
                    ditemukan = true;
                    break;
                }
            }

            if (!ditemukan) {
                System.out.println("Menu tidak tersedia! Silakan input kembali.");
            }
        }

        hitungTotal(pesanan, jumlah, totalItem);
    }

    static void hitungTotal(String[] pesanan, int[] jumlah, int totalItem) {
        double subtotal = 0;
        double diskon = 0;
        double pajak;
        double biayaService = 20000;
        int totalMinuman = 0;

        for (int i = 0; i < totalItem; i++) {
            for (int j = 0; j < jumlahMenu; j++) {
                if (pesanan[i].equalsIgnoreCase(daftarMenu[j].nama)) {
                    subtotal += daftarMenu[j].harga * jumlah[i];

                    if (daftarMenu[j].kategori.equalsIgnoreCase("Minuman")) {
                        totalMinuman += jumlah[i];
                    }
                }
            }
        }

        // Diskon 10% jika subtotal > 100.000
        if (subtotal > 100000) {
            diskon = subtotal * 0.10;
        }

        // Promo beli 1 gratis 1 minuman jika subtotal > 50.000
        double promoMinuman = 0;
        if (subtotal > 50000 && totalMinuman >= 2) {
            promoMinuman = totalMinuman / 2 * 10000; // asumsi rata-rata harga minuman 10rb
        }

        double setelahDiskon = subtotal - diskon - promoMinuman;

        pajak = setelahDiskon * 0.10;

        double totalAkhir = setelahDiskon + pajak + biayaService;

        cetakStruk(pesanan, jumlah, totalItem, subtotal, diskon, promoMinuman, pajak, biayaService, totalAkhir);
    }

    static void cetakStruk(String[] pesanan, int[] jumlah, int totalItem,
                           double subtotal, double diskon, double promo,
                           double pajak, double service, double total) {

        System.out.println("\n=== STRUK PEMBAYARAN ===");

        for (int i = 0; i < totalItem; i++) {
            System.out.println(pesanan[i] + " x" + jumlah[i]);
        }

        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Promo Minuman: Rp " + promo);
        System.out.println("Pajak 10%: Rp " + pajak);
        System.out.println("Biaya Service: Rp " + service);
        System.out.println("Total Bayar: Rp " + total);
    }

    static void manajemenMenu() {
        while (true) {
            System.out.println("\n=== MANAJEMEN MENU ===");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            int pilih = input.nextInt();
            input.nextLine();

            if (pilih == 1) {
                System.out.print("Nama Menu: ");
                String nama = input.nextLine();
                System.out.print("Harga: ");
                double harga = input.nextDouble();
                input.nextLine();
                System.out.print("Kategori (Makanan/Minuman): ");
                String kategori = input.nextLine();

                daftarMenu[jumlahMenu++] = new Menu(nama, harga, kategori);
                System.out.println("Menu berhasil ditambahkan!");

            } else if (pilih == 2) {
                tampilMenu();
                System.out.print("Pilih nomor menu: ");
                int nomor = input.nextInt();
                input.nextLine();

                System.out.print("Yakin ubah harga? (Ya/Tidak): ");
                String konfirmasi = input.nextLine();

                if (konfirmasi.equalsIgnoreCase("Ya")) {
                    System.out.print("Harga baru: ");
                    double hargaBaru = input.nextDouble();
                    input.nextLine();
                    daftarMenu[nomor - 1].harga = hargaBaru;
                    System.out.println("Harga berhasil diubah!");
                }

            } else if (pilih == 3) {
                tampilMenu();
                System.out.print("Pilih nomor menu: ");
                int nomor = input.nextInt();
                input.nextLine();

                System.out.print("Yakin hapus? (Ya/Tidak): ");
                String konfirmasi = input.nextLine();

                if (konfirmasi.equalsIgnoreCase("Ya")) {
                    for (int i = nomor - 1; i < jumlahMenu - 1; i++) {
                        daftarMenu[i] = daftarMenu[i + 1];
                    }
                    jumlahMenu--;
                    System.out.println("Menu berhasil dihapus!");
                }

            } else if (pilih == 4) {
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }
}A

