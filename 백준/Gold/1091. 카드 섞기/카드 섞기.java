import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCards = scanner.nextInt();
        int[] playerSequence = new int[numberOfCards];
        int[] shuffleSequence = new int[numberOfCards];

        for (int i = 0; i < numberOfCards; i++) {
            playerSequence[i] = scanner.nextInt();
        }

        for (int i = 0; i < numberOfCards; i++) {
            shuffleSequence[i] = scanner.nextInt();
        }

        CardShuffler shuffler = new CardShuffler(numberOfCards, playerSequence, shuffleSequence);

        shuffler.initializeCount();
        shuffler.draw();
        shuffler.shuffle();
        
        shuffler.printResult();
        scanner.close();
    }
}

class CardShuffler {
    private final int[] playerSequence;
    private final int[] shuffleSequence;

    private int numberOfShuffles;
    private final int numberOfCards;
    private final List<Card> cardDeck;

    public CardShuffler(int numberOfCards, int[] playerSequence, int[] shuffleSequence) {
        this.playerSequence = playerSequence;
        this.shuffleSequence = shuffleSequence;
        this.numberOfShuffles = -1;
        this.numberOfCards = numberOfCards;
        this.cardDeck = new ArrayList<>();
    }

//    public boolean isPossibleToShuffle() {
//        for (int i = 0; i < this.shuffleSequence.length; i++) {
//            if (this.shuffleSequence[i] == i && this.playerSequence[i] != this.shuffleSequence[i]%3) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void initializeCount() {
        this.numberOfShuffles = 0;
    }

    public void shuffle() {

        while (!isDone()) {
            changePosition();
            this.numberOfShuffles++;
            if (this.numberOfShuffles > 10000000) {
                this.numberOfShuffles = -1;
                break;
            }
        }

    }

    private void changePosition() {
        for (Card card : this.cardDeck) {
            card.changePosition(this.shuffleSequence[card.getCurrentPosition()]);
        }
    }

    public void draw() {
        for (int i = 0; i < this.numberOfCards; i++) {
            this.cardDeck.add(i, new Card(i, i % 3, i));
        }
    }

    private boolean isDone() {
        for (Card card : this.cardDeck) {
            if (this.playerSequence[card.getInitialPosition()] != card.getCurrentPosition() % 3) {
                return false;
            }
        }
        return true;
    }

    public int getNumberOfShuffles() {
        return numberOfShuffles;
    }

    public void printResult() {
        System.out.println(this.numberOfShuffles);
    }

}

class Card {
    private final int initialPosition;
    private int playerNumber;

    private int currentPosition;

    public Card(int initialPosition, int playerNumber, int currentPosition) {
        this.initialPosition = initialPosition;
        this.playerNumber = playerNumber;
        this.currentPosition = currentPosition;

    }

    public void changePosition(int position) {
        this.currentPosition = position;
    }


    public int getInitialPosition() {
        return initialPosition;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public String toString() {
        return "Card{" +
                "initialPosition=" + initialPosition +
                ", playerNumber=" + playerNumber +
                ", currentPosition=" + currentPosition +
                '}';
    }
}