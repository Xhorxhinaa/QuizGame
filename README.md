Game logic

Words from a file are being loaded,the readTheWords() method reads words from a specified file into the words ArrayList.
Following that, a word is chosen randomly from the loaded words.
The interesting part is that we must find the word while the system tracks guessed letters using the arrays (guessedLetters and letterGuessed) and notify the player whether a guess is correct or when the maximum number of attempts has been reached.
Finally, by using playAgain(), the player is given the opportunity to play again.

My project is a creative Java program that applies and expands on the knowledge learned in the first part of the Advanced Programming course.

I've included the following concepts in this project:

The concept of object-oriented programming (abstraction, encapsulation, classes, and objects). I did not, however, use the concepts of abstract classes, inheritance, or polymorphism.
Regarding generic collections: I used an arrey list.
File manipulation (by reading from the fail path) and exception handling
