package fByteBai3;

import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

    public static void main(String[] args) {
        String chon;
        Scanner sc = new Scanner(System.in);
        List<Book> list = new ArrayList<>();
        do {
            System.out.print("Nhập sách cần thêm: (Y/N)--->");
            chon = sc.next();
            if (chon.equalsIgnoreCase("N")) {
                break;
            }
            Book book = new Book();
            book.input();
            list.add(book);
        } while (chon.equalsIgnoreCase("Y"));

        try {
            FileOutputStream fos = new FileOutputStream("books.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Book book : list) {
                oos.writeObject(book);
            }
            oos.close();

            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("----DANH SÁCH----");
//        for (Book book : list) {
//            System.out.println(book.toString());
//        }
        //Xóa file
        System.out.print("Bạn có muốn xóa file books.dat không?-(Y/N)");
        String tl = sc.next();

        File f = new File("books.dat");
        if (tl.equalsIgnoreCase("Y")) {
            //copy sang file mới
            System.out.print("Nhập file bạn muốn chuyển dữ liệu sang: ");
            String file = sc.next();
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Book book : list) {
                    oos.writeObject(book);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
            f.delete();
            System.out.println("Đã xóa và chuyển dữ liệu xong!");
        }
        else
            System.out.println("okkkkkkk");
    }
}
