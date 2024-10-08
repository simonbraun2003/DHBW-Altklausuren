package de.dhbwka.evasionator.classes;

import java.util.LinkedList;
import java.util.List;

/**
 * The application main class
 */
public class Evasionator {

   /**
    * Application entry point
    * 
    * @param args
    *           CLI arguments (ignored)
    */
   public static void main( String[] args ) {
      QuestionCatalogue qc = Evasionator.loadQuestions();
      List<Evasion> evasions = Evasionator.loadEvasions();

      AnswerGenerator gen = new ListAnswerGenerator( evasions );

      QuestionnaireTerm questionnaireTerm = new QuestionnaireTerm();

      new EvasionatorTerm( qc, gen, questionnaireTerm );
   }

   public static QuestionCatalogue loadQuestions() {
      QuestionCatalogue qc = new QuestionCatalogue();

      // -- REPLACE BELOW --
      String[] dummies = new String[] {
            "Sind Ihnen die bisherigen Erkenntnisse zu {{TOPIC}} bekannt?;true;YES_NO",
            "Welche Auswirkungen könnte Ihr Handeln für die Menschheit haben?;false;GENERAL",
            "Welche Konsequenzen könnte Ihr Handeln für Sie haben?;false;GENERAL",
            "Gibt es weitere Themen neben {{TOPIC}}, die für diesen Fall relevant sind?;false;GENERAL",
            "Wie tief sind sie selbst in die Sache verstrickt?;false;GENERAL",
            "Gibt es Mitt\u00e4ter? Nennen Sie Namen!;false;YES_NO",
            "Was könnte Sie entlasten?;false;GENERAL",
            "Welche Personen können noch davon geh\u00f6rt haben?;false;WITNESS",
            "Welche mildernden Umst\u00e4nde können Sie geltend machen?;false;GENERAL",
            "Warum ist der Verdacht wohl auf Sie gefallen?;false;GENERAL"
      };
      for ( String dummy : dummies ) {
         Question q = Evasionator.parseQuestion( dummy );
         if ( q != null ) {
            qc.addQuestion( q );
         }
      }
      // -- REPLACE ABOVE --

      return qc;
   }

   public static List<Evasion> loadEvasions() {
      List<Evasion> evasions = new LinkedList<>();

      // -- REPLACE BELOW --
      String[] dummies = new String[] {
            "Bei solchen Fragen werde ich immer melancholisch, sorry!;GENERAL",
            "Beim Thema {{TOPIC}} bin ich blank.;GENERAL",
            "Das ist ein überraschender Umstand, den ich erst noch verarbeiten muss.;GENERAL",
            "Das ist eine heikle Frage, die ich nicht einfach so beantworten kann.;GENERAL",
            "Das ist eine peinliche Geschichte, die ich lieber ein anderes Mal erz\u00e4hle.;GENERAL",
            "Das ist eine persönliche Angelegenheit, die ich lieber für mich behalte.;GENERAL",
            "Das ist eine unerwartete Frage, die ich noch nicht in Betracht gezogen habe.;GENERAL",
            "Daran kann ich mich nicht erinnern.;GENERAL,WITNESS",
            "Das entzieht sich meiner Kenntnis.;GENERAL,WITNESS",
            "Das ist mir nicht bekannt.;GENERAL,WITNESS"
      };
      for ( String dummy : dummies ) {
         Evasion e = Evasionator.parseEvasion( dummy );
         if ( e != null ) {
            evasions.add( e );
         }
      }
      // -- REPLACE ABOVE --

      return evasions;
   }

   /**
    * Parse a question line to the question object instance
    * 
    * @param line
    *           line to parse
    * @return question object instance
    */
   // provided
   public static Question parseQuestion( String line ) {
      try {
         String[] parts = line.split( ";" );
         return new Question( parts[0], Boolean.parseBoolean( parts[1] ), QuestionType.valueOf( parts[2] ) );
      } catch ( Exception e ) {
         System.err.println( "Error parsing question line: " + line );
         e.printStackTrace();
      }
      return null;
   }

   /**
    * Parse an evasion line to the evasion object instance
    * 
    * @param line
    *           line to parse
    * @return evasion object instance
    */
   // provided
   public static Evasion parseEvasion( String line ) {
      try {
         String[] parts = line.split( ";" );
         String[] typeStrings = parts[1].split( "," );
         List<QuestionType> types = new LinkedList<>();
         for ( String typeStr : typeStrings ) {
            types.add( QuestionType.valueOf( typeStr ) );
         }
         return new Evasion( parts[0], types );
      } catch ( Exception e ) {
         System.err.println( "Error parsing evasion line: " + line );
         e.printStackTrace();
      }
      return null;
   }

   /**
    * Check if text contains an unreasonable keyword, usage:
    * 
    * <pre>
    *    String topic1 = "meine Doktorarbeit";
    *    String topic2 = "mein Quellcode";
    *    
    *    boolean check1 = Evasionator.containsUnreasonableKeyword( topic1 ); // --> true
    *    boolean check2 = Evasionator.containsUnreasonableKeyword( topic2 ); // --> false
    * </pre>
    * @param text
    *           text to check
    * @return <code>true</code> if an unreasonable keyword is contained,
    *         <code>false</code> otherwise
    */
   // provided
   public static boolean containsUnreasonableKeyword( String text ) {
      if ( text != null ) {
         String textLower = text.toLowerCase();
         final String[] keywords = { "doktorarbeit", "dissertation", "cum-ex", "cum ex", "cumex", "pkw-maut", "pkwmaut", "pkw maut", "flugblatt" };
         for ( String keyword : keywords ) {
            if ( textLower.contains( keyword ) ) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * <p>
    * Replace the topic placeholder text, usage:
    * </p>
    * 
    * <pre>
    *    String text = "Was ist {{TOPIC}}?";
    *    String issue = "Dingsda";
    *    String question = Evasionator.replaceTopic( text, topic ); // --> Was ist Dingsda?
    * </pre>
    * 
    * @param text
    *           the text with the placeholder
    * @param topic
    *           topic to fill in
    * @return text with the specified issue
    */
   // provided
   public static String replaceTopic( String text, String topic ) {
      if ( text == null ) {
         return null;
      }
      if ( topic == null ) {
         return text;
      }
      // String.replace replaces ALL occurrences of the specified search string 
      return text.replace( "{{TOPIC}}", topic );
   }

}
