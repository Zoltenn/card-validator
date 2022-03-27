import java.util.Scanner;

public class ValidateCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] cardNum = new int[16];

        //large loop to complete work
        do {
            //user input into array
            System.out.print("Enter a Credit Card Number: ");
            String input = sc.nextLine();
            for (int i = 0; i < input.length(); i++) {
                int temp = input.charAt(i);
                cardNum[i] = Character.getNumericValue(temp);
            }

            //work area
            
            //step 1
            int checkDigit = cardNum[15];
            int[] cardNumTwo = dropLast(cardNum);

            //step 2
            int[] cardNumThree = reverseArr(cardNumTwo);

            //step 3
            int[] cardNumFour = multiOdd(cardNumThree);

            //step 4
            int[] cardNumFive = subtract(cardNumFour);

            //step 5
            int cardNumSix = sum(cardNumFive);
            
            //step 6
            int cardNumSeven = validate(cardNumSix);

            //step 7
            int cardNumEight = lastDigit(checkDigit,cardNumSeven);
            
            //end work area

            //card number validity boolean
            boolean flag = false;
            if (cardNumEight == 0 || cardNumEight == 10) {
                flag = true;
            }

            //special check for mastercard
            String firstFiveStr = "";
            boolean masterCheck = false;

                //222100 - 272099 check
            for (int i = 0; i < 6; i++) {
                firstFiveStr += cardNum[i];
            }

            int firstFive = Integer.parseInt(firstFiveStr);
            if (firstFive >= 222100 && firstFive <= 272099) {
                masterCheck = true;
            }

            //if statement for -1 break and card type
            if (cardNum[0] == -1) {
                System.out.println("Have a great day!");
            }
            else if (masterCheck && flag) {
                System.out.println("This is a valid MasterCard credit card number.");
            }

                //51 - 55 check
            else if (((cardNum[0] == 5 && cardNum[1] == 1) || (cardNum[0] == 5 && cardNum[1] == 2) ||
            (cardNum[0] == 5 && cardNum[1] == 3) || (cardNum[0] == 5 && cardNum[1] == 4) ||
            (cardNum[0] == 5 && cardNum[1] == 5)) && flag) {
                System.out.println("This is a valid MasterCard credit card number.");
            }

            //visa check
            else if (cardNum[0] == 4 && flag) {
                System.out.println("This is a valid Visa credit card number.");
            }

            //american express check
            else if (((cardNum[0] == 3 && cardNum[1] == 4) || (cardNum[0] == 3 && cardNum[1] == 7)) && flag) {
                System.out.println("This is a valid American Express credit card number.");
            }

            else if (flag) {
                System.out.println("This is a valid credit card number.");
            } 

            else {
                System.out.println("This is an invalid credit card number.");
            }

            System.out.println();
            
        } while (cardNum[0] != -1);
        sc.close();
    }   //end of main method

    //print array method
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    //Step 1: drops the 16th
    public static int[] dropLast(int[] arr) {
        int[] arrTwo = new int[15];
        for (int i = 0; i < arr.length-1; i++) {
            arrTwo[i] = arr[i];
        }
        return arrTwo;
    }

    //Step 2: reverse array method
    public static int[] reverseArr(int[] arr) {
        int[] arrTwo = new int[15];
        for (int i = 0; i < arr.length; i++) {
            arrTwo[i] = arr[14-i];
        }
        return arrTwo;
    }

    //Step 3: multiply odd position by 2
    public static int[] multiOdd(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //test for even index positions
            if (i % 2 == 0) {
                arr[i] *= 2;
            }
        }
        return arr;
    }

    //Step 4: subtract 9 from all numbers greater than 9
    public static int[] subtract(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //test to subtract
            if(arr[i] > 9){
                arr[i] -= 9;
                }
        }
        return arr;
    }

    //Step 5: sum all numbers together
    public static int sum(int[] arr) {
        int sum = 0;
        for (int element: arr) {
        sum += element;	
        }
        return sum;
    }

    //Step 6: modulo 10 with total from step 5
    public static int validate(int summed) {
        int num = summed % 10;
        return num; 
    }

    //Step 7: add num to checkDigit
    public static int lastDigit(int lastAdd, int num) {
        int total = lastAdd + num;
        return total;
    }
}
