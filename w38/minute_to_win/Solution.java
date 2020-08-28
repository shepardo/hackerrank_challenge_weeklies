/*
6 2
1 2 5 7 9 85
2
1 3 5 7 9 11
0 2 4 


1 3 6 8 10 12 
1 3 6 11 15 17
*/

/*
Submission history:
https://www.hackerrank.com/contests/w38/challenges/minute-to-win-it/submissions/code/1325991234
*/

// https://www.hackerrank.com/contests/w38/challenges/minute-to-win-it
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Interval
 //implements Comparable<Interval>
 {
  public int x0, xf;
  public Interval(int x0, int xf) {
    this.x0 = x0;
    this.xf = xf;
  }

  int getDistance() { return xf - x0; }

  /*public int compareTo(Interval i) {
    return getDistance() - i.getDistance();
  }*/
}

public class Solution {

    // Complete the minuteToWinIt function below.
    static int minuteToWinIt(int[] a, int k) {
        // Return the minimum amount of time in minutes.
      PriorityQueue<Interval> pq = new PriorityQueue<Interval>(10, Comparator.comparing(Interval::getDistance).reversed());

      int i_0 = 0;
      int j_0 = a.length - 1;
      int i_score = 0;
      int j_score = 0;

      while (true) {
        int i = i_0;
        while (i + 1 < a.length && a[i] + k == a[i + 1]) {
          i++;
        }
        i_score += i - i_0;
        if (i_score > 1) pq.add(new Interval(i_0, i - 1));

        int j = j_0;
        while (j - 1 >= 0 && a[j - 1] + k == a[j]) {
          j--;
        }
        j_score += j_0 - j;
        if (j_score > 1) pq.add(new Interval(j + 1, j_0));

        if (j - i <= 1) break;

        // repeat starting the new values of i, j
        i_0 = i + 1;
        j_0 = j - 1;
      }

      int minutes = 0;
      Interval it = pq.poll();
      if (it == null) {
        it = new Interval(0, 0);
      }
      int i = it.xf + 1;
      while (i < a.length) {
        if (a[i] - k != a[i - 1]) {
          a[i] = a[i - 1] + k;
          minutes++;
        }
        i++;
      }
      i = it.x0 - 1;
      while (i > 0) {
        if (a[i] + k != a[i + 1]) {
          a[i] = a[i + 1] - k;
          minutes++;
        }
        i--;
      }
      return minutes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minuteToWinIt(a, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
