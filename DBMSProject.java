import java.lang.*;
import java.util.*;

class DBMSProject
{
    public static void main(String arg[])
    {
        DBMS dobj = new DBMS();
        dobj.StartDBMS();
    }
}
//Database table / Schema
class Student
{
    public int RID;
    public String Name;
    public int Salary;

    private static int Generator;

    static
    {
        Generator = 0;
    }

    public Student(String str, int value)
    {
        this.RID = ++Generator;
        this.Name = str;
        this.Salary = value;
    }

    public void DisplayData()
    {
        if(Name.length() >= 8)
        {
            System.out.println("|\t"+this.RID+"\t|\t"+this.Name+"\t|\t"+this.Salary+"\t|");
        }
        else
        {
            System.out.println("|\t"+this.RID+"\t|\t"+this.Name+"\t\t|\t"+this.Salary+"\t|");
        }
    }
}

class DBMS
{
    public LinkedList<Student> lobj;

    public DBMS()
    {
        lobj = new LinkedList<>();
    }

    public void StartDBMS()
    {
        Scanner scanobj = new Scanner(System.in);
        System.out.println("...Customised DBMS started successfully...");
        String Query = "";
////////////////////////////////////////////////////////////////////////////////////
///                                     SHELL                                    ///
////////////////////////////////////////////////////////////////////////////////////
        while(true)
        {
            System.out.print("Custom DBMS Console > ");
            Query = scanobj.nextLine();

            String tokens[] = Query.split(" ");
            int QuerySize = tokens.length;

            if(QuerySize == 1)
            {
                if("Help".equalsIgnoreCase(tokens[0]))
                {
                    System.out.println("*** This application is used to demonstate the Costomised DBMS ***");
                    System.out.println();
                    System.out.println("Exit : terminate DBMS");
                    System.out.println("Insert data : Insert into Student Name_String Salary_int");
                    System.out.println("Display All Data : Select * from student");
                    System.out.println("Display Specific By Name : Select String_name from student");
                    System.out.println("Display Specific By RID : Select RID_interger from student");
                    System.out.println("DeleteSpecific by name : delete from student where String_name");
                    System.out.println("DeleteSpecific by RID : delete from student where RID_int");
                    System.out.println("Sum of column elements : Select sum Column_name from Student");
                    System.out.println("Average of column elements : Select average Column_name from Student");
                    System.out.println("Count : Select count from Student");
                }
                else if("Exit".equalsIgnoreCase(tokens[0]))
                {
                    System.out.println("......THANK YOU FOR VISITING DBMS......");
                    break;
                }
                else
                {
                    System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                }
            }//if(QuerySize == 1)

            else if(QuerySize == 4)
            {
                if("Select".equalsIgnoreCase(tokens[0]))
                {
                    if("*".equalsIgnoreCase(tokens[1]))
                    {
                        if("from".equalsIgnoreCase(tokens[2]))
                        {
                            System.out.println("---------------------------------------------------------");
                            System.out.println("|\tRID\t|\tStudent\t\t|\tSalary\t|");
                            System.out.println("---------------------------------------------------------");
                            DisplayAll();
                            System.out.println("---------------------------------------------------------");
                        }
                        else
                        {
                           System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                        }
                    }
                //Select count from Student;
                    else if("count".equalsIgnoreCase(tokens[1]))
                    {
                        if("from".equalsIgnoreCase(tokens[2]) && "from".equalsIgnoreCase(tokens[2]))
                        {
                            AggregateCount();
                        }
                        else
                        {
                            System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                        }
                    }

                    else
                    {
                        int iRID = 0;
                        boolean bRet = false;
                        try
                        {
                            iRID = Integer.parseInt(tokens[1]);
                            bRet = true;
                        }
                        catch (NumberFormatException e)
                        {
                        }
                        if(bRet)
                        {
                            if("from".equalsIgnoreCase(tokens[2]))
                            {
                                System.out.println("---------------------------------------------------------");
                                System.out.println("|\tRID\t|\tStudent\t\t|\tSalary\t|");
                                System.out.println("---------------------------------------------------------");
                                DisplaySpecific(iRID);
                                System.out.println("---------------------------------------------------------");
                            }
                            else
                            {
                               System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                            }
                        }
                        else
                        {
                            if("from".equalsIgnoreCase(tokens[2]))
                            {
                                System.out.println("---------------------------------------------------------");
                                System.out.println("|\tRID\t|\tStudent\t\t|\tSalary\t|");
                                System.out.println("---------------------------------------------------------");
                                DisplaySpecific(tokens[1]);
                                System.out.println("---------------------------------------------------------");
                            }
                            else
                            {
                                System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                            }
                        }//end else
                    }
                }//if("Select".equalsIgnoreCase(tokens[0]))
                else
                {
                    System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                }
            }//else if(QuerySize == 4)

            else if(QuerySize == 5)
            {
                //Insert into student abhishek 1000
                if("Insert".equalsIgnoreCase(tokens[0]) )
                {
                    if("into".equalsIgnoreCase(tokens[1]) && "Student".equalsIgnoreCase(tokens[2]))
                    {
                        InsertData(tokens[3],Integer.parseInt(tokens[4]));
                    }
                    else
                    {
                        System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                    }
                }

                else if("Select".equalsIgnoreCase(tokens[0]))
                {
                //Select sum Salary from Student;
                    if("sum".equalsIgnoreCase(tokens[1]))
                    {
                        AggregateSum();
                    }
                //Select average Salary from Student;
                    else if("average".equalsIgnoreCase(tokens[1]))
                    {
                        AggregateAverage();
                    }
                //Select Max Salary from Student;
                    else if("Max".equalsIgnoreCase(tokens[1]))
                    {
                        AggregateMax();
                    }
                //Select Min Salary from Student;
                    else if("Min".equalsIgnoreCase(tokens[1]))
                    {
                        AggregateMin();
                    }
                }//end else if("Select".equalsIgnoreCase(tokens[0]))
                else
                {
                    System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                }
            }//end else if(QuerySize == 5)
            else if(QuerySize == 6)
            {
          //delete from student where name
          //delete from student where RID
                if("delete".equalsIgnoreCase(tokens[0]))
                {
                    if("from".equalsIgnoreCase(tokens[1]) && ("Student".equalsIgnoreCase(tokens[2])) && ("where".equalsIgnoreCase(tokens[3])))
                    {
                        if(("RID".equalsIgnoreCase(tokens[4])))
                        {
                            int iRID = 0;
                            try
                            {
                                iRID = Integer.parseInt(tokens[5]);
                            }
                            catch (NumberFormatException e){}
                            DeleteSpecific(iRID);
                        }
                        else if("Name".equalsIgnoreCase(tokens[2]))
                        {
                            DeleteSpecific(tokens[5]);
                        }
                    }
                    else
                    {
                        System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                    }
                }//end if("delete".equalsIgnoreCase(tokens[0]))
                else
                {
                    System.out.println("!!!..ERROR : Please check syntax or use command help ..!!!");
                }
            }//end else if(QuerySize == 6)
        }//end while
    }
///////////////////////////////////////////////////////////////////
///                         FUNCTIONS                           ///
///////////////////////////////////////////////////////////////////
    public void InsertData(String str, int value)
    {
        Student sobj = new Student(str,value);
        lobj.add(sobj);
    }
    //Display All//
    public void DisplayAll()
    {
        for(Student sref : lobj)
        {
            sref.DisplayData();
        }
    }
    //Display by RID
    public void DisplaySpecific(int rid)
    {
        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                sref.DisplayData();
                break;
            }
        }
    }
    //Display by Name
    public void DisplaySpecific(String str)
    {
        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.DisplayData();
            }
        }
    }
    //Delete by RID
    public void DeleteSpecific(int rid)
    {
        int index = 0;
        for(Student sref : lobj)
        {
            if(sref.RID == rid)
            {
                lobj.remove(index);
                break;
            }
            index++;
        }
    }
    //Delete by name
    public void DeleteSpecific(String str)
    {
        int index = 0;
        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                lobj.remove(index);
                break;
            }
            index++;
        }
    }
//
    public void AggregateMax()
    {
        int iMax = 0;
        Student temp = null;
        for(Student sref : lobj)
        {
            if(sref.Salary > iMax)
            {
                iMax = sref.Salary;
                temp = sref;
            }
        }
        System.out.println("Inforamtion of student having maximum salary : ");
        temp.DisplayData();
    }
//
    public void AggregateMin()
    {
        int iMin = (lobj.getFirst()).Salary;
        Student temp = lobj.getFirst();
        for(Student sref : lobj)
        {
            if(sref.Salary < iMin)
            {
                iMin = sref.Salary;
                temp = sref;
            }
        }
        System.out.println("Inforamtion of student having Minimum salary : ");
        temp.DisplayData();
    }
//
    public void AggregateSum()
    {
        long Sum = 0;
        for(Student sref : lobj)
        {
            Sum = Sum + sref.Salary;
        }
        System.out.println("Summation of Salaries is : "+Sum);
    }
//
    public void AggregateAverage()
    {
        long Sum = 0;
        for(Student sref : lobj)
        {
            Sum = Sum + sref.Salary;
        }
        System.out.println("Average of Salary is : "+Sum/(lobj.size()) );
    }
//
    public void AggregateCount()
    {
        System.out.println("Count is : "+(lobj.size()));
    }
}

