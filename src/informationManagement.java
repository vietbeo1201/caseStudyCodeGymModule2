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
                //setId(Integer.parseInt(line));
                String[] dataLine = line.split(","); // split data from string to elements
                int id = Integer.parseInt(dataLine[0]);
                information eachInfor = new information(id ,dataLine[1],dataLine[2],dataLine[3],dataLine[4],dataLine[5],dataLine[6],dataLine[7]);
                informationArray.add(eachInfor);
            }
            br.close();
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return informationArray;
    }

    @Override
    public void display(){
        try {
            List<information> peopleCount = readDataFromFile();
            for (int i = 0; i < peopleCount.size(); i++) {
                System.out.println("nguoi thu " + (i+1) + " : " + peopleCount.get(i));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int autoID(){
        List<information> listInformation = readDataFromFile();
        int  lastID = 1;
        if (!listInformation.isEmpty()) {
            lastID = listInformation.get(listInformation.size() - 1).getId();
            lastID ++;
        }
        return lastID;
    }

    public String validatePhoneNumber(){
        String phoneNumber;
        System.out.println("Enter the phone number of the person");
        phoneNumber = new Scanner(System.in).nextLine();
        if (phoneNumber.matches("[0-9]{10}"))
            return phoneNumber;
        else {
            System.out.println("invalid phone number");
            validatePhoneNumber();
        }
        return phoneNumber;
    }

    public String validateEmail(){
        String email;
        System.out.println("Enter your email: ");
        email = new Scanner(System.in).nextLine();
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            return email;
        } else {
            System.out.println("invalid email");
            validateEmail();
        }
        return email;
    }

    public String validateDate(){
        System.out.println("Enter your date of birth: ");
        String dateOfBirth = new Scanner(System.in).nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date date = dateFormat.parse(dateOfBirth);
        } catch (Exception e){
            System.out.println("Invalid date format");
            validateDate();
        }
        return dateOfBirth;
    }

    @Override
    public void addInformation() {
        // input information.txt
        int lastID = autoID();
        System.out.println("Enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter your gender: ");
        String gender = new Scanner(System.in).nextLine();
        // validate phone number
        String phoneNumber = validatePhoneNumber();
        System.out.println("Enter your group: ");
        String group = new Scanner(System.in).nextLine();
        System.out.println("Enter your address: ");
        String address = new Scanner(System.in).nextLine();
        String dateOfBirth = validateDate();
        String email = validateEmail() ;
        ArrayList<information> informationArray = new ArrayList<>();
        informationArray.add(new information(lastID, name, gender, phoneNumber, group, address, dateOfBirth, email));

        // check File
        File informationFile = new File("information.txt");

        if (!informationFile.exists()) {
            System.out.println("Information file does not exist");
        } else {
            if (!informationFile.canWrite()) {
                System.out.println("Cannot write to file");
            } else {
                try {
                    FileWriter fw = new FileWriter(informationFile, true);
                    fw.write(lastID+ ",");
                    fw.write(name+ ",");
                    fw.write(gender+ ",");
                    fw.write(phoneNumber+ ",");
                    fw.write(group+ ",");
                    fw.write(address+ ",");
                    fw.write(dateOfBirth+ ",");
                    fw.write(email+ ",");
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
        // search information by id
        System.out.println("Enter the id of the information you would like to update: ");
        int upid = new Scanner(System.in).nextInt();
        File file = new File("information.txt");
        ArrayList<String> updateList = new ArrayList<>();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(",");
                int id = Integer.parseInt(dataLine[0]);
                if (upid == id) {
                    // let user change all the information about that person then append into file with old id
                    information infor = new information(id, dataLine[1], dataLine[2], dataLine[3], dataLine[4],dataLine[5],dataLine[6],dataLine[7]);
                    System.out.println("Are you sure you want to update the information?" + infor); // double check
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = new Scanner(System.in).nextInt();
                    switch (choice) {
                        case 2:
                            System.exit(0);
                            break;
                        case 1:
                            System.out.println("rewrite name");
                            dataLine[1] = new Scanner(System.in).nextLine();
                            System.out.println("rewrite gender");
                            dataLine[2] = new Scanner(System.in).nextLine();
                            dataLine[3] = validatePhoneNumber();
                            System.out.println("rewrite group");
                            dataLine[4] = new Scanner(System.in).nextLine();
                            System.out.println("rewrite address");
                            dataLine[5] = new Scanner(System.in).nextLine();
                            System.out.println("rewrite dateOfBirth");
                            dataLine[6] = new Scanner(System.in).nextLine();
                            dataLine[7] = validateEmail();
                            break;
                    }
                    information newInfor = new information(id, dataLine[1], dataLine[2], dataLine[3], dataLine[4],dataLine[5],dataLine[6],dataLine[7]);
//                    FileWriter fw = new FileWriter("information.txt", true);
//                    fw.write(newInfor.toString());
                    updateList.add(newInfor.toString());
                } else {
                    updateList.add(line);
                }
            }
            br.close();
            fr.close();
            FileWriter fw = new FileWriter("information.txt");
            fw.write("");
            fw.close();
            FileWriter fw1 = new FileWriter("information.txt",true);
            for (int i = 0; i < updateList.size(); i++) {
                fw1.write(updateList.get(i) + "\n");
            }
            fw1.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteInformation() {
        // find the person
        File file = new File("information.txt");
        // if not found then add into new arraylist
        // override all txt file with the infor in arraylist
    }

    @Override
    public void searchInformation() {
        File file = new File("information.txt");
        System.out.println("Enter the id of the information you would like to search: ");
        int searchId = new Scanner(System.in).nextInt();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(",");
                int id = Integer.parseInt(dataLine[0]);
                information infor = new information(id, dataLine[1], dataLine[2], dataLine[3], dataLine[4],dataLine[5],dataLine[6],dataLine[7]);
                if (id == searchId) {
                    System.out.println("person number "+ id + " is " + infor);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
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
