/**
 * Created by sam on 4/21/17.
 */
public class Collecter {
    public static void main(String[] args){
        double timesRun = 100;
        for (int i = 0; i < timesRun; i++){
            test();
        }
        System.out.println("Weight delivered: " + (int)(weightDelivered/timesRun));
        System.out.println("Profit total: " + (int)(profit/timesRun));
        System.out.println("Avg days waited: " + ((daysWaited/timesRun)/(shipsDeparted/timesRun)));
        System.out.println("Avg. profit: " + (int)((profit/timesRun)/(shipsDeparted/timesRun)));
        System.out.println("Avg. percentFull: " + (((percentFull/timesRun)/(shipsDeparted/timesRun))));
    }
    static long weightDelivered = 0;
    static int W = 0; //Maximum time a shipment spends waiting at a part
    static int P = 0; //Average profit per shipment
    static int shipped = 0; //How many was shipped
    static int moonCounter = 0;
    static int profit = 0;
    static double shipsDeparted = 0;
    static int daysWaited = 0;
    static double percentFull = 0;

    public static void test(){
        ShippingSim.main(null);
        ShippingSim.agenda = new PQ();
        weightDelivered += Stat.weightDelivered;
        shipped += Stat.shipped;
        moonCounter += Stat.moonCounter;
        profit += Stat.profit;
        shipsDeparted += Stat.shipsDeparted;
        daysWaited += Stat.daysWaited;
        percentFull += Stat.percentFull;
    }
}
