/**
 * Created by sam on 4/21/17.
 */
public class Collecter {
    public static void main(String[] args){
        double timesRun = 100;
        for (int i = 0; i < timesRun; i++){
            test();
        }
        System.out.println("Weight delivered: " + weightDelivered/timesRun);
        System.out.println("Moon deliveries: " + moonCounter/timesRun);
        System.out.println("Profit total: " + profit/timesRun);
        System.out.println("Avg days waited: " + (avgDayWaited/timesRun));
        System.out.println("Avg. profit: " + (avgProfit/timesRun));
        System.out.println("Avg. percentFull: " + (avgPercent/timesRun));
    }
    static int weightDelivered = 0;
    static int W = 0; //Maximum time a shipment spends waiting at a part
    static int P = 0; //Average profit per shipment
    static int shipped = 0; //How many was shipped
    static int moonCounter = 0;
    static long profit = 0;
    static double shipsDeparted = 0;
    static int daysWaited = 0;
    static double percentFull = 0;
    static double avgDayWaited = 0;
    static double avgProfit = 0;
    static double avgPercent = 0;

    public static void test(){
        ShippingSim.main(null);
        ShippingSim.agenda = new PQ();
        Stat.avgDayWait = Stat.daysWaited/Stat.shipsDeparted;
        Stat.avgProfit = Stat.profit/Stat.shipsDeparted;
        Stat.avgPercent = Stat.percentFull/Stat.shipsDeparted;
        avgPercent += Stat.avgPercent;
        avgDayWaited += Stat.avgDayWait;
        avgProfit += Stat.avgProfit;
        weightDelivered += Stat.weightDelivered;
        shipped += Stat.shipped;
        moonCounter += Stat.moonCounter;
        profit += Stat.profit;
        shipsDeparted += Stat.shipsDeparted;
        daysWaited += Stat.daysWaited;
        percentFull += Stat.percentFull;
    }
}
