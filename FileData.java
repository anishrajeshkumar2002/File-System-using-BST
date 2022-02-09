/**Name: Anish Rajeshkumar
ID: A16670774
Email: arajeshkumar@ucsd.edu
Sources Used: tutors
File header: The purpose of this file is to implement a
filedata object with the necessary file information
*/

/**Class header: This class stores 1 method
 * which is the to string method which just prints
 * the instance variables of the fileData 
 * object in an orderly fasion

*/
public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    // TODO
    /**This constructor initilizes the instance variables
     * name, dir and lastmodifiedDate with the given parameters

    */
    public FileData(String name, String directory, String modifiedDate) {
    	this.name = name;
    	this.dir = directory;
    	this.lastModifiedDate = modifiedDate;

    }

    // TODO
    public String toString() {
    	String representation = "{Name:" + " " + this.name + "," + " "
        + "Directory:" + " " + this.dir + "," + " "
        + "Modified" + " " + "Date:" + " " + this.lastModifiedDate + "}";
    	return representation;

    }
}
