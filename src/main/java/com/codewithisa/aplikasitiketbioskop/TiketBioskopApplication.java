package com.codewithisa.aplikasitiketbioskop;

import com.codewithisa.aplikasitiketbioskop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiketBioskopApplication implements CommandLineRunner {

	@Autowired
	MenuService menuService;

	public static void main(String[] args) {
		SpringApplication.run(TiketBioskopApplication.class, args);
	}

	/**
	 * uncomment untuk mencoba aplikasi dengan terminal. anda harus login (jika sudah punya akun) untuk melihat
	 * film yang sedang tayang dan melakukan pemesanan tiket, jika belum punya akun maka diperlukan create akun terlebih
	 * dahulu yang bisa dilakukan di terminal. sebelum memesan pastikan kursi untuk masing-masing film dan jadwal
	 * sudah diinput melalui file SeatServiceImplTest.
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
//		System.out.println();
//		System.out.println("Aplikasi pemesanan tiket bioskop");
//		System.out.println();
//		Scanner scanner = new Scanner(System.in);
//		menuService.menu(scanner);
//		scanner.close();
	}
}
