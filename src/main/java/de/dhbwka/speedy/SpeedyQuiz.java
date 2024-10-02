package de.dhbwka.speedy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Speedy Quiz
 */
public class SpeedyQuiz {

	/**
	 * Main method, entry point of application
	 *
	 * @param args CLI arguments
	 */
	public static void main(String[] args) {
		// Set cross-platform LAF to get colors for sure to work on macOS
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}

		List<Question> questionPool = loadQuestions();

		try {
			Game game = new Game(questionPool, 10);

			//game.registerClient( new GameTerm("Mia", game) );
			//game.registerClient( new GameTerm("Ben", game) );

			game.startGame();
		} catch (GameException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Game Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Load questions
	 *
	 * @return questions to load
	 */
	public static List<Question> loadQuestions(){
		List<Question> questions = new ArrayList<>();

		// replace this code
		// Generates 15 dummy questions, the first answer is always correct
		for (int i = 1; i <= 15; i++) {
			String line = String.format("Generated question %s;Answer 1;Answer 2;Answer 3;Answer 4;0", i);
			Question q = parseQuestion(line);
			if ( q != null ){
				questions.add(q);
			}
		}
		// end replace this code

		return questions;
	}

	/**
	 * Parse a question from input line
	 *
	 * @param line line to parse
	 * @return created question instance
	 */
	public static Question parseQuestion( String line ){
		try {
			String[] parts = line.split(";");
				if ( parts.length == 6 ){
				String[] answers = new String[4];
				System.arraycopy(parts, 1, answers, 0, 4);
				return new Question(parts[0], answers, Integer.parseInt(parts[5]));
			}
		} catch (Exception e) {
		}
		return null;
	}

}
