
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Demontrates the implementation of a dictionary using a hash table.
 */
public class DictDemo
{
    protected static final String progName = "DictDemo";

    /** Default initial hash table size. */
    private static final int DEFAULT_HASH_SIZE = 128;

    /** Default max load factor before rehashing occurs. */
    private static final double MAX_LOAD_FACTOR = 0.75;

    /** 
     * List of operations.
     */
    public enum Op {
        END,
        LIST,
        REMOVE,
        LOOKUP,
        UNKNOWN
    }


    /**
     * Print help information.
     */
    private static void printHelp() {
        System.err.println("Available commands:");
        System.err.println("    delete <key>");
        System.err.println("    lookup <key>");
        System.err.println("    list");
        System.err.println("    end/exit/quit");
    } // end of printHelp()


    /**
     * Process next operation.
     * 
     * @param table Hash table.
     */
    private static void getNextOperation(HashTable table)
    {
        Op operation = Op.UNKNOWN;

        Scanner scanner = new Scanner(System.in);


        while (operation != Op.END && scanner.hasNext()) {
            String command = scanner.next();

            // deletion
            if (command.compareTo("delete") == 0) {
                if (scanner.hasNext()) {
                    operation = Op.REMOVE;
                    String key = scanner.next();
                    
                    table.delete(key);
                }
            }
            // lookup/search
            else if (command.compareTo("lookup") == 0) {
               if (scanner.hasNext()) {
                    operation = Op.LOOKUP;
                    String key = scanner.next();
                    
                    String value = table.lookup(key);
                    if (value == null) {
                        System.out.println("key " + key + " not found!");
                    }
                    else {
                        System.out.println(key + " : " + value);
                    }
               }
            }
            // list contents of hash table
            else if (command.compareTo("list") == 0) {
                operation = Op.LIST;
                System.out.println(table.toString());
            }
            // quit
            else if (command.compareTo("end") == 0) {
                 operation = Op.END;
            }
            else if (command.compareTo("exit") == 0) {
                operation = Op.END;
            }
            else if (command.compareTo("quit") == 0) {
                operation = Op.END;
            }
            else {
                operation = Op.UNKNOWN;
                System.err.println("Unknown command");
                printHelp();
            }
    	}

    } // end of getNextOperation()


    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: " + DictDemo.progName + " <datafile.dat>");
            System.exit(1);
        }

        // create empty hash table
        System.out.println("creating hash table.");
        HashTable table = new HashTable(DEFAULT_HASH_SIZE, MAX_LOAD_FACTOR);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            System.out.println("reading data");

            // read in data from file and construction hash table
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");

                assert(fields.length == 2);

                String key = fields[0];
                String value = fields[1];

                table.insert(key, value);
            }

            printHelp();
            
            /* enter the 'mini' shell */
            getNextOperation(table);
        }
        catch (IOException ex) {
            System.err.println("Error opening input file.");
            System.exit(1);
        }

       
    } // end of main()

} // end of class DictDemo
