import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutputData {
    int searchOpt, n;
    Scanner scanner;
    ArrayList<Account> accountList, foundAccounts;

    public InputOutputData() {
        scanner = new Scanner(System.in);
        accountList = new ArrayList<Account>();
        n = 0;
    }

    public void printOptions() {
        clearScreen();
        System.out.println("1. Kreiraj račun");
        System.out.println("2. Prikaz stanja računa");
        System.out.println("3. Prikaz prometa po računa");
        System.out.println("4. Polog novca na račun");
        System.out.println("5. Podizanje novca s računa");
        System.out.println("6. Izlaz iz programa");
    }

    public int getOption(int to) {
        String option = "";
        boolean input = true;
        while(input) {
            option = scanner.next();
            if(isParsable(option) && Integer.parseInt(option) < to && Integer.parseInt(option) > 0) input = false;
            else System.out.println("Krivi unos");
        }
        return Integer.parseInt(option);
    }

    public Account searchAccounts() {
        foundAccounts = new ArrayList<Account>();
        String searchQuery;

        clearScreen();
        System.out.println("1. Pretraži po imenu");
        System.out.println("2. Pretraži po OIB-u");
        System.out.println("3. Pretraži po BA-u");
        
        searchOpt = getOption(4);
        System.out.print("Pretraži:\t");
        searchQuery = scanner.next();
        clearScreen();
        System.out.println("Opcija\tBroj računa\t\tOIB\t\tIme");
        if(searchOpt == 1) {
            for(int i = 0; i<accountList.size(); i++) {
                if(accountList.get(i).name.equals(searchQuery)){
                    System.out.println(Integer.toString(i+1) + accountList.get(i).basicData());
                    foundAccounts.add(accountList.get(i));
                }
            }
        }else if(searchOpt == 2) {
            for(int i = 0; i<accountList.size(); i++) {
                if(accountList.get(i).oib.equals(searchQuery)){
                    System.out.println(Integer.toString(i+1) + accountList.get(i).basicData());
                    foundAccounts.add(accountList.get(i));
                }
            }
        }else if(searchOpt == 3) {
            for(int i = 0; i<accountList.size(); i++) {
                if(accountList.get(i).accountNum.equals(searchQuery)){
                    System.out.println(Integer.toString(i+1) + accountList.get(i).basicData());
                    foundAccounts.add(accountList.get(i));
                }
            }
        }
        foundAccounts.add(null);
        System.out.println(Integer.toString(foundAccounts.size()) + " Izlaz");
        searchOpt = getOption(foundAccounts.size()+1);
        clearScreen();


        return foundAccounts.get(searchOpt-1);
    }


    public void option1() {
        String in = "";

        clearScreen();
        System.out.println("#############################################");
        System.out.println("#               Izrada računa               #");
        System.out.println("#############################################\n");
        accountList.add(new Account(n));
        
        System.out.print("Upiši ime:\t\t\t"); accountList.get(accountList.size()-1).name = scanner.next();
        System.out.print("Upiši ime tvrtke:\t\t"); accountList.get(accountList.size()-1).companyName = scanner.next();
        System.out.print("Upiši adresu:\t\t\t"); accountList.get(accountList.size()-1).address = scanner.next();
        while(in.length() != 11 || !isParsable(in)) {System.out.print("Upiši OIB:\t\t\t"); in = scanner.next();}
        accountList.get(accountList.size()-1).oib = in; in = "";
        while(!isParsable(in)) {System.out.print("Upiši poštanski broj:\t\t"); in = scanner.next();}
        accountList.get(accountList.size()-1).postCode = in;
        System.out.print("Upiši grad:\t\t\t"); accountList.get(accountList.size()-1).city = scanner.next();
        while(!(in.equalsIgnoreCase("eur") || in.equalsIgnoreCase("hrk"))) {System.out.print("Upiši željenu valutu [eur/hrk]:\t"); in = scanner.next();}
        accountList.get(accountList.size()-1).currency = in;
        System.out.print("Upiši iznos računa:\t\t"); accountList.get(accountList.size()-1).balance = scanner.nextDouble();
        n++;
    }

    public void option2() {
        Account tempAccount = searchAccounts();
        if(tempAccount != null) tempAccount.printAccountDetails();
    }

    public void option3() {
        Account tempAccount = searchAccounts();
        if(tempAccount != null) tempAccount.printTraffic();
    }

    public void option4() {
        Account tempAccount = searchAccounts();
        if(tempAccount != null) tempAccount.deposit(scanner);
    }

    public void option5() {
        Account tempAccount = searchAccounts();
        if(tempAccount != null) tempAccount.withdraw(scanner);
    }


    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else System.out.println("\033[H\033[2J");
        }catch (IOException | InterruptedException exception) {}
    }

    public boolean isParsable(String in) {
        try {Long.parseLong(in);}
        catch (NumberFormatException exception) {return false;}
        return true;
    }

}
