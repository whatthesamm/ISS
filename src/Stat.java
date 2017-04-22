//Written by maten009 and nguy2886
public class Stat {
    //Variables
    static int weightDelivered = 0;
    static int W = 0; //Maximum time a shipment spends waiting at a part
    static int P = 0; //Average profit per shipment
    static int shipped = 0; //How many was shipped
    static int moonCounter = 0;
    static int profit = 0;
    static double shipsDeparted = 0;
    static int daysWaited = 0;
    static double percentFull = 0;

    static void restart(){
        weightDelivered = 0;
        W = 0; //Maximum time a shipment spends waiting at a part
        P = 0; //Average profit per shipment
        shipped = 0; //How many was shipped
        moonCounter = 0;
        profit = 0;
        shipsDeparted = 0;
        daysWaited = 0;
        percentFull = 0;
    }

}
