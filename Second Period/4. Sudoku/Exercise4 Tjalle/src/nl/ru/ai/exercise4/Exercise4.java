package nl.ru.ai.exercise4;

import java.util.ArrayList;

public class Exercise4 {

  public static void main(String[] args) {
    ArrayList<Integer> people = new ArrayList<Integer>();
    ArrayList<Integer> addedPeople = new ArrayList<Integer>();
    int weigth = 0;

    initList(people);

    System.out.println(getMax(people, weigth, addedPeople));
    System.out.println(addedPeople.toString());
  }

  /**
   * Initzializes the ArrayList with array entries
   * 
   * @param list
   */
  private static void initList(ArrayList<Integer> list) {
    assert list != null : "Array must be initzialized";

    int[] peopleArr = { 15, 24, 32, 40, 50, 60, 72, 80, 90 };

    for (int i = 0; i < peopleArr.length; i++) {
      list.add(peopleArr[i]);
    }

  }

  /**
   * Recursive program that finds a group of people that does not exceed the
   * maximum capacities
   * 
   * @param people
   * @param weigth
   * @param addedPeople
   * @return
   */
  private static int getMax(ArrayList<Integer> people, int weigth, ArrayList<Integer> addedPeople) {
    assert people != null : "Array must be initzialized";
    assert people.size() > 0 : "People array must not be empty";
    assert addedPeople != null : "addedPeople array must not be empty";

    int goal = 250;

    if (weigth + people.get(people.size() - 1) < goal && people.size() > 0) {
      addedPeople.add(people.get(people.size() - 1));
      weigth += people.get(people.size() - 1);
      people.remove(people.size() - 1);

      return getMax(people, weigth, addedPeople);
    }

    return weigth;

  }

}
