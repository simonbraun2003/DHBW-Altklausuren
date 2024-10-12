package de.dhbwka.coronawarn.classes;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.*;

public class CoronaWarn {

	/**
	 * Application entry point for CoronaWarn
	 *
	 * @param args command line arguments, not used here
	 */
	public static void main(String[] args) {
		try {
			// Only necessary on MacOS to prevent color issues with standard look and feel.
			// Redundant on other operation systems - they use this look and feel by default.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		}
		catch ( final Exception e ) {
		}

		JPhone phone1 = new JPhone("0123-4567", "Markus");
		JPhone phone2 = new JPhone("9876-5432", "Angela");
		JPhone phone3 = new JPhone("4711-0815", "Olaf");

		CoronaWarnTerm client1 = new CoronaWarnTerm(phone1);
		CoronaWarnTerm client2 = new CoronaWarnTerm(phone2);
		CoronaWarnTerm client3 = new CoronaWarnTerm(phone3);

		CoronaWarnAPI.registerClients(client1,client2,client3);
	}


	/**
	 * Clear token store for phone
	 *
	 * @param phone phone to clear store for
	 */
	public static void clearTokenStore(JPhone phone) {
		// ADD CODE HERE
		File file = new File(phone.getId() + "-tokens.txt");
		if(file.delete()){
			System.out.println("Clear token store");
		}

	}


	/**
	 * Load tokens for phone
	 *
	 * @param phone phone to load tokens for
	 *
	 * @return loaded tokens
	 */
	public static List<Token> loadTokens(JPhone phone){
		List<Token> tokens = new LinkedList<>();

		// REPLACE THE FOLLOWING CODE
		Random rnd = new Random();
		for (int i = 0; i < 3; i++) {
			String line = UUID.randomUUID().toString()+";"+Long.toString(System.currentTimeMillis()-rnd.nextInt(1000000));
			tokens.add(parseToken(line));
		}

		try(BufferedReader br = new BufferedReader(new FileReader(phone.getId() + "-tokens.txt"))) {
			while(br.ready()){
				tokens.add(parseToken(br.readLine()));
			}
		} catch (IOException e) {
            tokens = null;
        }
        // END OF CODE TO REPLACE

		return tokens;
	}

	/**
	 * Save token for provided phone
	 *
	 * @param phone phone to save token for
	 * @param token token to save
	 */
	public static void saveToken(JPhone phone, Token token) {
		String line = token.getValue() + ";" + token.getDate().getTime();

		// ADD CODE HERE
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(phone.getId()+"-tokens.txt", true))) {
			bw.write(line);
			bw.newLine();
		} catch (IOException e) {
            JOptionPane.showMessageDialog(null ,"Fehler beim Schreiben der Datei");
        }
    }

	/**
	 * Parse a token line
	 *
	 * @param line token line to parse
	 *
	 * @return parsed token instance
	 */
	private static Token parseToken(String line) {
		String[] parts = line.split("[;]");
		if (parts.length == 2 ) {
			try {
				return new Token(parts[0], new Date(Long.parseLong(parts[1])));
			} catch (Exception e) {
				System.err.println("Error parsing token line: " + line);
				e.printStackTrace();
			}
		}
		return null;
	}
}
