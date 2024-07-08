import java.util.*;

class CustomQueue {
    private LinkedList<Integer> queue;

    public CustomQueue() {
        queue = new LinkedList<>();
    }

    public CustomQueue(List<Integer> initialData) {
        queue = new LinkedList<>(initialData);
    }

    public void enqueue(List<Integer> elements) {
        queue.addAll(elements);
    }

    public List<Integer> dequeue(int n) {
        List<Integer> dequeuedElements = new ArrayList<>();
        for (int i = 0; i < n && !queue.isEmpty(); i++) {
            dequeuedElements.add(queue.removeFirst());
        }
        return dequeuedElements;
    }

    public int length() {
        return queue.size();
    }

    public List<Integer> getData() {
        return new ArrayList<>(queue);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int initialLength = scanner.nextInt();
        List<Integer> initialElements = new ArrayList<>();
        for (int i = 0; i < initialLength; i++) {
            initialElements.add(scanner.nextInt());
        }
        scanner.nextLine(); // Consume the remaining newline

        CustomQueue queue = new CustomQueue(initialElements);

        List<Integer> dequeuedElements = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String[] operation = scanner.nextLine().split(" ");
            if (operation[0].equals("in")) {
                List<Integer> elementsToEnqueue = new ArrayList<>();
                for (int j = 1; j < operation.length; j++) {
                    elementsToEnqueue.add(Integer.parseInt(operation[j]));
                }
                queue.enqueue(elementsToEnqueue);
            } else if (operation[0].equals("out")) {
                int n = Integer.parseInt(operation[1]);
                dequeuedElements.addAll(queue.dequeue(n));
            }
        }

        int currentLength = queue.length();
        if (currentLength > 0) {
            StringBuilder currentData = new StringBuilder();
            for (int element : queue.getData()) {
                currentData.append(element).append(" ");
            }
            System.out.println("len = " + currentLength + ", data = " + currentData.toString().trim());
        } else {
            System.out.println("len = 0");
        }

        int dequeuedLength = dequeuedElements.size();
        if (dequeuedLength > 0) {
            StringBuilder dequeuedData = new StringBuilder();
            for (int element : dequeuedElements) {
                dequeuedData.append(element).append(" ");
            }
            System.out.println("len = " + dequeuedLength + ", data = " + dequeuedData.toString().trim());
        } else {
            System.out.println("len = 0");
        }

        scanner.close();
    }
}

