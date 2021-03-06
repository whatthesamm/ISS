//Written by maten009 and nguy2886
public class Port {
    private static int counter = 0;
    Queue[] shipmentList; //Each shipment list contains 9 queues. The first element is the next port regarding to port.getPortNum() and so on. Once it reaches the final port num, it loops from beginning
    private String name;
    private double[] location = new double[2]; //Stores co-ordinations of (x,y) in the array as {x, y}
    private int genRate;
    private int portNum;

    public Port(String name, int portX, int portY, int genRate) {
        this.name = name;
        this.location[0] = portX;
        this.location[1] = portY;
        this.genRate = genRate;

        portNum = counter++; //Assign portNum to port in order each port is added
        shipmentList = new Queue[9];
        for (int i = 0; i < 9; i++) {
            shipmentList[i] = new Queue();
        }
    }

    public int getOldestShipmentDest() { //Get the port with the shipment that has waited the longest
        int oldestIndex = -1;
        double oldestTime = -1;
        for (int i = 0; i < shipmentList.length; i++) {
            if (shipmentList[i].length() > 0) {
                Shipment temp = (Shipment) shipmentList[i].getFront().getData();
                if (oldestIndex == -1) {
                    oldestIndex = i;
                    oldestTime = temp.getCreationTime();
                } else if (oldestTime > temp.getCreationTime()) {
                    oldestIndex = i;
                    oldestTime = temp.getCreationTime();
                }
            }
        }
        return oldestIndex; //Returns the index of the ports location in the Queue list
    }

    public double[] getLocation() {
        return location;
    }

    public String getName() { return name; }

    public int getGenRate() { return genRate; }

    public int getPortNum() { return portNum; }
}

