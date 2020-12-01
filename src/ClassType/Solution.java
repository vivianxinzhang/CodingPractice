package ClassType;

public class Solution {
    public static void main(String[] args) {
        // Instance zhao owns his bike
        People zhao = new People("Zhao");
        People.Bike zhaoBike = zhao.new Bike();
        zhaoBike.printName();

        // The User class owns shared bikes
        SharedBikeUser.Bike sharedBike1= new SharedBikeUser.Bike();
        sharedBike1.lock();
        SharedBikeUser.Bike sharedBike2= new SharedBikeUser.Bike();
        SharedBikeUser.Bike sharedBike3= new SharedBikeUser.Bike();
        SharedBikeUser.Bike sharedBike4= new SharedBikeUser.Bike();

        SharedBikeUser.Bike sharedBike5= new SharedBikeUser.Bike();

        SharedBikeUser anna = new SharedBikeUser("Anna");
    }
}