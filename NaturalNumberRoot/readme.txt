This GitHub code is for my (Charan's) personal use. do not copy this code or anything in this repository. I am not responsible
for any resulting action by any governing body based upon the use of my (Charan's) personal repository.

Project: NaturalNumber Roots

Objectives

Familiarity with using NaturalNumber objects and methods.
Familiarity with using interval halving to invert a function.
The Problem

Your job is to implement the root static method for NaturalNumber using the interval halving root algorithm you developed in an earlier homework and lab for integer roots.

Setup

Create a new Eclipse project by copying ProjectTemplate or a previous project you have created, naming the new project NaturalNumberRoot.
Open the src folder of this project and then open (default package). As a starting point you can use any of the Java files. Rename it NaturalNumberRoot and delete the other files from the project.
Follow the link to NaturalNumberRoot.java, select all the code on that page and copy it to the clipboard; then open the NaturalNumberRoot.java file in Eclipse and paste the code to replace the file contents. Save the file.
Method

Edit NaturalNumberRoot.java to implement the root static method with the interval halving algorithm. Here is the contract:
/**
 * Updates {@code n} to the {@code r}-th root of its incoming value.
 * 
 * @param n
 *            the number whose root to compute
 * @param r
 *            root
 * @updates n
 * @requires r >= 2
 * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
 */
public static void root(NaturalNumber n, int r) {...}
For this method you can use any NaturalNumber methods except for NaturalNumber's own instance method root. Run the NaturalNumberRoot program to test your implementation of root.
Select your Eclipse project NaturalNumberRoot (not just some of the files, but the whole project), create a zip archive of it, and submit the zip archive to the Carmen dropbox for this project, as described in Submitting a Project.
Additional Activities

Here are some possible additional activities related to this project. Any extra work is strictly optional, for your own benefit, and will not directly affect your grade.

In Eclipse, copy NaturalNumberRoot.java to NaturalNumberRoot2.java. Write another implementation of the root method using interval halving with a smaller starting interval. It turns out that you can start not with the interval [0, #n + 1) but with the (generally much smaller) interval [10k, 10k+1), where k is the greatest integer less than or equal to (|#n|-1)/r. Here, |#n| denotes the discrete log (base 10) of #n (the discrete log (base 10) of a positive natural number is equal to the number of digits in the base 10 representation of the number; the discrete log of 0 is 0). Compare performance of the two implementations.