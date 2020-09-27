package nl.ru.ai.easter;

public class Easter {
  static boolean isLeapYear(int year) {
    // implement this function

    if (year % 4 == 0) {
      if (year % 100 == 0) {
        if (year % 400 == 0) {
          return true;
        }
        return false;
      }
      return true;
    }
    return false;
  }

  static int numberOfDaysInMonth(int year, Month month) {
    // implement this function
    switch (month) {
      case APRIL:
      case JUNE:
      case SEPTEMBER:
      case NOVEMBER:
        return 30;
      case FEBRUARY:
        Boolean leapyear = isLeapYear(year);
        if (leapyear) {
          return 29;
        }
        return 28;

      default:
        return 31;
    }
  }

  static Month easterMonth(int year) {
    // implement this function
    int a = year % 19;
    int b = year / 100;
    int c = year % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int L = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * L) / 451;
    int monthNumber = (h + L - 7 * m + 114) / 31;
    return Month.month(monthNumber);
  }

  static int easterDay(int year) {
    // implement this function
    int a = year % 19;
    int b = year / 100;
    int c = year % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int k = c % 4;
    int L = (32 + 2 * e + 2 * i - h - k) % 7;
    int m = (a + 11 * h + 22 * L) / 451;
    return ((h + L - 7 * m + 114) % 31) + 1;
  }

  static int dayNumberInYear(int day, Month month, int year) {
    // implement this function
    int monthNumber = month.number();
    int dayNumber = 0;
    for (int i = 1; i < monthNumber; i++) {
      dayNumber += numberOfDaysInMonth(year, Month.month(i));
    }

    return dayNumber + day;
  }

  static Month monthInYearOfDayNumber(int dayNumber, int year) {
    // implement this function
    for (int i = 1; i <= 12; i++) {
      dayNumber -= numberOfDaysInMonth(year, Month.month(i));
      if (dayNumber <= 0) {
        return Month.month(i);
      }
    }

    return Month.JANUARY;

  }

  static int dayInMonthOfDayNumber(int dayNumber, int year) {
    // implement this function
    Month month = monthInYearOfDayNumber(dayNumber, year);
    int monthNumber = month.number();

    for (int i = 1; i < monthNumber; i++) {
      dayNumber -= numberOfDaysInMonth(year, Month.month(i));
    }
    return dayNumber;
  }

  static void holyPrinter(String occasion, int year, Month month, int day) {

    System.out.println(occasion + " is on " + day + " " + month + " in " + year);

  }

  static void showHolyDays(int year) {

    int easterDayInMonth = easterDay(year);
    Month easterMonth = easterMonth(year);

    int easterDayNumber = dayNumberInYear(easterDayInMonth, easterMonth, year);

    int carnivalDayNumber = easterDayNumber - 7 * 7;
    int carnivalDayInMonth = dayInMonthOfDayNumber(carnivalDayNumber, year);
    Month carnvialMonth = monthInYearOfDayNumber(carnivalDayNumber, year);

    int goodFridayDayNumber = easterDayNumber - 2;
    int goodFridayDayInMonth = dayInMonthOfDayNumber(goodFridayDayNumber, year);
    Month goodFridayMonth = monthInYearOfDayNumber(goodFridayDayNumber, year);

    int ascensionDay_DayNumber = easterDayNumber + 39;
    int ascensionDay_DayInMonth = dayInMonthOfDayNumber(ascensionDay_DayNumber, year);
    Month ascensionDay_Month = monthInYearOfDayNumber(ascensionDay_DayNumber, year);

    int whitsuntideDayNumber = easterDayNumber + 7 * 7;
    int whitsuntideDayInMonth = dayInMonthOfDayNumber(whitsuntideDayNumber, year);
    Month whitsuntideMonth = monthInYearOfDayNumber(whitsuntideDayNumber, year);

    holyPrinter("Easter", year, easterMonth, easterDayInMonth);
    holyPrinter("Carnival", year, carnvialMonth, carnivalDayInMonth);
    holyPrinter("Good Friday", year, goodFridayMonth, goodFridayDayInMonth);
    holyPrinter("Ascension Day", year, ascensionDay_Month, ascensionDay_DayInMonth);
    holyPrinter("Whitsuntide", year, whitsuntideMonth, whitsuntideDayInMonth);

    // implement this function
  }

  static void showLeapYears() {
    System.out.println(isLeapYear(1900)); // false
    System.out.println(isLeapYear(1999)); // false
    System.out.println(isLeapYear(2000)); // true
    System.out.println(isLeapYear(2012)); // true
    System.out.println(isLeapYear(2013)); // false
    System.out.println(isLeapYear(2014)); // false
    System.out.println(isLeapYear(2015)); // false
    System.out.println(isLeapYear(2016)); // true
  }

  static int getDayOfTheWeek(int year, Month monthName, int day) {

    // Zeller implementation

    int month = monthName.number();

    if (month < 3) {
      month += 12;
      year -= 1;
    }

    int k = year % 100;
    int j = year / 100;

    int dayOfWeek = ((day + (((month + 1) * 26) / 10) + k + (k / 4) + (j / 4)) + (5 * j)) % 7;

    return (dayOfWeek + 6) % 7;

  }

  static void FirstRowGenerator(int startDate1, int startDate2, int startDate3, Month month1, Month month2,
      Month month3, int year) {
    System.out.println(dateRowGenerator(startDate1, 1, month1, year) + " "
        + dateRowGenerator(startDate2, 1, month2, year) + " " + dateRowGenerator(startDate3, 1, month3, year));
  }

  static void nThRowGenerator(int offset, int startDate1, int startDate2, int startDate3, Month month1, Month month2,
      Month month3, int year) {
    System.out.println(dateRowGenerator(0, offset - startDate1, month1, year) + " "
        + dateRowGenerator(0, offset - startDate2, month2, year) + " "
        + dateRowGenerator(0, offset - startDate3, month3, year));
  }

  static void allRowsGenerator(int year, Month month1, Month month2, Month month3) {
    int startDate1 = getDayOfTheWeek(year, month1, 1);
    int startDate2 = getDayOfTheWeek(year, month2, 1);
    int startDate3 = getDayOfTheWeek(year, month3, 1);

    FirstRowGenerator(startDate1, startDate2, startDate3, month1, month2, month3, year);
    int ct = 8;
    for (int i = 0; i < 5; i++) {
      nThRowGenerator(ct, startDate1, startDate2, startDate3, month1, month2, month3, year);
      ct += 7;
    }
  }

  static void threeMonthGenerator(int year, Month month1, Month month2, Month month3) {
    System.out.println();
    System.out.println(month1 + "                     " + month2 + "                     " + month3);
    System.out.println("Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa");
    allRowsGenerator(year, month1, month2, month3);
    System.out.println();

  }

  static String dateRowGenerator(int startPos, int startNumber, Month month, int year) {
    int max = numberOfDaysInMonth(year, month);
    String dateRow = "";
    for (int i = 0; i < 7; i++) {
      if (i >= startPos && startNumber <= max) {
        if (startNumber < 10) {
          dateRow += " " + startNumber + " ";
        } else {
          dateRow += startNumber + " ";
        }
        startNumber++;
      } else {
        dateRow += "   ";
      }
    }

    return dateRow;
  }

  static void showYear(int year) {
    System.out.println("");
    System.out.println("                            " + year + "                               ");
    int counter = 1;
    for (int i = 0; i < 4; i++) {
      threeMonthGenerator(year, Month.month(counter), Month.month(counter + 1), Month.month(counter + 2));
      counter += 3;
    }

  }

  public static void main(String[] arguments) {
    // implement this function

    // These to were neccesairy for the exercises
    // Comment and uncomment them accordingly
    //
    // showLeapYears();
    // showHolyDays(2020);

    showYear(1969);

  }
}
