package Day7;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LibraryWaitlistDemo {
    public static void main(String[] args) {
        LinkedHashSet<String> uniqueTitles = new LinkedHashSet<>();
        uniqueTitles.add("Java Basics");
        uniqueTitles.add("Clean Code");
        uniqueTitles.add("Java Basics");

        ArrayList<String> catalogue = new ArrayList<>(uniqueTitles);
        System.out.println("Catalogue: " + catalogue);

        ArrayDeque<String> waitlist = new ArrayDeque<>();
        waitlist.offer("Asha");
        waitlist.offer("Ben");
        System.out.println("Next on waitlist: " + waitlist.poll());
        System.out.println("Waitlist: " + waitlist);

        PriorityQueue<LibraryRequest> requests = new PriorityQueue<>(
                Comparator.comparing(LibraryRequest::isFaculty).reversed()
                        .thenComparing(LibraryRequest::getName));
        requests.offer(new LibraryRequest("Asha", false));
        requests.offer(new LibraryRequest("Dr. Lee", true));
        requests.offer(new LibraryRequest("Ben", false));
        System.out.println("Requests (faculty first):");
        while (!requests.isEmpty()) {
            System.out.println("- " + requests.poll());
        }

        ArrayList<String> loans = new ArrayList<>(List.of(
                "Clean Code", "OVERDUE: Java Basics", "SQL Guide"));
        Iterator<String> loanIterator = loans.iterator();
        while (loanIterator.hasNext()) {
            if (loanIterator.next().startsWith("OVERDUE:")) {
                loanIterator.remove();
            }
        }
        System.out.println("Loans after removing overdue items: " + loans);

        compareLists();
    }

    private static void compareLists() {
        int size = 3000;
        int operations = 1000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            arrayList.add(arrayList.size() / 2, i);
        }
        long arrayInsertTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            linkedList.add(linkedList.size() / 2, i);
        }
        long linkedInsertTime = System.nanoTime() - start;

        long arrayReadTime = timeIndexedReads(arrayList, operations * 5);
        long linkedReadTime = timeIndexedReads(linkedList, operations * 5);

        System.out.println("Timing comparison (nanoseconds; varies by machine):");
        System.out.println("Middle inserts - ArrayList: " + arrayInsertTime);
        System.out.println("Middle inserts - LinkedList: " + linkedInsertTime);
        System.out.println("get(i) reads - ArrayList: " + arrayReadTime);
        System.out.println("get(i) reads - LinkedList: " + linkedReadTime);
    }

    private static long timeIndexedReads(List<Integer> list, int operations) {
        long total = 0;
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            total += list.get((i * 37) % list.size());
        }
        long elapsed = System.nanoTime() - start;
        System.out.println("Read checksum: " + total);
        return elapsed;
    }

    private static class LibraryRequest {
        private final String name;
        private final boolean faculty;

        private LibraryRequest(String name, boolean faculty) {
            this.name = name;
            this.faculty = faculty;
        }

        private String getName() {
            return name;
        }

        private boolean isFaculty() {
            return faculty;
        }

        @Override
        public String toString() {
            return name + (faculty ? " (faculty)" : " (student)");
        }
    }
}