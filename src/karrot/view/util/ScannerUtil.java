package karrot.view.util;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner scan = new Scanner(System.in);

    public static Scanner getScanner() {
        return scan;
    }

    public static void closeScanner() {
    	scan.close();
    }
} 
