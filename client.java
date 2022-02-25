package docGhiFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class client {

    public static void main(String[] args) {
        List<nhanVien> list = new ArrayList<>();
        System.out.print("Nhập số lượng nhân viên: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        nhanVien Nv;
        for (int i = 0; i < n; i++) {
            Nv = new nhanVien();
            Nv.input();
            list.add(Nv);
        }
        System.out.println("Bạn có muốn lưu dữ liệu vào file không?(Y/N)");
        System.out.print("--->Nhập Y để lưu, N để bỏ qua: ");
        String chon = sc.next();
        if (chon.equalsIgnoreCase("Y")) {
            try {
                FileOutputStream fos = new FileOutputStream("nhanvien.dat");
                try {
                    try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        for (nhanVien vien : list) {
                            oos.writeObject(vien);
                        }
                        oos.close();
                    }
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            FileInputStream fis = new FileInputStream("nhanvien.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(fis.available()>0){
                nhanVien NV = (nhanVien)ois.readObject();
                System.out.println(NV.toString());
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        //copyFile
        List<nhanVien> list2 = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("nhanvien.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(fis.available()>0){
                nhanVien a = (nhanVien)ois.readObject();
                list2.add(a);
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.print("Nhập tên file cần copy sang: ");
        String fName = sc.next();
        try {
            FileOutputStream fos = new FileOutputStream(fName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (nhanVien vien : list2) {
                oos.writeObject(vien);
            }
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
