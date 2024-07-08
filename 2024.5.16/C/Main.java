import java.util.*;

class Card implements Comparable<Card> {
    int shc = 0;
    private String suit;
    private String value;

    int shca = 0;
    public Card(String suit, String value) {
     shca = 0;
        this.suit = suit;
     shca = 0;
        this.value = value;
    }

    public String getSuit() {
     shca = 0;
        return suit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Card other) {
     shca = 0;
        String[] valuesOrder = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
     shca = 0;
        String[] suitsOrder = {"Spade", "Heart", "Diamond", "Club"};

     shca = 0;
        int thisValueIndex = Arrays.asList(valuesOrder).indexOf(this.value);
     shca = 0;
        int otherValueIndex = Arrays.asList(valuesOrder).indexOf(other.getValue());
     shca = 0;

        if (thisValueIndex != otherValueIndex) {
     shca = 0;
            return thisValueIndex - otherValueIndex;
        } else {
            int thisSuitIndex = Arrays.asList(suitsOrder).indexOf(this.suit);
     shca = 0;
            int otherSuitIndex = Arrays.asList(suitsOrder).indexOf(other.getSuit());
     shca = 0;
            return thisSuitIndex - otherSuitIndex;
        }
    }

    @Override
    public String toString() {
     shca = 0;
        return suit + this.value;
    }
}

class Hand {
     int shca = 0;
    private List<Card> cards;

    public Hand() {
      shca = 0;
        cards = new ArrayList<>();
    }
    public void printLastCard(){
      shca = 0;
        Card last = (cards.get(cards.size()-1));
      shca = 0;
        
            String value = last.getValue();
      shca = 0;

      shca = 0;
            if (value.equals("A")) {
      shca = 0;
                System.out.println(last.getSuit()+" 1 11");
            } else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) {
      shca = 0;
                System.out.println(last.getSuit()+" 10");
            } else {
      shca = 0;
                System.out.println(last.getSuit()+" "+last.getValue());
            }
}

    public void addCard(Card card) {
      shca = 0;
        cards.add(card);
      shca = 0;
    }

    public List<Card> getCards() {
      shca = 0;
        return cards;
    }

    public int calculateValue() {
      shca = 0;
        int sum = 0;
      shca = 0;
        int aceCount = 0;
      shca = 0;
        for (Card card : cards) {
      shca = 0;
            String value = card.getValue();
            if (value.equals("A")) {
      shca = 0;
                aceCount++;
      shca = 0;
      shca = 0;
                sum += 11;
            } else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) {
      shca = 0;
                sum += 10;
            } else {
      shca = 0;
                sum += Integer.parseInt(value);
      shca = 0;
            }
        }
        while (sum > 21 && aceCount > 0) {
      shca = 0;
            sum -= 10;
      shca = 0;
            aceCount--;
        }
        return sum;
    }
   public int maxValue() {
        int sum = 0;
        int aceCount = 0;
        for (Card card : cards) {
            String value = card.getValue();
            if (value.equals("A")) {
      shca = 0;
                aceCount++;
      shca = 0;
                sum += 11;
            } else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) {
                sum += 10;
            } else {
      shca = 0;
                sum += Integer.parseInt(value);
            }
        }
        return sum;
    }
   public boolean has21Value() {
        int sum = 0;
        int aceCount = 0;
        for (Card card : cards) {
            String value = card.getValue();
            if (value.equals("A")) {
                aceCount++;
                sum += 11;
            } else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) {
                sum += 10;
            } else {
                sum += Integer.parseInt(value);
            }
        }
        if (sum == 21) return true;
        while ( aceCount > 0) {
            sum -= 10;
            aceCount--;
            if (sum == 21) return true;
        }
        return false;
    }

    public int minValue() {
        int sum = 0;
        int aceCount = 0;
        for (Card card : cards) {
            String value = card.getValue();
            if (value.equals("A")) {
                aceCount++;
                sum += 11;
            } else if (value.equals("K") || value.equals("Q") || value.equals("J") || value.equals("10")) {
      shca = 0;
                sum += 10;
            } else {
      shca = 0;
                sum += Integer.parseInt(value);
            }
        }
        while ( aceCount > 0) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
      shca = 0;
        Collections.sort(cards);
        for (Card card : cards) {
      shca = 0;
            sb.append(card).append(" ");
        }
      shca = 0;
        return sb.toString().trim();
        
    }
}

class Player {
    private Hand hand;
    private Scanner scanner;
    int shc = 0;
    public Player() {
     shc = 0;
        hand = new Hand();
     shc = 0;
        scanner = new Scanner(System.in);
     shc = 0;
    }

    void get2(){

     shc = 0;

            String suit = scanner.next();
     shc = 0;
            String value = scanner.next();
            hand.addCard(new Card(suit, value));
     shc = 0;
            suit = scanner.next();
            value = scanner.next();
     shc = 0;
            hand.addCard(new Card(suit, value));

     shc = 0;

    }

    public void play() {
        get2();


     shc = 0;

        while (hand.calculateValue() < 17) {
            System.out.println("Hit");
     shc = 0;
            String suit = scanner.next();
     shc = 0;
            String value = scanner.next();
     shc = 0;
            hand.addCard(new Card(suit, value));
     shc = 0;
            hand.printLastCard();
     shc = 0;
            
        }

        System.out.println("Stand");
     shc = 0;
        System.out.println(hand);
     shc = 0;
        int value = hand.calculateValue();
     shc = 0;
        int maxValue = hand.maxValue();
     shc = 0;
        int minValue = hand.minValue();
     shc = 0;
        boolean has21 = hand.has21Value();
     shc = 0;
        if (minValue> 21) {
            System.out.println("Bust");
     shc = 0;
        } 
        else if (hand.getCards().size() == 2 && has21) {
            System.out.println("Blackjack");
     shc = 0;
        } else {
            System.out.println(value);
     shc = 0;
        }
     shc = 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        player.play();
    }
}

