// Name: B6-24
// Date: 08/28/19

import java.text.DecimalFormat;
public class SmartCard_Driver
{
    public static void main(String[] args)
    {
        Station downtown = new Station("Downtown", 1);
        Station center = new Station("Center City", 1);
        Station uptown = new Station("Uptown", 2);
        Station suburbia = new Station("Suburb", 4);

        SmartCard jimmy = new SmartCard(20.00);
        jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
        jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
        jimmy.disembark(uptown);				//Error:  did not board?!
        System.out.println();

        SmartCard susie = new SmartCard(1.00);
        susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
        susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
        System.out.println();

        SmartCard kim = new SmartCard(.25);
        kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
        System.out.println();

        SmartCard b = new SmartCard(10.00);
        b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
        b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
        System.out.println();

        SmartCard mc = new SmartCard(10.00);
        mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
        mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
        System.out.println();

        //Make more test cases.  What else needs to be tested?
        Station zone1Station = new Station("Zone 1", 1);
        Station zone4Station = new Station("Zone 4", 4);
        SmartCard johnDoe = new SmartCard(.49);

        System.out.printf("Current Balance is %s. Passenger Boarded: %s.%n", johnDoe.getBalance(), johnDoe.getIsOnBoard());
        johnDoe.board(zone1Station);
        johnDoe.addMoney(.01);
        johnDoe.board(zone1Station);
        System.out.printf("Current Balance is %s. Passenger Boarded: %s.%n", johnDoe.getBalance(), johnDoe.getIsOnBoard());
        johnDoe.board(zone4Station);
        System.out.printf("Current Station is %s.(Expected Zone 1)%n", johnDoe.getBoardedAt().getName());
        johnDoe.disembark(zone4Station);
        System.out.printf("Current Balance is %s. Passenger Boarded: %s.%n", johnDoe.getBalance(), johnDoe.getIsOnBoard());
        johnDoe.addMoney(5.0);
        johnDoe.disembark(zone4Station);
        johnDoe.disembark(zone1Station);
    }
}

//Note SmartCard is not denoted as public.  Why?
class SmartCard
{
    public final static DecimalFormat df = new DecimalFormat("$0.00");
    public final static double MIN_FARE = 0.5;
    /* enter your code below */

    private double balance;
    private Station stationBoarded;
    private boolean isBoarded;

    public SmartCard(double d) {
        balance = d;
        stationBoarded = null;
        isBoarded = false;
    }

    void addMoney(double d) {
        balance += d;
    }

    String getBalance() {
        return df.format(balance);
    }

    void board(Station s) {
        if (isBoarded) {
            System.out.println("Error: already boarded?!");
            return;
        }

        if (balance < .5) {
            System.out.println("Insufficient funds to board. Please add more money.");
            return;
        }

        stationBoarded = s;
        isBoarded = true;
        System.out.printf("Boarded at %s.  SmartCard has %s.%n", s.getName(), df.format(balance));
    }

    double cost(Station s) {
        return 0.50 + (0.75 * (Math.abs(s.getZone() - stationBoarded.getZone())));
    }

    void disembark(Station s) {
        if (!isBoarded) {
            System.out.println("Error: Did not board?!");
            return;
        }

        double cost = cost(s);
        if (cost > balance) {
            System.out.println("Insufficient funds to exit. Please add more money.");
            return;
        }

        isBoarded = false;
        balance -= cost;
        System.out.printf("From %s to %s costs %s.  SmartCard has %s%n", stationBoarded.getName(), s.getName(), df.format(cost), df.format(balance));
        stationBoarded = null;
    }
    
    boolean isBoarded() {
        return isBoarded;
`   }

    //the next 3 methods are for use ONLY by Grade-It
    //these accessor methods only return your private data
    //they do not make any changes to your data
    double getMoneyRemaining()
    {
        //enter your code here and replace the return with yours
        return balance;
    }

    Station getBoardedAt()
    {
        //enter your code here and replace the return with yours
        return stationBoarded;
    }

    boolean getIsOnBoard()
    {
        //enter your code here and replace the return with yours
        return isBoarded;
    }
}

//Note Station is not a public class.  Why?
class Station
{
    private int zone;
    private String name;

    public Station(String n, int z) {
        name = n;
        zone = z;
    }

    String getName() {
        return name;
    }

    int getZone() {
        return zone;
    }

    void setName(String n) {
        name = n;
    }

    void setZone(int z) {
        zone = z;
    }

    public String toString() {
        return String.format("Name: %s Zone: %d", name, zone);
    }
}

/*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!

 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.

 Insufficient funds to board. Please add more money.

 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50

 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25

 ************************************************/