package de.dhbwka.coronawarn.classes;

import java.util.List;

public interface CoronaWarnClient {
     Token getCurrentToken();
     List<Token> getAllTokens();
     List<Token> getAllSeenTokens();
     void tokenReceived(Token token);
}
