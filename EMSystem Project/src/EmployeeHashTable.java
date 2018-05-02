import java.util.ArrayList;

/**
 * A open hashing/closed addressing hash table class for EmployeeInfo objects.
 * Contains methods for adding and removing EmployeeInfo objects from the hash table, as well as a method for displaying its contents.
 * @author Longman Xu
 * @version 2018-04-03
 * 
 */
public class EmployeeHashTable {
	
	private int k; // the k value (number of buckets, length of hashTable)
	
	private ArrayList<EmployeeInfo>[] hashTable; // the hash table, which is an array of ArrayLists (buckets) of EmployeeInfo objects
	
	
	/**
	 * Constructs a new open hashing/closed addressing hash table with the specified k value.
	 * @param k the k value (number of buckets).
	 */
	@SuppressWarnings("unchecked")
	public EmployeeHashTable(int k) {
		this.k = k; // set the k value
		
		this.hashTable = new ArrayList[k]; // initialize the hashTable array to contain k number of buckets
		
		// initialize the ArrayLists (buckets) within the hashTable array
		for (int i = 0; i < k; i++) {
			this.hashTable[i] = new ArrayList<EmployeeInfo>();
		}
	}
	
	/**
	 * Adds the specified EmployeeInfo object to the hash table.
	 * @param newItem the new EmployeeInfo to be added.
	 */
	public void add(EmployeeInfo newItem) {
		// adds the new EmployeeInfo object to the bucket with index employee number % k
		hashTable[newItem.getEmployeeNumber() % k].add(newItem);
	}
	
	/**
	 * Finds and returns the EmployeeInfo object with the specified employee number.
	 * @param employeeNumber the employee number of the EmployeeInfo object to be searched for.
	 * @return the EmployeeInfo object with the given employee number, or null if one was not found.
	 */
	public EmployeeInfo find(int employeeNumber) {
		// for every employee within the correct bucket, check if its SN == the given SN
		for (EmployeeInfo employee : hashTable[employeeNumber % k]) {
			// return the employee if it has the correct SN
			if (employee.getEmployeeNumber() == employeeNumber) {
				return employee;
			}
		}
		// return null if no employee with the correct SN is found
		return null;
	}
	
	/**
	 * Removes the first EmployeeInfo object with the specified employee number from the hash table.
	 * @param employeeNumber the employee number of the EmployeeInfo object to be removed.
	 * @return the EmployeeInfo object that was found and removed, or null if one was not found.
	 */
	public EmployeeInfo remove(int employeeNumber) {
		// create an ArrayList pointing to the bucket containing the target employee
		ArrayList<EmployeeInfo> targetBucket = hashTable[employeeNumber % k];
		// iterate through the bucket until finding a EmployeeInfo object with the specified SN
		for (int i = 0; i < targetBucket.size(); i++) {
			// if the EmployeeInfo object has the correct SN, return and remove it
			if (targetBucket.get(i).getEmployeeNumber() == employeeNumber) {
				return targetBucket.remove(i);
			}
		}
		// return null if no employee with the correct SN is found
		return null;
	}
	
	/**
	 * Display the contents of the hash table.
	 * The contents will be formatted as follows: Bucket number: [first EmployeeInfo], [second EmployeeInfo], ...
	 */
	public void display() {
		for (int i = 0; i < k; i++) {
			// print the bucket number
			System.out.print("Bucket " + Integer.toString(i) + ": ");
			
			// create an ArrayList pointing to the current bucket
			ArrayList<EmployeeInfo> tempBucket = hashTable[i];
			
			// only iterate through the current bucket if it is not empty (will get IndexOutOfBounds otherwise)
			if (tempBucket.size() != 0) {
				// display every employee in the bucket with a trailing comma, except the last one
				for (int j = 0; j < tempBucket.size() - 1; j++) {
					System.out.print("[" + tempBucket.get(j).toString() + "], ");
				}
				// display the last employee in the bucket without a trailing comma
				System.out.print("[" + tempBucket.get(tempBucket.size() - 1).toString() + "]");
			}
			
			System.out.println(); // print a newline after every bucket
		}
	}
	
}
