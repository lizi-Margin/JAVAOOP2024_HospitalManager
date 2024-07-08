import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Thread seller1 = new Thread(new TicketSeller(1));
        Thread seller2 = new Thread(new TicketSeller(2));
        Thread seller3 = new Thread(new TicketSeller(3));

        seller1.start();
        seller2.start();
        seller3.start();
    }
}

class Ticket {
    int row;
    int number;

    Ticket(int row, int number) {
        this.row = row;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Row " + row + " Seat " + number;
    }
}

class TicketOrder {
    List<Ticket> tickets;

    TicketOrder(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Order: " + tickets;
    }
}

class TicketSeller implements Runnable {
    private static final int ROWS = 10;
    private static final int SEATS_PER_ROW = 10;
    private static final int TOTAL_SEATS = ROWS * SEATS_PER_ROW;
    private static final boolean[][] seats = new boolean[ROWS][SEATS_PER_ROW];
    private static int remainingSeats = TOTAL_SEATS;
    private static final Lock lock = new ReentrantLock();
    private final int sellerId;

    public TicketSeller(int sellerId) {
        this.sellerId = sellerId;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            int numberOfTickets = random.nextInt(10) + 1;
            TicketOrder order = generateOrder(numberOfTickets);
            if (order == null) {
                System.out.println("Seller " + sellerId + ": No more tickets available.");
                break;
            }
            printOrder(order);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private TicketOrder generateOrder(int numberOfTickets) {
        lock.lock();
        try {
            if (remainingSeats < numberOfTickets) {
                return null;
            }

            List<Ticket> tickets = new ArrayList<>();
            outer: for (int row = 0; row < ROWS; row++) {
                for (int seat = 0; seat < SEATS_PER_ROW; seat++) {
                    if (!seats[row][seat] && canAllocate(row, seat, numberOfTickets)) {
                        for (int i = 0; i < numberOfTickets; i++) {
                            seats[row][seat + i] = true;
                            tickets.add(new Ticket(row + 1, seat + 1 + i));
                        }
                        remainingSeats -= numberOfTickets;
                        break outer;
                    }
                }
            }

            return new TicketOrder(tickets);
        } finally {
            lock.unlock();
        }
    }

    private boolean canAllocate(int row, int startSeat, int numberOfTickets) {
        if (startSeat + numberOfTickets > SEATS_PER_ROW) {
            return false;
        }
        for (int i = 0; i < numberOfTickets; i++) {
            if (seats[row][startSeat + i]) {
                return false;
            }
        }
        return true;
    }

    private void printOrder(TicketOrder order) {
        System.out.println("Seller " + sellerId + " processed " + order);
    }
}


