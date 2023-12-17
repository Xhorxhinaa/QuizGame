import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuizGame {
    private ArrayList<String> words;
    private Scanner scanner;
    private Random random;
    private String selectedWord;
    private int wordLength;
    private char[] guessedLetters;
    private boolean[] letterGuessed;
    private int attempts;
    private final int maxAttempts = 6;

    public QuizGame() {
    	words  = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
        selectedWord = "";
        wordLength = 0;
        attempts = 0;
    }

    public static void main(String[] args) {
    	QuizGame game = new QuizGame();
        game.play();
    }

    public void play() {
        words = readTheWords("C:\\Users\\User\\Desktop\\midterm-project\\75fjateltmetebukurashqip.txt");

        if (words.isEmpty()) {
            System.out.println("Please check the file.");
            return;
        }

        while (true) {
            selectedWord = selectRandomWord(words, random);
            wordLength = selectedWord.length();
            guessedLetters = new char[wordLength];
            letterGuessed = new boolean[wordLength];
            attempts = 0;

            System.out.println("Welcome to the Word Guessing Game!");
            System.out.println("The word contains " + wordLength + " letters.");

            while (attempts < maxAttempts) {
                boolean wordGuessed = true;

                for (int i = 0; i < wordLength; i++) {
                    if (letterGuessed[i]) {
                        System.out.print(guessedLetters[i] + " ");
                    } else {
                        System.out.print("_ ");
                        wordGuessed = false;
                    }
                }

                if (wordGuessed) {
                    System.out.println("\nCongratulations! \nYou guessed the word correctly: " + selectedWord);
                    break;
                }

                System.out.print("\nType a letter: ");
                String guess = scanner.nextLine().trim().toLowerCase();

                if (guess.length() == 1) {
                    char letter = guess.charAt(0);
                    boolean found = false;

                    for (int i = 0; i < wordLength; i++) {
                        if (!letterGuessed[i] && selectedWord.charAt(i) == letter) {
                            guessedLetters[i] = letter;
                            letterGuessed[i] = true;
                            found = true;
                        }
                    }

                    if (!found) {
                        attempts++;
                        System.out.println("Sorry, '" + letter + "' is not in the word. \nAttempts left: " + (maxAttempts - attempts));
                    }
                } else {
                    if (guess.equals(selectedWord)) {
                        System.out.println("Congratulations! You guessed the word correctly: " + selectedWord);
                        break;
                    } else {
                        attempts++;
                        System.out.println("Incorrect.Try an other one :) \nAttempts left: " + (maxAttempts - attempts));
                    }
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry :(. \nThe correct word was: " + selectedWord);
            }

            if (!playAgain()) {
                System.out.println("Thank you for playing with my game !^_^\nGoodbye!");
                break;
            }
        }
        scanner.close();
    }

    public boolean playAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }

    public ArrayList<String> readTheWords(String filePath) {
        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    word = word.trim();
                    if (word.length() >= 4 && word.length() < 15 && word.matches("[a-zA-Z]+")) {
                        words.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }

    public String selectRandomWord(ArrayList<String> words, Random random) {
        return words.get(random.nextInt(words.size()));
    }
}
