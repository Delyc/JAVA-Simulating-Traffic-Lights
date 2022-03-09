//AUTHOR: Delyce

//importing necessary libraries to use later on in the codes
import java.util.ArrayList; //importing arraylist to be used to store data (resizable and allow us to add data)
import java.util.Collections; // helping us to store and manipulate the group of data
import java.util.HashMap; // help us in indexing and move to specific values

import java.util.Map; // helping us to associate a key with a value
import java.util.Random; //generating random values
import java.util.Scanner; //gettin user inputs

public class quizProgram {

    public static void main(String[] args) {

        // displaying welcome message and instruction

        System.out.println("\n =========== WELCOME TO THE TRIVIA GAME ============\n");

        System.out.println("""
                Welcome once again, enjoy the game as well as lear new things in java about Random numbers\s

                """);

        System.out.println("""
                INSTRUCTIONS: 1) Read the question\s
                              2) Choose the answer from the given options
                              3) choose the best option by writing a corresponding letter
                              4) all answers in Small letters except where specified
                              5) Hint enter and check the result

                """);

        // creating an array to hold all questions together
        String[] qns = new String[] {
                "Which class is used to generate random number?",
                "Which method is used to generate boolean random values in java?",
                "What is the return type of Math.random() method?",
                "How many bits are used for generating random numbers",
                "What is the range of numbers returned by Math.random() method?."

        };

        // creating array to hold all answers together
        String[] qnsAnswers = new String[] {
                "java.util.Random",
                "nextBoolean()",
                "Double",
                "48",
                "0.0 to 1.0"

        };

        // creating a loop that get executed through out the whole program unless a
        // specific condition is met

        while (true) {
            ArrayList<Integer> qn_list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                qn_list.add(i);
            }

            // for our question to be generated in a random order we have to shuffel
            // questions array
            // so that the user wont get all questions in the same order
            Collections.shuffle(qn_list);

            // answers needs to be randomly generated as they keep changing their order
            int[] rand_ans = new int[5];
            for (int i = 0; i < 5; i++) {
                rand_ans[i] = qn_list.get(i);
            }

            // initializing random rand
            Random rand = new Random();
            int rand_In = rand.nextInt(5);
            int num = rand_ans[rand_In];

            // creating a list holding options of all answers
            String[] option = new String[5];
            for (int i = 0; i < 5; i++) {
                option[i] = qnsAnswers[rand_ans[i]];
            }

            // pairing a value with its accessing key using map
            final Map<String, String> map_val = new HashMap<>();
            map_val.put("a", option[0]);
            map_val.put("b", option[1]);
            map_val.put("c", option[2]);
            map_val.put("d", option[3]);
            map_val.put("e", option[4]);

            System.out.println("\nQuestion: " + qns[num]);
            System.out.printf("""

                    Choose a letter corresponding to the correct answer:\s
                            A. %s
                            B. %s
                            C. %s
                            D. %s
                            E. %s""", map_val.get("a"), map_val.get("b"), map_val.get("c"),
                    map_val.get("d"), map_val.get("e"));

            long startTime = System.currentTimeMillis() / 1000; // currentTimeMills returns time in millisecond so
                                                                // divide by 1000 to get in seconds
            System.out.print("\n\nAnswer: ");
            Scanner scanner = new Scanner(System.in);
            // convert user answers into lower cases
            String user_choice = scanner.nextLine().strip();

            // time it took the user to answer is the start time we initialized subtracted
            // by the run time or the time taken throughout the whole process of answering
            long runTime = System.currentTimeMillis() / 1000 - startTime;

            // try catch to catch unexpected results that might cause some errors
            try {

                // if user choice which is the user answer matches the guestion answer it is
                // correct otherwise false
                if (map_val.get(user_choice).equals(qnsAnswers[num])) {
                    System.out.println("\nCorrect!");
                    System.out.println("Time taken to answer: " + runTime + " seconds");

                } else {
                    System.out.println("\nWrong!");
                    System.out.println("Time taken to answer: " + runTime + " seconds\n");

                }
                // Catching an error by using NullPointerException as an exception when a
                // variable is tried to be accessed and points to null
            } catch (NullPointerException error) {
                System.out.println("\nWrong!");

            }

            // asking user if they want to play again
            Scanner sc = new Scanner(System.in);
            System.out.print("\nDo you want to play again? YES/NO in capitals ");
            String play_choice = sc.nextLine();
            String link = "https://www.sanfoundry.com/java-questions-answers-random-number/";

            // either type yes to continue or no
            if (play_choice.equals("YES")) {
                continue;
            } else {
                System.out.println("\n Thank you for playing, see you!. find more interesting questions at : " + link);
                break;
            }

        }

    }
}
