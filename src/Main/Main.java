/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import java.util.Scanner;
import Model.Mahasiswa;
import Model.MataKuliah;
import Service.Service;
/**
 *
 * @author Regyma
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("siak?serverTimezone=UTC");
        dataSource.setServerName("localhost");

        dataSource.setPortNumber(3306);

        Service service = new Service();
        service.setDataSource(dataSource);
        
        Scanner in = new Scanner(System.in);
        System.out.println("Sistem Informasi Akademik");
        Boolean mainmenu = true;
        while (mainmenu) {
            System.out.println("\nDaftar Menu ");
            System.out.println("\n1. Data Mahasiswa");
            System.out.println("2. Data Mata Kuliah");
            System.out.println("3. Input Nilai Mahasiswa");
            System.out.print("\nMasukkan pilihan : ");
            String pilihanutama = in.nextLine();
            switch(Integer.parseInt(pilihanutama)) {
                case 1:
                    getMenuMahasiswa(service);
                    break;
                case 2:
                    getMenuMataKuliah(service);
                    break;
                case 3:
                    getMenuNilai();
                    break;
                default:
                    break;
            }
            
        }
    }
    
    public static void getMenuMahasiswa(Service service) {
        Boolean mahasiswamenu = true;
        Scanner in = new Scanner(System.in);
        while (mahasiswamenu) {
            System.out.println("\nMenu Data Mahasiswa");
            System.out.println("\n1. Lihat Daftar Mahasiswa");
            System.out.println("2. Tambah Data Mahasiswa");
            System.out.println("3. Ubah Data Mahasiswa");
            System.out.println("4. Hapus Data Mahasiswa");
            System.out.println("\n0. Kembali ke Menu Utama");
            System.out.print("\nMasukkan pilihan : ");
            String pilihanmahasiswa = in.nextLine();
            switch (Integer.parseInt(pilihanmahasiswa)) {
                case 1:
                    List<Mahasiswa> mahasiswaR = service.getAllMahasiswa();
                    if (mahasiswaR.isEmpty()) {
                        System.out.println("\nDaftar Mahasiswa TIdak Ada");
                        break;
                    }
                    System.out.println("NPM \t | NAMA \t\t | TANGGAL LAHIR");
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                    mahasiswaR.forEach((mahasiswa) -> {
                        System.out.println(mahasiswa.getNpm() + "\t | " + mahasiswa.getNama() + " \t | " + mahasiswa.getTgllahir());
            });
                    break;
                case 2:
                    System.out.print("NPM : ");
                    String npm = in.nextLine();
                    System.out.print("Nama : ");                    
                    String nama = in.nextLine();
                    System.out.print("Tgl Lahir (YYYY-MM-DD) : ");                    
                    String tgllahir = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Mahasiswa mahasiswa = new Mahasiswa();
                        mahasiswa.setNpm(Integer.parseInt(npm));
                        mahasiswa.setNama(nama);
                        mahasiswa.setTgllahir(tgllahir);
                        service.save(mahasiswa);
                    }
                    break;
                case 3:
                    System.out.print("Masukkan NPM : ");
                    String npm_x = in.nextLine();
                    Mahasiswa mahasiswa_x = service.getMahasiswa(Integer.parseInt(npm_x));
                    if (mahasiswa_x == null) {
                        System.out.println("mahasiswa dengan NPM " + npm_x + " tidak ada");
                        break;
                    }
                    System.out.print("Nama : ");                    
                    String nama_x = in.nextLine();
                    System.out.print("Tanggal Lahir (YYYY-MM-DD) : ");                    
                    String tgllahir_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah_x = in.nextLine();
                    if (tambah_x.toLowerCase().equals("y")) {
                        mahasiswa_x.setNpm(Integer.parseInt(npm_x));
                        mahasiswa_x.setNama(nama_x);
                        mahasiswa_x.setTgllahir(tgllahir_x);
                        service.update(mahasiswa_x);
                    }
                    break;
                case 4:
                    System.out.print("Masukkan NPM : ");
                    String npm_d = in.nextLine();
                    Mahasiswa mahasiswa_d = service.getMahasiswa(Integer.parseInt(npm_d));
                    if (mahasiswa_d == null) {
                        System.out.println("mahasiswa dengan NPM "+npm_d+" Tidak ditemukan");
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(mahasiswa_d);
                    }
                    break;
                case 0:
                    mahasiswamenu = false;
                    break;
            }

        }
    }
    
    public static void getMenuMataKuliah(Service service) {
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while (active) {
            System.out.println("\nMenu Data Mata Kuliah : \n");
            System.out.println("1. Lihat Daftar Mata Kuliah");
            System.out.println("2. Tambah Data Mata Kuliah");
            System.out.println("3. Ubah Data Mata Kuliah");
            System.out.println("4. Hapus Data Mata Kuliah");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<MataKuliah> matakuliahR = (List<MataKuliah>) service.getAllMataKuliah();
                    if (matakuliahR.isEmpty()) {
                        System.out.println("\nMata kuliah Belum terdaftar. Silakan tambahkan mata kuliah terlebih dahulu!");
                    } else {
                        System.out.println("\nKode Mata Kuliah | Nama Mata Kuliah\t | Jumlah SKS");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        matakuliahR.forEach((matakuliah) -> {
                            System.out.println(matakuliah.getKode_mk()+ "\t\t | " + matakuliah.getNama_mk()+ "\t\t|  " + matakuliah.getSks());
                });
                    }
                    break;
                case "2":
                    System.out.print("Kode Mata Kuliah : ");
                    String kode_mk = in.nextLine();
                    System.out.print("Nama Mata Kuliah : ");
                    String nama_mk = in.nextLine();
                    System.out.print("Jumlah SKS : ");
                    String sks = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        MataKuliah matakuliah = new MataKuliah();
                        matakuliah.setNama_mk(nama_mk);
                        matakuliah.setSks(Integer.parseInt(sks));
                        service.save(matakuliah);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan ID Mata Kuliah : ");
                    String id_matakuliah_x = in.nextLine();
                    MataKuliah matakuliah_x = service.getMataKuliah(Integer.parseInt(id_matakuliah_x));
                    if (matakuliah_x == null) {
                        System.out.println("Tidak ditemukan mata kuliah dengan ID " + id_matakuliah_x);
                        break;
                    }
                    System.out.print("Nama Mata Kuliah : ");
                    String nama_mk_x = in.nextLine();
                    System.out.print("Jumlah SKS : ");
                    String sks_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String ubah_x = in.nextLine();
                    if (ubah_x.toLowerCase().equals("y")) {
                        matakuliah_x.setKode_mk(Integer.parseInt(id_matakuliah_x));
                        matakuliah_x.setNama_mk(nama_mk_x);
                        matakuliah_x.setSks(Integer.parseInt(sks_x));
                        service.update(matakuliah_x);
                    }
                    break;
                case "4":
                    System.out.print("Masukkan ID Mata Kuliah : ");
                    String id_matakuliah_d = in.nextLine();
                    MataKuliah matakuliah_d = service.getMataKuliah(Integer.parseInt(id_matakuliah_d));
                    if (matakuliah_d == null) {
                        System.out.println("Tidak ditemukan mata kuliah dengan ID " + id_matakuliah_d);
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(matakuliah_d);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    
    public static void getMenuNilai() {
        
    }
    
}
