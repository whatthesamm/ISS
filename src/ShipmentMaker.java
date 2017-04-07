/**
 * Created by sam on 4/6/17.
 */
public class ShipmentMaker implements Event {

    private Port port;
    private int genRate;

    public ShipmentMaker(Port portName, int genRate){
        port = portName;
        this.genRate = genRate;
    }

    public void run(){
        for (int i = 0; i < genRate; i++) { //Creates genRate number of packages per day
            int temp = (int) (Math.random() * 9); //Randomly number between 0-8
            int weight = (int) (Math.random() * 1000) + 1; //Weight of given package
            double creationTime = ShippingSim.agenda.getCurrentTime(); //Time created
            Port destination = ShippingSim.portList[(temp + port.getPortNum() + 1) % 10]; //
            Shipment tempShip = new Shipment(weight, creationTime, destination);
            port.shipmentList[temp].add(tempShip);
        }
        ShippingSim.agenda.add(this,1);
    }
}
