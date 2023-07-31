
/**
 * Hash table implementation using linear probing for collision.
 * 
 * @author jefcha
 */
public class HashTable
{
    protected Entry[] mTable;
    /** number of items in table */
    protected int mItemNum;
    /** maximum load factor */
    protected double mMaxLoadFactor;



    /**
     * Inner class representing a cell.
     */
    protected class Entry
    {
        public String key;
        public String value;

        public Entry(String newKey, String newValue) {
            key = new String(newKey);
            value = new String(newValue);
        }
    } // end of inner class Entry

    
    /**
     *  Create the hash table.
     *  
     *  @param size Initial size of hash table.
     *  @param maxLoadFactor Maximum load factor before we rehash.
     */
    HashTable(int size, double maxLoadFactor) {
        mTable = new Entry[size];
        mItemNum = 0;
        mMaxLoadFactor = maxLoadFactor;


        for (int i = 0; i < mTable.length; i++) {
            mTable[i] = null;
        }
    } // end of HashTable()


    /**
     *  This is the hash function.   Implement as described in the lab sheet.
     *  
     *  @param key Key to hash.
     *  
     *  @return Hash value of key.
     */
    int calcHash(String key) {
        int hashVal = 0;      
        int n = mMaxLoadFactor + 1;
	for(int i = 0; i<key.length(); i++)
	{
		hashVal = hashVal + (int)key.charAt(i);
	}
	hashVal = hashVal%n;
        return hashVal;
    } // end of calcHash()


    /**
     *  Insert into hash table and rehash if loadfactor too high.
     *  
     *  @param key Key to insert.
     *  @param value Associated value.
     */
    void insert(String key, String value) {

        // rehash if too full 
        if ( (float)((float) mItemNum / mTable.length) > mMaxLoadFactor ){
            rehash();
        }

        // IMPLEMENT ME!

    } // end of insert()


    /**
     *  Delete a key + value pair from the hash table.
     *  
     *  @param key Key to delete.
     */
    void delete(String key) {
    	// IMPLEMENT ME!
    } // end of delete


    /**
     *  Check for membership of a key.
     *  
     *  @param key Key to search.
     *  
     *  @return Associated value, or null if key not in table.
     */
    String lookup(String key)
    {
    	// IMPLEMENT ME!

        return null;
    } // end of lookup()


    /**
     *  Resize the hash table so the load factor is ''normal'' again.
     */
    public void rehash() {
        // calc new hash table size based on load factor 
        int newSize = mItemNum * 4; // 25 % full 
        Entry[] oldTable = new Entry[mTable.length];
        System.arraycopy(mTable, 0, oldTable, 0, mTable.length);
        mTable = null;
        mTable = new Entry[newSize];

        // rehash the values on the old hash table 
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                insert(oldTable[i].key,
                            oldTable[i].value);
            }
        }
    } // end of rehash()


    /**
     *  Returns the list of key-value pairs in table.
     *  
     *  @return String of the list of key-vallue pairs.
     */
    public String toString() {
        StringBuffer str = new StringBuffer();
        // output all key + value pairs 
        str.append("key" + "\t\t" + "value\n");
        for (int i = 0; i < mTable.length; i++) {
            if (mTable[i] != null) {
                str.append(mTable[i].key + "\t\t" + mTable[i].value + "\n");
            }
        }
        
        return str.toString();
    } // end of toString()


} // end of class HashTable
