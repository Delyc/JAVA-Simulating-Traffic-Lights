//AUTHOR: Delyce


import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;   //class to schedule a task to be executed after specified time
import java.util.TimerTask;  //schedule task to run for specified number of time




public class traffic_light {
    public static void main(String[] args) {
        // an array to hold cars before they get in the queue
        ArrayList<String> generated_cars = new ArrayList<String>();

        //generating class until they get to 100
        for (int i =1; generated_cars.size() <100; i++ ){

            //convert  i into string to be concatenated with a string word below
            String To_String = Integer.toString(i);

            //conactination : every generated number be added to word car
            String carName = "car" + To_String;

            //add the car name which is conactination of generated car and the name car to defined array
            generated_cars.add(carName);
        }

         //shuffling array of generated cars to get out randomly
        Collections.shuffle(generated_cars); 

        //creating other two new arrays to hold cars in queue and cars after existing queue

        ArrayList<String> carsInQueue = new ArrayList<String>();
        ArrayList<String> exitedCars = new ArrayList<String>();



        System.out.println();
        System.out.println(">Movement Starts<");
        System.out.println();
        System.out.println(" ---- " + generated_cars.size() + "  : " + carsInQueue.size() + " cars waiting : " + exitedCars.size() + " cars exited");
        System.out.println();

        //while true to  execute codes until the set condition is met to stop it or when it becomes false
        while (true){

            //creating timer called adding_cars
            Timer adding_cars = new Timer();

            //set initial time and intervel of 1000 which is equivalent to 1s
            int initial = 0;
            int interval = 1000;
            adding_cars.schedule(new TimerTask() {
                //count
                int count = 0;

                @Override
                public void run() {
                    //if macimum number of 100 cars met we stop the count (adding_cars.cancel();)
                    if (generated_cars.size() == 0){
                        adding_cars.cancel();
                    } 

                    //otherwise we get  car at front of the array generated_array
                    else{
                        String F_elem = generated_cars.get(0);

                        //remove it
                        generated_cars.remove(0);

                        //add it to the cars in queue or waiting cars
                        carsInQueue.add(F_elem);
                    }
                    //increment the count
                    count ++;
                     
                    //until it gets to 100 
                    if (count == 100){

                        //then stop the count
                        adding_cars.cancel();
                    }
                }
                 

                //put in the time bound here for the program to use that particular time(skiping one second )
                
            }, initial, interval);

            System.out.println();


            //logic goes the same to the rest of the codes except a few lines that i will be commenting independently
            Timer red_light = new Timer();
            int initial_red = 0;
            int interval_red = 1000;
            red_light.schedule(new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    
                    System.out.println("RED : NO MOVEMENT! " + generated_cars.size() + " cars waiting : " + carsInQueue.size() + " cars in queue : " + exitedCars.size() + " cars exited");
                    count++;
                    

                    if (count == 20){
                        red_light.cancel();
                    }

                    
                }
                
            }, initial_red, interval_red);

            //for red cars should last fro 20 seconds which means we need thread.sleep with value 20000ms ehich is equvalent to 20s
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (carsInQueue.size() == 0) {
                System.out.println();
                System.out.println("No more cars");
                System.out.println();
                break;
            }

            System.out.println();
            Timer yellow = new Timer();
            int begin_yellow = 0;
            int timeInterval_yellow = 2000;  // move cars every 2 secs
            yellow.schedule(new TimerTask() {
                int count = 0;
                @Override
                public void run() {
                    //when cars in queue not equal to zero
                    if (carsInQueue.size() != 0) {
                        //we have to add to existed car the carin que ue but subtracting one cause one car gets out at time
                        exitedCars.add(carsInQueue.get(carsInQueue.size() - 1));  
                        //here we remove it from carInque t update it as well
                        carsInQueue.remove(carsInQueue.size() - 1);  
                        System.out.println("YELLOW : Go slower! " + carsInQueue.get(carsInQueue.size() - 1) + " moving : " + generated_cars.size() + " cars waiting : " + carsInQueue.size() + " cars in queue : " + exitedCars.size() + " exited");
                        count++;
                    }
                    else {

                        //so if there is no car then the timer shoudld stop
                        yellow.cancel();
                    }

                    //CONDITION: for yellow it has 10secs and car gets out each 2secs i.2 only 5 cars can get out in 10 secs
                    if (count >= 5){  

                        //if we get to 5 we stop the timer
                        yellow.cancel();
                    }
                }
            }, begin_yellow, timeInterval_yellow);
           
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //if no remaining car in queue program stops
            
            if (carsInQueue.size() == 0) {
                System.out.println();
                System.out.println("No more cars");
                System.out.println();
                break;
            }

             //logic is the same to red but instead it will realise number of cars equal to number of seconds because the interval between two cars is one second
            System.out.println();
            Timer green = new Timer();
            int begin_green = 0;
            int timeInterval_green = 1000; 
            green.schedule(new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (carsInQueue.size() > 0) {
                        exitedCars.add(carsInQueue.get(carsInQueue.size() - 1));  
                        if (carsInQueue.size() == 1) {  
                            System.out.println("GREEN : Go!" + carsInQueue.get(0) + " moving : " + generated_cars.size() + " cars waiting : 0 cars in queue : " + exitedCars.size() + " cars exited");
                            carsInQueue.remove(carsInQueue.size() - 1);  
                            green.cancel();
                        }
                        else {
                            carsInQueue.remove(carsInQueue.size() - 1);  
                            System.out.println("GREEN : Go! " + carsInQueue.get(carsInQueue.size() - 1) + " moving : " + generated_cars.size() + " cars waiting : " + carsInQueue.size() + " cars in queue : " + exitedCars.size() + " cars exited");
                        }
                        count ++;
                    }
                    else {
                        green.cancel();
                    }

                    if (count >= 30) {  
                        green.cancel();
                    }
                }
            }, begin_green, timeInterval_green);
           
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          
            if (carsInQueue.size() == 0) {
                System.out.println();
                System.out.println("No more cars");
                System.out.println();
                break;



        }

        
    }
    System.out.println(">Movement Stops - ALL cars out of the round about<");
        System.out.println();

    
}
}


