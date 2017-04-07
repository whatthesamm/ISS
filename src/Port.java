/**
 * Created by sam on 4/6/17.
 */
public class Port {
    private int[] location = new int[2];
    private String name;
    Q2[] shipmentList; //Each shipment list contains 9 queues. The first element is the next port regarding to port.getPortNum() and so on. Once it reaches the final port num, it loops from beginning
    private static int counter = 0;

    public int getPortNum() {
        return portNum;
    }

    private int portNum;

    public Port(int portX, int portY, String name, int genRate){
         this.location[0] = portX;
         this.location[1] = portY;
         this.name = name;
         if (!name.equals("Moon")) ShippingSim.agenda.add(new ShipmentMaker(this,genRate), 0); //Instantiate instance of shipmentMaker for each port (except moon)
         portNum = counter++; //Assign portNum to port in order each port is added
         shipmentList = new Q2[9];
         for (int i = 0; i < 9; i++){
             shipmentList[i] = new Q2();
         }
    }
}
