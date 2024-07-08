import java.util.*;

class CustomStack {
    private List<Integer> stack;

    public CustomStack() {
        stack = new ArrayList<>();
    }

    public CustomStack(List<Integer> initialData) {
        stack = new ArrayList<>(initialData);
    }

    public void push(List<Integer> elements) {
        stack.addAll(elements);
    }

    public List<Integer> pop(int n) {
        List<Integer> stack_;
        List<Integer> poppedElements = new ArrayList<>();
        if (n >= stack.size()) {
            stack_ = new ArrayList<Integer>(stack);
            n = stack.size();
            stack.clear();
        } else {
            stack_ = stack;
        }

            for (int i = 0; i < n; i++) {
                poppedElements.add(stack_.remove(stack_.size() - 1));
            }
        return poppedElements;
    }

    public int length() {
        return stack.size();
    }

    public List<Integer> getData() {
        return stack;
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

        CustomStack stack = new CustomStack(initialElements);

        List<Integer> poppedElements = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String[] operation = scanner.nextLine().split(" ");
            if (operation[0].equals("push")) {
                List<Integer> elementsToPush = new ArrayList<>();
                for (int j = 1; j < operation.length; j++) {
                    elementsToPush.add(Integer.parseInt(operation[j]));
                }
                stack.push(elementsToPush);
            } else if (operation[0].equals("pop")) {
                int n = Integer.parseInt(operation[1]);
                poppedElements.addAll(stack.pop(n));
            }
        }

        int currentLength = stack.length();
        if (currentLength > 0) {
            StringBuilder currentData = new StringBuilder();
            for (int element : stack.getData()) {
                currentData.append(element).append(" ");
            }
            System.out.println("len = " + currentLength + ", data = " + currentData.toString().trim());
        } else {
            System.out.println("len = 0");
        }

        int poppedLength = poppedElements.size();
        if (poppedLength > 0) {
            StringBuilder poppedData = new StringBuilder();
            for (int element : poppedElements) {
                poppedData.append(element).append(" ");
            }
            System.out.println("len = " + poppedLength + ", data = " + poppedData.toString().trim());
        } else {
            System.out.println("len = 0");
        }

        scanner.close();
    }
}

