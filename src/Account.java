import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Account {
    String companyName, name, oib, currency, accountNum, address, postCode, city;
    double balance;
    ArrayList<String> traffic;

    public Account(int num) {
        traffic = new ArrayList<String>();

        String str = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        int n = 5-Integer.toString(num).length();

        accountNum = "BA-" + str + "-";
        for(int i = 0; i<n; i++) accountNum += "0";
        accountNum += Integer.toString(num);
    }

    public String basicData() {
        return "\t" + accountNum + "\t" + oib + "\t" + name;
    }

    public void printAccountDetails() {
        System.out.println("#############################################");
        System.out.println("#                   Račun                   #");
        System.out.println("#############################################\n");
        System.out.println("Ime:             " + name);
        System.out.println("Ime trvrtke:     " + companyName);
        System.out.println("Oib:             " + oib);
        System.out.println("Valuta:          " + currency);
        System.out.println("Broj računa:     " + accountNum);
        System.out.println("Adresa:          " + address);
        System.out.println("Poštanski broj:  " + postCode);
        System.out.println("Grad:            " + city);
        System.out.println("Količina novca:  " + balance);
    }

    public void deposit(Scanner scanner) {
        double n;
        String str = new SimpleDateFormat("dd-MM-yyyy (hh:mm:ss)").format(Calendar.getInstance().getTime());
        System.out.print("Količina:\t");
        n = scanner.nextDouble();
        balance += n;
        traffic.add("Položen novac: +" + n + "\t(" + balance + ")\t" + str);
    }

    public void withdraw(Scanner scanner) {
        double n;
        String str = new SimpleDateFormat("dd-MM-yyyy (hh:mm:ss)").format(Calendar.getInstance().getTime());
        System.out.print("Količina:\t");
        n = scanner.nextDouble();
        balance -= n;
        traffic.add("Podignut novac: -" + n + "\t(" + balance + ")\t" + str);
    }

    public void printTraffic() {
        System.out.println("#############################################");
        System.out.println("#                  Promet                   #");
        System.out.println("#############################################\n");

        for(int i = 0; i<traffic.size(); i++) {
            System.out.println(traffic.get(i));
        }
    }
}
