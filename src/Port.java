//Written by maten009 and nguy2886
public class Port {
    private double[] location = new double[2];
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

    public int getOldestShipmentDest(){ //Get the port with the shipment that has waited the longest
        int oldestIndex = -1;
        double oldestTime = -1;
        for (int i = 0; i < shipmentList.length; i++){
            Shipment temp = (Shipment)shipmentList[i].getLast();
            if (oldestIndex == -1) {
                oldestIndex = i;
                oldestTime = temp.getCreationTime();
            } else if (oldestTime > temp.getCreationTime()){
                oldestIndex = i;
                oldestTime = temp.getCreationTime();
            }
        }
        return oldestIndex;
    }

    public double[] getLocation() {
        return location;
    }
}

