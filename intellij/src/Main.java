import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Trips trips = new Trips();        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please input your start Latitude");
        double startLatitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Hello! Please input your start Longitude");
        double startLongitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("At what time did you start? Insert in Military Time!(HH:MM)");
        String startTime = scanner.next();
        scanner.nextLine();
        System.out.println("Hello! Please input your end Latitude");
        double endLatitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Hello! Please input your end Longitude");
        double endLongitude = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("At what time did you end? Insert in Military Time!(HH:MM)");
        String endTime = scanner.next();
        scanner.nextLine();
        double distance = trips.getDistance(startLatitude, startLongitude, endLatitude, endLongitude);
        double timeDifference = trips.calcTimeDifference(startTime, endTime);
        if(timeDifference>0){
            double averageSpeed = distance/timeDifference;
            System.out.println("Average speed: " + averageSpeed + " mph.");
        }
        else{
            System.out.println("Error: Time difference has to be greater than zero. No distance found");
        }
        scanner.close();
        }
    }