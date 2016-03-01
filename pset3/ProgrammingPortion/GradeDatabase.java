package ProblemSet3.programmingProblems;/*
 * ProblemSet3.programmingProblems.GradeDatabase.java
 *
 * Author:          Computer Science E-22
 * Modified by:     <Paul Zeng>
 */

import java.util.*;
import java.util.ListIterator;

/**
 * A simple in-memory database of student and grade information.
 *
 *
 * COMMENTS ABOUT TIME EFFICIENCY:
 *
 * In my implementation,
 *    addStudent() and addGrade() run in quadratic time because they traverse through the table to
 * insert the input item in its correct ordered position. The traversal is the outer loop, which takes linear time,
 * and finding the correct insertion position takes linear time, resulting in overall quadratic time.
 *    printStudents() and printGrades() run in linear time because they need to traverse through
 * the table and print each record. You can't do better than linear time.
 *    printStudentsGrades() runs in linear time because each record in either the students table
 * or the grades table is visited only once.
 *    For more details, see comments above each method's definition.
 *
 * Alternatively, I could have had addStudent() and addGrade() run in constant time by always
 * adding to the beginning of the table, and had printStudentsGrades() run in quadratic time
 * by iterating through the students table and for each student record, iterate through the
 * entire grades table to find matches.
 *
 * I chose to sacrifice addStudent()'s and addGrade()'s constant run time to avoid printStudentsGrades()'s
 * quadratic run time because:
 * 1. printStudentsGrades() is an operation whereby after the user typed in the corresponding command, she expects to
 *    see an output on the screen in a timely manner. So, if there is any lag, the user would likely be displeased.
 *
 * 2. addStudent() and addGrade() are invoked by user's input by hand, whereby after the user typed in some input, she
 *    either continues to type another input or does nothing. It takes time for the user to type each input, which
 *    gives the program "buffer" time to execute addStudent() and addGrade().
 *
 */
public class GradeDatabase {
    /* 
     * A private inner class for storing information about a student.
     */
    private class StudentRecord {
        private int id;
        private String lastName;
        private String firstName;

        StudentRecord(int id, String lastName, String firstName) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
        }
    }

    /* 
     * A private inner class for storing information about a student's
     * grade on a particular assignment.
     */
    private class GradeRecord {
        private int studentID;
        private String assignment;    // e.g., "PS 1" or "midterm"
        private int grade;

        GradeRecord(int studentID, String assignment, int grade) {
            this.studentID = studentID;
            this.assignment = assignment;
            this.grade = grade;
        }
    }


    /****
     * add your instance variables here
     ****/
    private LLList students;
    private LLList grades;


    public GradeDatabase() {
        /** complete the constructor below **/
        students = new LLList();
        grades = new LLList();
    }

    /**
     * addStudent - add a record for the student with the specified information
     *
     * This method runs in O(n^2) time because it maintains small to large ordering
     * of the records. And every time it's called, it iterates through the students table
     * and calls students.addItem() to insert student record in its correct position and the
     * addItem() call takes O(n). The whole operation takes O(n^2)
     */
    public void addStudent(int id, String last, String first) {
        /* complete the method below */
        ListIterator iterator = students.iterator();
        StudentRecord newRecord = new StudentRecord(id, last, first);
        int insert = 0;

        while (iterator.hasNext()) {
            StudentRecord item = (StudentRecord) iterator.next();
            if (item.id >= id)
                break;
            insert++;
        }
        students.addItem(newRecord, insert);

    }

    /**
     * addGrade - add a record for the grade entry with the specified details
     *
     * This method runs in O(n^2) time because it maintains small to large ordering
     * of the records. And every time it's called, it iterates through the grades table
     * and calls grades.addItem() to insert student record in its correct position and the
     * addItem() call takes O(n). The whole operation takes O(n^2).
     */
    public void addGrade(int id, String asst, int grade) {
        /* complete the method below */
        ListIterator iterator = grades.iterator();
        GradeRecord newRecord = new GradeRecord(id, asst, grade);
        int insert = 0;

        while (iterator.hasNext()) {
            GradeRecord item = (GradeRecord) iterator.next();
            if (item.studentID >= id)
                break;
            insert++;
        }
        grades.addItem(newRecord, insert);
    }

    /**
     * printStudents - print the entries in the student table
     *
     * This method runs in O(n) time because it traverses through the students table
     * and prints every record
     */
    public void printStudents() {
        System.out.println();
        System.out.println("id\tlast\t\tfirst");
        System.out.println("--------------------------------------------");
        
        /* complete the method below */

        // This is O(n) time because it traverse through the records only once.
        ListIterator iterator = students.iterator();
        while (iterator.hasNext()) {
            StudentRecord record = (StudentRecord) iterator.next();
            System.out.printf("%d\t%s\t\t%s\n", record.id, record.lastName, record.firstName);
        }

    }

    /**
     * printGrades - print the entries in the grade table
     * This method runs in O(n) time because it traverses through the grades table
     * and prints every record
     */
    public void printGrades() {
        System.out.println();
        System.out.println("id\tassignment\tgrade");
        System.out.println("--------------------------------------------");
        
        /* complete the method below */
        // This is O(n) time because it traverse through the records only once.
        ListIterator iterator = grades.iterator();
        while (iterator.hasNext()) {
            GradeRecord record = (GradeRecord) iterator.next();
            System.out.printf("%d\t%s\t%d\n", record.studentID, record.assignment, record.grade);
        }


    }

    /**
     * printStudentsGrades - print a "join" of the student and grade
     * tables.  See the problem set handout for more details.
     *
     * This table runs in O(n + m) time, where n is the number of records in the students table
     * and m is the number of records in the grades table, because although it has two while loop with
     * one nested inside the other, each record is visited only once
     */
    public void printStudentsGrades() {
        System.out.println();
        System.out.println("last\t\tfirst\tassignment\tgrade");
        System.out.println("------------------------------------------------");
        
        /* complete the method below */
        ListIterator studentsItr = students.iterator();
        ListIterator gradesItr = grades.iterator();
        StudentRecord studentRecord = null;
        GradeRecord gradeRecord = null;

        outer:
        while (studentsItr.hasNext()) {
            studentRecord = (StudentRecord) studentsItr.next();

            if (gradeRecord != null && studentRecord.id < gradeRecord.studentID) {
                // this will skip the inner while loop and wait for students table's current record's id to catch up
                continue;
            } else if (gradeRecord != null && studentRecord.id == gradeRecord.studentID) {
                System.out.printf("%s\t\t%s\t%s\t%d\n",
                        studentRecord.lastName, studentRecord.firstName,
                        gradeRecord.assignment, gradeRecord.grade);
            }

            inner:
            while (gradesItr.hasNext()) {

                gradeRecord = (GradeRecord) gradesItr.next();
                if (studentRecord.id > gradeRecord.studentID) {
                    continue; // wait for grades table id to catch up
                } else if (studentRecord.id == gradeRecord.studentID) {
                    System.out.printf("%s\t\t%s\t%s\t%d\n",
                            studentRecord.lastName, studentRecord.firstName,
                            gradeRecord.assignment, gradeRecord.grade);
                } else if (studentRecord.id < gradeRecord.studentID) {
                    // the current grades record's id has moved higher than the current students record's id
                    // we need to stop advancing grades table, and take a "break" and let the students table catch up
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String last, first, asst;
        int id, grade, op;

        GradeDatabase db = new GradeDatabase();

        while (true) {
            System.out.println();
            System.out.println("(1) Add student");
            System.out.println("(2) Add grade");
            System.out.println("(3) Print students");
            System.out.println("(4) Print grades");
            System.out.println("(5) Print each student's grades");
            System.out.println("(6) Exit");
            System.out.print("Which operation (1-6)? ");
            op = in.nextInt();
            in.nextLine();

            switch (op) {
                case 1:
                    System.out.print("    id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    last: ");
                    last = in.nextLine();
                    System.out.print("    first: ");
                    first = in.nextLine();

                    db.addStudent(id, last, first);
                    break;
                case 2:
                    System.out.print("    student id: ");
                    id = in.nextInt();
                    in.nextLine();
                    System.out.print("    assignment: ");
                    asst = in.nextLine();
                    System.out.print("    grade: ");
                    grade = in.nextInt();
                    in.nextLine();

                    db.addGrade(id, asst, grade);
                    break;
                case 3:
                    db.printStudents();
                    break;
                case 4:
                    db.printGrades();
                    break;
                case 5:
                    db.printStudentsGrades();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.  " +
                            "Please enter a number from 1-6.");
            }
        }
    }
}
