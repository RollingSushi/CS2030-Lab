import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int recordCount = 0;
        recordCount = sc.nextInt();
        ArrayList<Query> allQueries = new ArrayList<Query>();
        Roster roster = new Roster("AY2020");

        //Filling up queries
        while (sc.hasNext()) {
            if (recordCount != 0) {
                String id = sc.next();
                String modCode = sc.next();
                String asName = sc.next();
                String grade = sc.next();

                if (roster.localMap.containsKey(id)) {
                    if (roster.get(id).orElseThrow().localMap.containsKey(modCode)) {
                        roster.get(id).map(x -> x.get(modCode).orElseThrow().put(
                            new Assessment(asName, grade)));
                    } else {
                        roster.get(id).map(x -> x.put(
                            new Module(modCode).put(
                                new Assessment(asName, grade))));
                    }

                } else {
                    roster.put(
                        new Student(id).put(
                            new Module(modCode).put(
                                new Assessment(asName, grade))));

                }
                recordCount--;
                
            } else {
                Query query = new Query(sc.next(), sc.next(), sc.next());
                allQueries.add(query);
            }  
        }

        sc.close();

        for (Query q: allQueries) {
            try {
                String grade = roster.getGrade(q.id, q.modCode, q.aName);
                System.out.println(grade);
            } catch (NoSuchRecordException e) {
                System.out.println("NoSuchRecordException: " + e.getMessage());
            }
        }

        

        
    }
}