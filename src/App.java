import java.io.IOException;

public class App {
    public static void main(String[] args) {
        InputOutputData io = new InputOutputData();
        boolean running = true;
        int option;


        while(running) {
            io.printOptions();
            option = io.getOption(7);
            switch (option) {
                case 1: io.option1(); break;
                case 2: io.option2(); break;
                case 3: io.option3(); break;
                case 4: io.option4(); break;
                case 5: io.option5(); break;
                case 6: running = false; break;
                default: break;
            }
            System.out.println("[Pritisni enter]");

            try {System.in.read();}
            catch (IOException e) {e.printStackTrace();}
        }
    }
}
