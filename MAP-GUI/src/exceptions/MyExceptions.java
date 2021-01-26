package exceptions;

import java.text.MessageFormat;

public class MyExceptions extends Exception {
    public MyExceptions(String message){super(message);}
    public MyExceptions(String message, Object... objects){super(MessageFormat.format(message,objects));}

}
