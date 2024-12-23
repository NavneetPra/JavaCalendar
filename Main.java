import java.time.Year;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        // Part 1: Display month loop
        Object yearly[][] = {{1,31},{2,28},{3,31},{4,30},{5,31},{6,30},{7,31},{8,31},{9,30},{10,31},{11,30},{12,31}};

        printCalendar(yearly);

        // Part 2: Display month loop with names
        Object yearlyM[][] = {{"January",31},{"Febuary",28},{"March",31},{"April",30},{"May",31},{"June",30},{"July",31},
            {"August",31},{"September",30},{"October",31},{"November",30},{"December",31}};
        
        printCalendar(yearlyM);

        // Part 3: Format and display weekly output
        int start = 1;

        for (Object[] i : yearlyM)
        {
            start = printMonth(i, start);
            System.out.println();
        }

        // Part 4: Getting current year and getting correct day to start the year and displaying the calendar again with the correct
        // start day for this year and checking leap year
        int year = Year.now().getValue();
        boolean isLeap = Year.now().isLeap();
        if (isLeap) System.out.println("It is a leap year");
        else System.out.println("It is not a leap year");
        System.out.println(year);

        String firstDay = Year.now().atDay(1).getDayOfWeek().name();
        System.out.println(firstDay);

        switch (firstDay) 
        {
            case "MONDAY":
                start = 2;
                break;
            case "TUESDAY":
                start = 3;
                break;
            case "WEDNESDAY":
                start = 4;
                break;
            case "THURSDAY":
                start = 5;
                break;
            case "FRIDAY":
                start = 6;
                break;
            case "SATURDAY":
                start = 7;
                break;
            case "SUNDAY":
                start = 1;
                break;
        }
        
        for (Object[] i : yearlyM)
        {
            start = printMonth(i, start);
            System.out.println();
        }
    
        // Part 5: Writing the calendar to a CSV file
        
        start = Year.now().atDay(1).getDayOfWeek().getValue() + 1;

        try
        {
            File calendarFile = new File("calendar.csv");
            FileWriter calendarWriter = new FileWriter("calendar.csv");
            
            calendarWriter.write("\"Month\"\n");

            for (Object[] i : yearlyM)
            {
                calendarWriter.write("\"" + i[0] + "\"\n");

                String daysOfWeek[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                for (String j : daysOfWeek)
                {
                    if (j != "Sat") calendarWriter.write(j + ", ");
                    else calendarWriter.write(j);
                }
                calendarWriter.write("\n");
                
                for (int j = 1; j <= (int) i[1]; j++)
                {
                    for (int k = start; k <= 7; k++)
                    {
                        start = k;
                        if (j > (int) i[1]) break;

                        if (j == 1) 
                        {
                            for (int l = 1; l < start; l++)
                                calendarWriter.write("_, ");
                        }
                        if (k < 7 && j < (int) i[1]) calendarWriter.write(j + ", ");
                        else calendarWriter.write(j + "");
                        if (k != 7) j++;
                        else start = 1;
                    }

                    // if (!(j >= (int) i[1])) start = 1;
                    calendarWriter.write("\n");
                }

                calendarWriter.write("\n_,\n");
            }

            calendarWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printCalendar(Object[][] cal)
    {
        for (Object i[] : cal)
        {
            System.out.print(i[0] + ": ");

            for (int j = 1; j <= (int) i[1]; j++)
            {
                if (j != (int) i[1]) System.out.print(j + ", ");
                else System.out.print(j);
            }

            System.out.println();
        }

        System.out.println();
    }

    public static int printMonth(Object[] month, int startDay)
    {
        System.out.println(month[0]);

        String daysOfWeek[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (String i : daysOfWeek)
        {
            System.out.printf(i + "\t");
        }

        System.out.println();

        for (int i = 1; i <= (int) month[1]; i++)
        {
            for (int j = startDay; j <= 7; j++)
            {
                startDay = j;
                if (i > (int) month[1]) break;

                if (i == 1) 
                {
                    for (int k = 1; k < startDay; k++)
                        System.out.printf("\t");
                }
                System.out.printf(i + "\t");
                if (j != 7) i++;
                else startDay = 1;
            }

            if (!(i >= (int) month[1])) startDay = 1;
            System.out.println();
        }

        return startDay;
    }
}
