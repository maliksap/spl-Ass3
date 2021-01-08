package bgu.spl.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Passive object representing the bgu.spl.net.Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
	private ConcurrentHashMap<String, User> usersInfo;
	private ConcurrentHashMap<Integer,Course> coursesInfo;
	private Vector<Integer> courseOrder;

	//to prevent user from creating new bgu.spl.net.Database
	private Database() {
		this.usersInfo=new ConcurrentHashMap<>();
		this.coursesInfo=new ConcurrentHashMap<>();
		this.courseOrder = new Vector<>();

	}


	private static class DatabaseHolder{
		private static Database instance = new Database();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Database getInstance() {
		return DatabaseHolder.instance;
	}
	
	/**
	 * loades the courses from the file path specified 
	 * into the bgu.spl.net.Database, returns true if successful.
	 */
	public boolean initialize(String coursesFilePath) {
		try {
			File myObj = new File(coursesFilePath);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String courseData = myReader.nextLine();
				String[] courseMembers = courseData.split("\\|");
				String[] kdamArray = courseMembers[2].substring(1, (courseMembers[2].length() - 1)).split(",");
				LinkedList<Integer> kdamList = new LinkedList<>();
				Vector<String> studsReg = new Vector<>();
				if (!(kdamArray[0].length()==0)) {
					for (int i = 0; i < kdamArray.length; i++) {
						kdamList.add(Integer.parseInt(kdamArray[i]));
					}
				}
				Course c = new Course(courseMembers[1], Integer.parseInt(courseMembers[3]), kdamList, studsReg);
				coursesInfo.putIfAbsent(Integer.parseInt(courseMembers[0]), c);
				courseOrder.add(Integer.parseInt(courseMembers[0]));
			}
			myReader.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return false;
	}

	public ConcurrentHashMap<String, User> getUsersInfo() {
		return usersInfo;
	}

	public ConcurrentHashMap<Integer, Course> getCoursesInfo() {
		return coursesInfo;
	}

	public Vector<Integer> getCourseOrder() {
		return courseOrder;
	}


}
