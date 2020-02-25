import java.util.Set;
import java.util.SortedSet;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.ArrayList;

public class WordSearchClass implements WordSearchGame {
//constants
   private static final int MAX_NEIGHBORS = 8;
// lexicon
   TreeSet<String> wordlist;
   //grid
   String[][] board;
   //grid of integers
   int[][] order;
   // has loadLexicon method been correctly called (or implemented)
   boolean loadLexiconCalled;
   // grid of boolean of whether a letter has been visited
   boolean[][] visited;
   // length of one side of square grid
   int length;
   // position markers
   int i;
   int j;
   // 
   int[] tempx;
   int[] tempy;
   // stringbuilder global
   StringBuilder wordYet;
   List<Integer> pathway;
 /**
*
* Initializes boggle board to 4 x 4 grid of letters.
* length is 4.; visited is all false.
*
*/
   public WordSearchClass() {
      board = new String[][]{{"E", "E", "C", "A"}, {"A", "L", "E", "P"}, {"H", 
                                 "N", "B", "O"}, {"Q", "T", "T", "Y"}};
      order = new int[4][4];
      visited = new boolean[4][4]; 
      length = 4; 
      wordlist = new TreeSet<String>(); 
      for (int a = 0; a < length; a++) {
         for (int b = 0; b < length; b++) {
            order[a][b] = ((length* b) + a);
         }
      }                       
   }
  /**
  *
  *Loads lexicon into wordlist.
  *
  */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException("Must be non-empty file name");
      }
      try {
         Scanner scan = new Scanner(new File(fileName));
         while (scan.hasNext()) {
            String word = scan.next();
            scan.nextLine().toUpperCase();
            wordlist.add(word.toUpperCase());
         } 
         scan.close();       
      }
      catch(Exception e) {
         throw new IllegalArgumentException("File cannot be opened");
      } 
      loadLexiconCalled = true;   
   }
   /**
   *
   *
   *
   */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      boolean isSquare = true;
      
      Double mod = (letterArray.length % (Math.sqrt(letterArray.length)));
      if (mod != 0) {
         isSquare = false;
      }
      if (isSquare == false) {
         throw new IllegalArgumentException();
      }
      Integer side = (int) Math.round(Math.sqrt(letterArray.length));
      String[][] table = new String[side][side];
      order = new int[side][side];
      visited = new boolean[side][side];
      length = side; // setting convenience field, length
      for (int i = 0; i < length; i++) {
         for (int j = 0; j < length; j++) {
            table[j][i] = letterArray[(length*j)+ i].toUpperCase();
            order[j][i] = (length*j + i); // double check
         }
      }
      board = table;
   }
   /**
   *
   *
   *
   *
   */
   public String getBoard() { //ungraded
      String output = new String();
      for (int k = 0; k < length; k++) {
         for (int m = 0; m < length; m++) {
            output += ("(" + board[m][k] + ")");
         
         }
      }
      return output;
   }
   /**
   *
   *
   *
   */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      clear(); // unsure
      SortedSet<String> validWords = new TreeSet<String>(); 
      for (String word: wordlist) {
         List<Integer> foundPath = isOnBoard(word); 
         if (foundPath != null && foundPath.size() != 0) {
            if (word.length() >= minimumWordLength) {
               validWords.add(word);
            }
         }
      
      }
      return validWords;
   }
   /**
   *
   *
   *
   *
   */
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      int score = 0;
      for (String word : words) {
         if (word.length() > minimumWordLength) {
            score += (word.length() - minimumWordLength + 1);
         } 
      }
      return score;
   }
   /**
   *
   *
   *
   */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      return (wordlist.contains(wordToCheck));
   }
   /**
   *
   *
   *
   */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      String ceiling = wordlist.ceiling(prefixToCheck.toUpperCase()); // check!
      return (ceiling != null || ceiling.toUpperCase().compareTo(prefixToCheck) < 0);
   }
   /**
   *
   *
   *
   */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (!loadLexiconCalled) {
         throw new IllegalStateException();
      }
      clear();
      pathway = new ArrayList<Integer>();
      for (int k = 0; k < length; k++) {
         for (int m = 0; m < length; m++) { // check
            clear();// delete?
            j = m;
            i = k;
            if (wordStartsWithSame(board[i][j], wordToCheck)) {
               StringBuilder wordYet =  new StringBuilder();
               pathway = new ArrayList<Integer>();
                // note
               if (dfsOneWord(i, j, wordToCheck, wordYet, pathway)) {
                  return pathway;
               }
                
            }
         }
      }
      return pathway;
   }
   /**
   *
   *Mark position as visited
   *
   */
   private void visit() {
      visited[i][j] = true;
   
   }
   /**
   *
   *Mark position as unvisited
   *
   */
   private void unvisit(int c, int d) {
      visited[c][d] = false;
   }
   /**
   *Increment, then Mark position with integer.
   *
   */
   private void process() {
      wordYet.append(board[i][j]);
      pathway.add(order[i][j]); 
   }
   /**
   *
   *
   *
   */
   private void unprocess() {
      wordYet.deleteCharAt(wordYet.toString().length() - 1); // interfere
      pathway.remove(pathway.size() - 1);
   }
   /**
   *
   *Checks to make sure that the position is wihtin the grid.
   *
   */
   private boolean isValid() {
      return ((i >= 0) && (i < length) && (j >= 0) && (j < length));
   }
   /**
   *
   *returns the boolean value at a particular letter[i][j].
   *
   */
   private boolean isVisited() {
      return visited[i][j];
   }
   /**
   *
   *Returns an array of strings of all the neighbors to a position[i][j].
   *
   */
   private int neighbors() {
      String[] nbrs = new String[MAX_NEIGHBORS];
      tempx = new int[MAX_NEIGHBORS];
      tempy = new int[MAX_NEIGHBORS]; 
      int counter = 0;
      for (int u = -1; u <= 1; u++) {
         for (int v = -1; v <= 1; v++) {
            if (!((u == 0) && (v == 0))) {
               if (isInBounds(i + v, j + u)) {
                  String neighbor = board[i + v][j + u];
                  nbrs[counter] = neighbor;
                  tempy[counter] = i + v;
                  tempx[counter] = j + u;
                  counter++;  
               }
            }
         }
      }
      return counter;
   }
           /**
  *
  *
  *
  */       
   private boolean dfsOneWord(int c, int d, String searchWord, 
      StringBuilder wordSoFar, List<Integer> path) {
      i = c;
      j = d;
      pathway = path;
      wordYet = wordSoFar;
      String position = board[i][j];// ji
      if (!isValid()) {
         return false;
      }
      if (isVisited()) {
         return false;
      }
      StringBuilder duplicate = new StringBuilder();
      duplicate.append(wordYet.toString());
      if (!searchWord.contains(duplicate.append(position))) { 
         return false;
      }
      visit();
      process();
      if (wordYet.toString().equals(searchWord)) {
         return true;
      }
      else {
         int family = neighbors();
         int[] x = Arrays.copyOf(tempx, family);
         int[] y = Arrays.copyOf(tempy, family);
         for (int r = 0; r < family; r++) {
            int neighborx = x[r];
            int neighbory = y[r];
            if (dfsOneWord(neighbory, neighborx, searchWord, wordYet, pathway)) {
               return true;
            } 
         }
         unvisit(c, d);
         unprocess();
         return false;
      }
   }
   public boolean wordStartsWithSame(String str1, String str2) {
      String letter1 = String.valueOf(str1.charAt(0));
      String letter2 = String.valueOf(str2.charAt(0));
      return (letter1.equalsIgnoreCase(letter2));
   
   }
   /**
   *
   *
   *
   */
   private void clear() {
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
      //wordYet = new StringBuilder();
      //pathway = new ArrayList<Integer>();
      //i = 0; // not sure 
      //j = 0; // not sure
   }
   /**
   *
   *
   *
   */
   public String gridToString() {
      StringBuilder sb = new StringBuilder();
      for (int p = 0; p < length*length; p++) {
         sb.append("*");
      }
      sb.append("\n");
      for (String[] row: board) {
         for (String entry : row) {
            sb.append(entry + "  ");
         }
         sb.append("\n");
      }
      for (int q = 0; q < length*length; q++) {
         sb.append("*");
      }
      sb.append("\n");
      return sb.toString();
   }
 /**
 *
 *
 *
 */ 
   private boolean isInBounds(int a, int b) {
      int w = a;
      int s = b;
      return (w < length && w >= 0 && s < length && s >= 0);
   }
        
   
   



}