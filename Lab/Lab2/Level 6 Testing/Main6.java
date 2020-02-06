import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.util.ArrayList;

public class Main6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfCruises = sc.nextInt();
        Cruise[] allCruises = new Cruise[numOfCruises];

        for (int j = 0; j < numOfCruises; j++) {
            String identity = sc.next();
            if (identity.charAt(0) == 'B') {
                allCruises[j] = new BigCruise(identity, sc.nextInt(),
                                              sc.nextDouble(), sc.nextDouble());
            } else if (identity.charAt(0) == 'S') {
                allCruises[j] = new SmallCruise(identity, sc.nextInt());
            } else {
                allCruises[j] = new Cruise(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
        }

        ArrayList<Loader> allLoaders = new ArrayList<Loader>();
        
        /* Creates a loader if there isn't one. If there is one, check if it is busy
         * If it is not, check if it is recycled and create a special loader with
         * extra 60 minutes.
         */
        for (Cruise cruise: allCruises) {
            int count = cruise.getNumOfLoadersRequired();  
            int i = 0;
            int emptyLoader = 0;
            while (count != 0) {
                try {
                        allLoaders.get(i);
                    } catch (IndexOutOfBoundsException e) {
                        emptyLoader = 1;
                    }

                if (emptyLoader == 1) {
                    emptyLoader = 0;
                    // Added check for creating a recycled loader to add 60 minutes
                    if ((i + 1) % 3 == 0) { 
                        Loader newLoad = new Loader(i + 1, 0,
                                                    cruise.getServiceCompletionTime(),
                                                    cruise.toString(), 60);
                        allLoaders.add(newLoad);
                        allLoaders.set(i, allLoaders.get(i).serve(cruise));
                        count--;
                        System.out.println("Loader " + (i + 1) +
                                           " (recycled) " + "serving " +
                                           allLoaders.get(i).serving);
                    } else {
                        Loader newLoad = new Loader(i + 1);
                        allLoaders.add(newLoad);
                        allLoaders.set(i, allLoaders.get(i).serve(cruise));
                        count--;
                        System.out.println(allLoaders.get(i));
                    }
                } else {
                    if (allLoaders.get(i).serve(cruise) == null) {
                        i++;
                        continue;
                    } else {
                        // Added check for recycled loader to add 60 minutes
                        if ((i + 1) % 3 == 0) { 
                            Loader newLoad = new Loader(i + 1, 0,
                                                        cruise.getServiceCompletionTime(),
                                                        cruise.toString(), 60);
                            allLoaders.set(i, newLoad);
                            allLoaders.set(i, allLoaders.get(i).serve(cruise));
                            count--;
                            System.out.println("Loader " + (i + 1) +
                                               " (recycled) " + "serving " +
                                               allLoaders.get(i).serving); 
                        } else {
                            allLoaders.set(i, allLoaders.get(i).serve(cruise));
                            count--;
                            System.out.println(allLoaders.get(i));
                        }
                    }

                }
            }
        }
    }
}