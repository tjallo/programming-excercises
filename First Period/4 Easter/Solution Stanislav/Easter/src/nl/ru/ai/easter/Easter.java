package nl.ru.ai.easter;
import java.util.Scanner;

public class Easter {
    static boolean isLeapYear(int year) {
        if (year%100 != 0 && year % 4 == 0)
            return true;
        else if(year % 100 ==0 && year%400 == 0  )
            return true;
        return false;
    }

    static int numberOfDaysInMonth(int year, Month month) {
        switch(month){
            case FEBRUARY: if(isLeapYear(year))
                return 29;
            return 28;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            default:
                return 31;
        }
    }

    static Month easterMonth(int year) {
        int Y = year;
        int a = Y % 19;
        int b = Y / 100;
        int c = Y % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int L = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * L) / 451;
        int month = ( h + L - 7 * m + 114) / 31;
        return Month.month(month);
    }

    static int easterDay(int year) {
        int Y = year;
        int a = Y % 19;
        int b = Y / 100;
        int c = Y % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int L = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * L) / 451;
        int day = ((h + L - 7 * m + 114) % 31) + 1;
        return day;


    }

    static int dayNumberInYear(int day, Month month, int year) {
        int days=0;
        for(int i =1;i<month.number(); i++){
            days=days+numberOfDaysInMonth(year,Month.month(i));
        }
        days= days+day;
        return days;
    }

    static Month monthInYearOfDayNumber(int dayNumber, int year) {
        for (int i = 1; i <= 12; i++) {
            dayNumber -= numberOfDaysInMonth(year, Month.month(i));
            if (dayNumber <= 0) {
                return Month.month(i);
            }

        }
        return Month.JANUARY;
    }


    static int dayInMonthOfDayNumber(int dayNumber, int year) {

        Month month = monthInYearOfDayNumber(dayNumber, year);
        int monthNumber = month.number();

        for (int i = 1; i < monthNumber; i++){
            dayNumber -=  numberOfDaysInMonth(year, Month.month(i));
        }
        return dayNumber;
    }

    static void showHolyDays(int year) {
        Month easterMonth = easterMonth(year);
        int easterDayInMonth = easterDay(year);
        int easterDayInYear = dayNumberInYear(easterDayInMonth,easterMonth,year);


        int carnivalDay = easterDayInYear-(7*7);
        Month carnivalMonth = monthInYearOfDayNumber(carnivalDay,year);
        int carnivalDayInMonth = dayInMonthOfDayNumber(carnivalDay,year);

        int goodFridayDay = easterDayInYear-2;
        Month goodFridayMonth = monthInYearOfDayNumber(goodFridayDay,year);
        int goodFridayDayInMonth = dayInMonthOfDayNumber(goodFridayDay,year);

        int whitsuntideDay = easterDayInYear+(7*7);
        Month whitsuntideMonth = monthInYearOfDayNumber(whitsuntideDay,year);
        int whitsuntideDayInMonth = dayInMonthOfDayNumber(whitsuntideDay,year);

        int ascensionDayDay = whitsuntideDay -10;
        Month ascensionDayMonth = monthInYearOfDayNumber(ascensionDayDay,year);
        int ascensionDayDayInMonth = dayInMonthOfDayNumber(ascensionDayDay,year);

        System.out.println(" The Easter date "+year+ " is:        "+easterDayInMonth+" "+easterMonth);
        System.out.println(" The Carnival date "+year+ " is:      "+carnivalDayInMonth+" "+carnivalMonth);
        System.out.println(" The Good Friday date "+year+ " is:   "+goodFridayDayInMonth+" "+goodFridayMonth);
        System.out.println(" The Whitsuntilde date "+year+ " is:  "+whitsuntideDayInMonth+" "+whitsuntideMonth);
        System.out.println(" The Ascension Day date "+year+ " is: "+ascensionDayDayInMonth+" "+ascensionDayMonth);



    }

    public static void main(String[] arguments) {
        System.out.println("Type in the year you want to know the holy days for:");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        showHolyDays(year);
        

    }
}
