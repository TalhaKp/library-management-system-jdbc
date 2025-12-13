package com.library.main;

import com.library.dao.BookDAOImpl;
import com.library.dao.IBookDAO;
import com.library.model.Book;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // DAO örneğini oluştur
        IBookDAO bookDAO = new BookDAOImpl();

        System.out.println("=========== TEST BAŞLIYOR ===========");/*

        // ADIM 1: Önce veritabanına bir kitap ekleyelim (ki ID oluşsun)
        Book myBook = new Book();
        myBook.setTitle("Suç ve Ceza");
        myBook.setAuthor("Dostoyevski");
        myBook.setIsbn("1234567890");
        myBook.setAvailable(true);

        System.out.println("-> Kitap ekleniyor...");
        bookDAO.add(myBook);

        // Senin add metodun book nesnesine ID'yi set ettiği için direkt alabiliriz:
        int newId = myBook.getId();
        System.out.println("-> Eklenen Kitabın ID'si: " + newId);

        System.out.println("-------------------------------------");

        // ADIM 2: getById (Read) Testi
        System.out.println("-> ID ile okuma yapılıyor (ID: " + newId + ")...");*/
        Book foundBook = bookDAO.getById(1);

        if (foundBook != null) {
            System.out.println("✅ BAŞARILI: Kitap veritabanından çekildi.");
            System.out.println("   ID: " + foundBook.getId());
            System.out.println("   Başlık: " + foundBook.getTitle());
            System.out.println("   Yazar: " + foundBook.getAuthor());
            System.out.println("   ISBN: " + foundBook.getIsbn());
        } else {
            System.out.println("❌ HATA: Eklenen kitap okunamadı (Null döndü)!");
        }

        System.out.println("-------------------------------------");

        // ADIM 3: Olmayan ID Testi
        int fakeId = 99999;
        System.out.println("-> Olmayan ID ile okuma yapılıyor (ID: " + fakeId + ")...");
        Book noBook = bookDAO.getById(fakeId);

        if (noBook == null) {
            System.out.println("✅ BAŞARILI: Olmayan kitap için null döndü.");
        } else {
            System.out.println("❌ HATA: Olmayan kitap için veri döndü! Mantık hatası.");
        }

        System.out.println("=========== TEST BİTTİ ===========");



        System.out.println("=========== GET ALL (PAGINATION) TESTİ ===========");


        int limit = 2;
        int offset = 0;
        System.out.println("-> SENARYO 1: Sayfa 1 isteniyor (Limit: " + limit + ", Offset: " + offset + ")");

        List<Book> page1 = bookDAO.getAll(limit, offset);
        printBooks(page1);

        // ADIM 3: İkinci Sayfayı Çek (Limit: 2, Offset: 2)
        // Beklenti: Sonraki 2 kitabı getirmeli (3. ve 4. kitaplar)
        offset = 2;
        System.out.println("\n-> SENARYO 2: Sayfa 2 isteniyor (Limit: " + limit + ", Offset: " + offset + ")");

        List<Book> page2 = bookDAO.getAll(limit, offset);
        printBooks(page2);

        // ADIM 4: Kalanları Çek (Limit: 2, Offset: 4)
        // Beklenti: 5. kitabı getirmeli (Sadece 1 tane kalmıştı)
        offset = 4;
        System.out.println("\n-> SENARYO 3: Sayfa 3 isteniyor (Limit: " + limit + ", Offset: " + offset + ")");

        List<Book> page3 = bookDAO.getAll(limit, offset);
        printBooks(page3);

        System.out.println("\n=========== TEST BİTTİ ===========");

    }
    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("   [Liste Boş]");
            return;
        }
        for (Book b : books) {
            System.out.println("   - ID: " + b.getId() + " | " + b.getTitle() + " (" + b.getAuthor() + ")");
        }
    }
}
