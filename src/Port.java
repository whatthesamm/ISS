//Written by maten009 and nguy2886
public class Port {
    private String name;
    private double[] location = new double[2]; //Stores co-ordinations of (x,y) in the array as {x, y}
    private int genRate;
    Q2[] shipmentList; //Each shipment list contains 9 queues. The first element is the next port regarding to port.getPortNum() and so on. Once it reaches the final port num, it loops from beginning
    private static int counter = 0;

    public int getPortNum() {
        return portNum;
    }

    private int portNum;

    public Port(String name, int portX, int portY, int genRate) {
        this.name = name;
        this.location[0] = portX;
        this.location[1] = portY;
        this.genRate = genRate;
        if (!name.equals("Moon"))
            ShippingSim.agenda.add(new ShipmentMaker(this, genRate), 0); //Instantiate instance of shipmentMaker for each port (except moon)
        portNum = counter++; //Assign portNum to port in order each port is added
        shipmentList = new Q2[9];
        for (int i = 0; i < 9; i++) {
            shipmentList[i] = new Q2();
        }
    }

    public int getOldestShipmentDest() { //Get the port with the shipment that has waited the longest
        int oldestIndex = -1;
        double oldestTime = -1;
        for (int i = 0; i < shipmentList.length; i++) {
            if (shipmentList[i].length() > 0) {
                Shipment temp = (Shipment) shipmentList[i].getFirst();
                if (oldestIndex == -1) {
                    oldestIndex = i;
                    oldestTime = temp.getCreationTime();
                } else if (oldestTime > temp.getCreationTime()) {
                    oldestIndex = i;
                    oldestTime = temp.getCreationTime();
                }
            }
        }
        return oldestIndex;
    }

    public double[] getLocation() {
        return location;
    }
    public String getName() { return name; }
}

