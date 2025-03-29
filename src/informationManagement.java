package src;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class informationManagement extends information implements IinformationManagement {

    public List<information> readDataFromFile() {
        List<information> informationArray = new ArrayList();
        File file = new File("information.txt");
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(","); // split data from string to elements\
                information eachInfor = new information(dataLine[0],dataLine[1],dataLine[2],dataLine[3],dataLine[4],dataLine[5],dataLine[6]);
                informationArray.add(eachInfor);
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return informationArray;
    }

    @Override
    public void display(){
        try {
            List<information> peopleCount = readDataFromFile();
            for (int i = 1; i < peopleCount.size() + 1; i++) {
                System.out.println("nguoi thu " + i + " : " + peopleCount.get(i));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addInformation() {
        // imput information.txt
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter your gender: ");
        String gender = new Scanner(System.in).nextLine();
        String phoneNumber;
        while (true){
            System.out.println("Enter the phone number of the person");
            phoneNumber = new Scanner(System.in).nextLine();
            if (phoneNumber.matches("[0-9]{10}"))
                break;
            else {
                System.out.println("invalid phone number");
            }
        }
        System.out.println("Enter your group: ");
        String group = new Scanner(System.in).nextLine();
        System.out.println("Enter your address: ");
        String address = new Scanner(System.in).nextLine();
        System.out.println("Enter your date of birth: ");
        String dateOfBirth = new Scanner(System.in).nextLine();
        // validate dateOfBirth
        try{
            Date date = dateFormat.parse(dateOfBirth);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Invalid date format");
        }
        String email;
        while (true){
            System.out.println("Enter your email: ");
            email = new Scanner(System.in).nextLine();
            if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                break;
            } else {
                System.out.println("invalid email");
            }
        }
        ArrayList<information> informationArray = new ArrayList<>();
        informationArray.add(new information(name, gender, phoneNumber, group, address, dateOfBirth, email));

        // check File
        File informationFile = new File("information.txt");

        if (!informationFile.exists()) {
            System.out.println("Information file does not exist");
        } else {
            if (!informationFile.canWrite()) {
                System.out.println("Cannot write to file");
            } else {
                try {
                    FileWriter fw = new FileWriter(informationFile);
                    fw.append(informationArray.toString());
                    fw.append("\n");
                    fw.close();
                } catch (IOException e){
                    System.out.println("Cannot write to file" + e.getMessage());
                }
            }
        }
    }

    @Override
    public void updateInformation() {
        // search by name and phone number

    }

    @Override
    public void deleteInformation() {

    }

    @Override
    public void searchInformation() {

    }

    @Override
    public void sortInformation() {

    }

    @Override
    public void writeIntoFile() {

    }

    @Override
    public void readFromFile() {

    }

}
