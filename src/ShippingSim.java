//Written by maten009 and nguy2886
public class ShippingSim {
    //Agenda for storing our events in when we run main()
    static PQ agenda = new PQ();

    //A list of ports, so we can easily access it
    static Port[] portList = { //Port(name, x, y, genRate)
            new Port("Minneapolis", 0, 0, 50),
            new Port("Saint Paul", 0, 10, 50),
            new Port("Antarctica", 0, -6000, 10),
            new Port("Japan", 4000, 4000, 100),
            new Port("Korea", 6000, 5000, 50),
            new Port("China", 5000, 6000, 1000),
            new Port("Moon", 0, 1000000, 0), //********We need to figure out how to make this only have a .001 chance or 0.1% chance being picked as the destination*******
            new Port("Panama", 1000, 3000, 50),
            new Port("Hawaii", 2000, 2000, 50),
            new Port("Pirate Town", 3000, 3000, 100)
    };

    //A list of vessels, so we can easily access it
    static Vessel[] vesselList = { //Vessel(name, capacity, speed, cost)
            new Vessel("Canoe", 1000, 10, 1),
            new Vessel("Yacht", 2000, 60, 2000),
            new Vessel("Galleon", 15000, 15, 100),
            new Vessel("Barge", 1000000, 10, 1000),
            new Vessel("Freighter", 2000000, 5, 1000),
            new Vessel("Airplane", 50000, 850, 10000),
            new Vessel("Carrier Pigeon Team", 1000, 10, 0),
            new Vessel("Rocket", 10000, 2000, 100000)
    };

    //Variables
    static double c = .9;
    static int w = 5;
    static int shipNum = 4;

    //This will run our international shipping simulation according to 10,000 seconds. We can change the time if we need to for other statistics.
    public static void main(String[] args) {
        //Instantiate VesselEvents
        agenda.add(new VesselEvent(portList[0],vesselList[shipNum],null),2400);

        //agenda.add(new VesselEvent(portList[4], vesselList[0]),20);
        while (agenda.getCurrentTime() <= 10000000) { //This will loop until all of the events went for a duration of at least 10,000 or whatever else we set the time to
            agenda.remove().run();
        }

        /*
        //Use this to look at package production
        for (int i = 0; i < portList.length; i++){ //i = current port
            Port temp = portList[i];
            String output = "";
            for (int p = 0; p < temp.shipmentList.length; p++){ //p = destination port
                int count = temp.shipmentList[p].length(); //Returns the number of packages
                output += portList[i].getName() + " with " + count + " packages going to " + portList[(i+p+1)%10].getName() + "\n";
            }
            System.out.println(output +  "\n----------------------------------\n");
        }
        */
    }
}
