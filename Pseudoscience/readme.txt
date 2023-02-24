Project: Pseudoscience

Objectives

Familiarity with using while loops and static methods
Exposure to using nested while and for loops and arrays
The Problem

In his book Irreligion, the mathematician John Allen Paulos tells an amusing story about the Dutch astronomer Cornelis de Jager, "who concocted the following algorithm for personalized physical constants, [and] used it to advance a charming theory about the metaphysical properties of Dutch bicycles." First select any positive real-valued universal physical or mathematical constant that seems interesting to you, e.g., π, e, Planck's constant, the atomic weight of molybdenum, the boiling point of water in Kelvin, whatever you like. Call this constant μ. Then select any four positive real numbers not equal to 1 that have personal meaning to you, e.g., your favorite number, day or month or year of birth, age in fortnights or seconds, weight in stones or grams, height in furlongs or millimeters, number of children, house number, apartment number, zip code, last four digits of SSN, whatever you like. Call these four personal numbers w, x, y, and z.

Now consider the de Jager formula waxbyczd, where each of a, b, c, and d is one of the 17 numbers {-5, -4, -3, -2, -1, -1/2, -1/3, -1/4, 0, 1/4, 1/3, 1/2, 1, 2, 3, 4, 5}. The "charming theory" asserts that the de Jager formula with your four personal numbers can be used to approximate μ within a fraction of 1% relative error. For example, suppose you choose to approximate the mean distance from the earth to the moon in miles: μ = 238,900. And suppose you are an OSU sports fan, so your personal numbers are the number of wins in OSU's last national championship season (14), the seating capacity of Ohio Stadium (102,329), the year of Jesse Owens' four gold medals in Berlin (1936), and your jersey number when you played high school field hockey (13). Then the value of 14-5102329119361/2134 is about 239,103, which is within about 0.08% of μ.

Your job is to create a Java program that asks the user what constant μ should be approximated, and then asks in turn for each of the four personal numbers w, x, y, and z. The program should then calculate and report the values of the exponents a, b, c, and d that bring the de Jager formula as close as possible to μ, as well as the value of the formula waxbyczd and the relative error of the approximation to the nearest hundredth of one percent (see SimpleWriter print(double, int, boolean) for a method you may find useful for this). Note that your program must find the combination of exponents that minimizes the error of the approximation of μ and then print the exponents, best approximation, and corresponding relative error. (Essentially this program could be used to disprove the "charming theory" by finding μ, w, x, y, and z such that the best approximation of μ results in a relative error that is greater than 1%.)

Method

Create a new Eclipse project by copying ProjectTemplate or a previous project you have created, naming the new project Pseudoscience. In the src folder of this project and the default package, create a class called ABCDGuesser1.
Edit ABCDGuesser1.java to satisfy the problem requirements stated above, as well as the following additional requirements:
Use only while loops for iteration.
Check that the inputs provided by the user are valid, i.e., the input for μ is a positive real value and the inputs for w, x, y, and z are each a positive real value not equal to 1. You should implement and use two new static methods declared as follows:
/**
 * Repeatedly asks the user for a positive real number until the user enters
 * one. Returns the positive real number.
 * 
 * @param in
 *            the input stream
 * @param out
 *            the output stream
 * @return a positive real number entered by the user
 */
private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {...}
  
/**
 * Repeatedly asks the user for a positive real number not equal to 1.0
 * until the user enters one. Returns the positive real number.
 * 
 * @param in
 *            the input stream
 * @param out
 *            the output stream
 * @return a positive real number not equal to 1.0 entered by the user
 */
private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {...}
Note that you cannot assume the user will provide a number; the user can type pretty much anything. So your methods should read the input as a String (use SimpleReader nextLine() method), then make sure that the input is a real number (use FormatChecker.canParseDouble()), and finally convert the string to a double (use Double.parseDouble()).
Copy ABCDGuesser1.java to create ABCDGuesser2.java. Change it so the while loops in the main method are replaced by for loops (but you should not change the loops in the bodies of getPositiveDouble and getPositiveDoubleNotOne), and so it uses at least one additional private static method.
Select your Eclipse project Pseudoscience (not just some of the files, but the whole project), create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project.
Additional Activities

Here are some possible additional activities related to this project. Any extra work is strictly optional, for your own benefit, and will not directly affect your grade.

Explain why it is important to restrict the personal numbers to be "positive numbers not equal to 1".
Modify your program to explore empirically whether 3 personal constants would work (nearly) as well as 4 in the de Jager formula, and/or whether 5 would work (substantially) better.
