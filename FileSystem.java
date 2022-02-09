/**Name: Anish Rajeshkumar
ID: A16670774
Email: arajeshkumar@ucsd.edu
Sources Used: tutors
File header: The purpose of this file is to create a 
working filesystem using a Binary search tree
*/

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**Class header: This class stores  methods
 * which allow the user add,find files by name
 * date as well as 
 * filter through the files by name 
 * and date

*/
public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    // TODO
    /**
	 * This constructor initilizez the instance vairables and 
	 * initilizez the 2 binary search trees one for date
	 * and one for name
	
	 */
    public FileSystem() {
    	nameTree = new BST<String, FileData>();
    	dateTree = new BST<String, ArrayList<FileData>>();

    }


    // TODO
    /**
   	 * This constructor initilizez the instance vairables and 
   	 * initilizez the 2 binary search trees one for date
   	 * and one for name as well as takes in an 
   	 * input file which it fills both binary trees
   	 * with the filedata in the files
   	 * @param string name of input file
   	
   	 */
    public FileSystem(String inputFile) {
    	// Add your code here
    	nameTree = new BST<String, FileData>();
    	dateTree = new BST<String, ArrayList<FileData>>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                this.add(data[0], data[1], data[2]);
                // Add your code here
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }
     
    
    /**create a FileData object with the given file information and 
     * add it to the instance variables of FileSystem. This 
     * method then adds the filedata object into the 
     * binary search trees if it is most recent and 
     * is not a duplicate
     * @param filename, directory and modified date
   
    */
    // TODO
    public void add(String name, String dir, String date) {
    	if(name != null && dir != null && date != null) {
    		FileData object = new FileData(name,dir,date);
    		FileData compare = nameTree.get(object.name);
    		
    		if(compare != null) {
    			//System.out.println(compare.toString());
    			if(compare.dir.equals(object.dir) 
    			&& compare.lastModifiedDate.equals(object.lastModifiedDate)) {
    				return;
    			}
    			if(compare.lastModifiedDate.equals(object.lastModifiedDate)) {
    				return;
    			}
    			int comp = 
    			compare.lastModifiedDate.compareTo(object.lastModifiedDate);
    			//System.out.println(comp);
    			if(comp < 0) {
    				this.nameTree.replace(name, object);
    				ArrayList<FileData> temp = 
    				this.dateTree.get(compare.lastModifiedDate);
    				temp.remove(compare);
					ArrayList<FileData> list = new ArrayList<FileData>();
    				list.add(object);
    				this.dateTree.set(date, list);
    			
    			}
    			if(comp > 0) {
    				return;
    			}
    		}
    		else {
    			this.nameTree.set(name, object);
    			ArrayList<FileData> temp = dateTree.get(date);
    			if(temp != null) {
    				temp.add(object);
    			}
    			else {
    				ArrayList<FileData> list = new ArrayList<FileData>();
    				list.add(object);
    				this.dateTree.set(date, list);
    			}
    			
    		}
    	}
    	
    	
    }

    /**Given a date (format: yyyy/mm/dd), return an 
     * ArrayList of file names that correspond to this
     *  date. This list should have the file names 
     *  in the order that they were added.
     * @param string date
     * @return an arraylist of filenames
   
    */
    // TODO
    public ArrayList<String> findFileNamesByDate(String date) {
    	if(date == null) {
    		return null;
    	}
    	ArrayList<String> names = new ArrayList<String>();
    	ArrayList<FileData> list = this.dateTree.get(date);
    	if(list!= null) {
    		for(int i = 0; i < list.size(); i++) {
    			names.add(list.get(i).name);
    		}
    	}
    	return names;

    }
    /**Given a startDate and an endDate (format: yyyy/mm/dd),
     *  return a new FileSystem that contains only the
     *   files that are within the range
     *    (startDate is inclusive, endDate is exclusive).
     *   
     * @param string start date and end date
     * @return new filesystem object with filtered files
   
    */
    // TODO
    public FileSystem filter(String startDate, String endDate) {
    	FileSystem temp = new FileSystem();
    	ArrayList<String> list = (ArrayList<String>) this.dateTree.keys();
    	for(int i = 0; i < list.size(); i++) {
    		if(startDate.compareTo(list.get(i)) <= 0 &&
    				endDate.compareTo(list.get(i)) > 0 ) {
    			ArrayList<FileData> data = this.dateTree.get(list.get(i));
    			if(data != null) {
    				for(int j = 0; j < data.size(); j++) {
    					temp.add(data.get(j).name, 
    					data.get(j).dir, data.get(j).lastModifiedDate);
    				}
    			}
    		}
    	}
    	return temp;
 
    }
    
    /**Give a string wildCard, return a new FileSystem 
     * that contains only the files with names that contain
     *  the wildCard string. Note that this wildcard can
     *   be found anywhere in the file name
  
        Assume the given parameter is valid and non-null.
     *   
     * @param string wildcard
     * @return new filesystem object with filtered files
   
    */
    // TODO
    public FileSystem filter(String wildCard) {
    	FileSystem temp = new FileSystem();
    	ArrayList<String> list = (ArrayList<String>) this.nameTree.keys();
    	for(int i = 0; i < list.size(); i++) {
    		if(list.get(i).contains(wildCard)) {
    			FileData object = this.nameTree.get(list.get(i));
    			temp.add(object.name,object.dir,object.lastModifiedDate);
    		}
    	}
    	return temp;

    }
    
    /**Return a List that contains the nameTree where each entry is formatted as:
        ": <FileData toString()>"

       This list should be in alphabetical order.
     *   
     * 
     * @return string list of filedata objects in
     * in the name tree binary search tree
   
    */
    // TODO
    public List<String> outputNameTree(){
    	ArrayList<String> names = new ArrayList<String>();
    	ArrayList<String> list = (ArrayList<String>) this.nameTree.keys();
    	for(int i = 0; i < list.size(); i++) {
    		names.add(list.get(i) + ":" + " " 
    	    + this.nameTree.get(list.get(i)).toString());
    	}
    	return names;
    	

    }
    
    /**Return a List that contains the dateTree where each entry is formatted as:
      ": <FileData toString()>"

       The List should be in order from most recent to oldest.
     *   
     * @param
     * @returnstring list of filedata objects in
     * in the date tree binary search tree
   
    */
    // TODO
    public List<String> outputDateTree(){
    	ArrayList<String> temp = new ArrayList<String>();
    	ArrayList<String> dates = new ArrayList<String>();
    	ArrayList<String> list = (ArrayList<String>) this.dateTree.keys();
    	for(int i = 0; i < list.size(); i++) {
    		ArrayList<FileData> temps = this.dateTree.get(list.get(i));
    		for(int j = 0; j < temps.size(); j++) {
    			temp.add(list.get(i) + ":" + " " + temps.get(j).toString());
    		}
    	}
    	for(int x = temp.size() - 1; x >=0; x--) {
    		dates.add(temp.get(x));
    	}
    	
    	
    	return dates;

    }
    

}

