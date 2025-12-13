package com.library.main;

import com.library.dao.BookDAOImpl;
import com.library.dao.IBookDAO;
import com.library.model.Book;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        IBookDAO bookDAO = new BookDAOImpl();

        System.out.println("==========================================");
        System.out.println("🧪 DAO UPDATE & DELETE TEST SENARYOSU");
        System.out.println("==========================================\n");

        // ---------------------------------------------------------
        // ADIM 1: Önce denek (Test) bir kitap ekleyelim
        // ---------------------------------------------------------
        System.out.println("1️⃣  [CREATE] Test kitabı ekleniyor...");
        Book testBook = new Book();
        testBook.setTitle("Silinecek Kitap");
        testBook.setAuthor("Test Yazarı");
        testBook.setIsbn("9781234567897");
        testBook.setAvailable(true);

        boolean isAdded = bookDAO.add(testBook);
        int bookId = testBook.getId(); // DAO'da generated key aldığımız için ID set edildi.

        if (isAdded && bookId > 0) {
            System.out.println("✅  Kitap Eklendi! ID: " + bookId);
        } else {
            System.out.println("❌  Kitap eklenemedi, test iptal!");
            return;
        }

        // ---------------------------------------------------------
        // ADIM 2: Kitabı Güncelleyelim (UPDATE)
        // ---------------------------------------------------------
        System.out.println("\n2️⃣  [UPDATE] Kitap bilgileri değiştiriliyor...");

        // Veriyi değiştiriyoruz
        testBook.setTitle("GÜNCELLENMİŞ KİTAP ADI");
        testBook.setAvailable(false); // Mesela ödünç verildi gibi yapalım

        boolean isUpdated = bookDAO.update(testBook);

        if (isUpdated) {
            System.out.println("✅  Update işlemi veritabanında başarılı (True döndü).");

            // Şimdi gerçekten değişmiş mi diye veritabanından tekrar çekip bakalım (Verification)
            Optional<Book> updatedBookOpt = bookDAO.getById(bookId);

            updatedBookOpt.ifPresent(b -> {
                System.out.println("    -> DB'den Gelen Başlık: " + b.getTitle());
                System.out.println("    -> DB'den Gelen Durum: " + (b.isAvailable() ? "Müsait" : "Müsait Değil"));

                if (b.getTitle().equals("GÜNCELLENMİŞ KİTAP ADI") && !b.isAvailable()) {
                    System.out.println("    ✅  Veri bütünlüğü doğrulandı!");
                } else {
                    System.out.println("    ❌  HATA: Veri güncellenmemiş görünüyor!");
                }
            });

        } else {
            System.out.println("❌  Update işlemi başarısız oldu!");
        }

        // ---------------------------------------------------------
        // ADIM 3: Kitabı Silelim (DELETE)
        // ---------------------------------------------------------
        System.out.println("\n3️⃣  [DELETE] Kitap siliniyor (ID: " + bookId + ")...");

        boolean isDeleted = bookDAO.delete(bookId);

        if (isDeleted) {
            System.out.println("✅  Delete işlemi veritabanında başarılı (True döndü).");

            // Gerçekten silindi mi? (Verification)
            Optional<Book> deletedBookCheck = bookDAO.getById(bookId);

            if (deletedBookCheck.isEmpty()) {
                System.out.println("    ✅  KONTROL: Veritabanında arandı ve BULUNAMADI (Optional.empty). Test Başarılı!");
            } else {
                System.out.println("    ❌  HATA: Kitap silinmesine rağmen hala veritabanında geliyor!");
            }

        } else {
            System.out.println("❌  Delete işlemi başarısız oldu!");
        }

        System.out.println("\n==========================================");
        System.out.println("🏁 TEST TAMAMLANDI");
        System.out.println("==========================================");
    }
}