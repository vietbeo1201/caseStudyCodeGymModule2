import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import src.informationManagement;
public class main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("---CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ---");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Sửa");
            System.out.println("4. Xoá");
            System.out.println("5. Tìm Kiếm");
            System.out.println("6. Sắp xếp");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Đọc file");
            System.out.println("0. Thoát");
            System.out.println("Chọn chức năng: ");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            informationManagement inm = new informationManagement();
            switch (option){
                case 1: //display
                    inm.display();
                    break;
                case 2: // add
                    inm.addInformation();
                    break;
                case 3: // update
                    inm.updateInformation();
                    break;
                case 4: // delete
                    inm.deleteInformation();
                    break;
                case 5: // search
                    inm.searchInformation();
                    break;
                case 6: // sort
                    inm.sortInformation();
                    break;
                case 7: // file write
                    inm.writeIntoFile();
                    break;
                case 8: // file read
                    inm.readFromFile();
                    break;
                case 0:
                    System.out.println("See you soon!");
                    System.exit(0);
                    break;
                default: // your option is invalid
                    System.out.println("Your option is invalid!");
            }
        }


    }
}
