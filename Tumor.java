package tumor;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Tumor
{
  public static String createFile1(String file1, String file2, int cut5End, int cut3End, int export5End, int export3End, int keepMinFromStart, int numberOfReads)
    throws IOException
  {
    HashMap<String, Integer> frequencyMap = new HashMap();
    HashMap<String, String> basePairMap = new HashMap();
    
    CsvWriter out = new CsvWriter("tumor_cell_comparison.csv");
    
    String[] title = new String[2];
    title[0] = "Stitched_Read";
    title[1] = "Frequency";
    
    out.writeRecord(title);
    
    FileReader fileReader1 = new FileReader(file1);
    BufferedReader textReader1 = new BufferedReader(fileReader1);
    FileReader fileReader2 = new FileReader(file2);
    BufferedReader textReader2 = new BufferedReader(fileReader2);
    while (textReader1.ready())
    {
      String saveLocation1 = textReader1.readLine();
      String saveLocation2 = textReader2.readLine();
      
      String R1 = textReader1.readLine();
      String R2 = textReader2.readLine();
      
      String R2reverseComplemented = reverseCompliment(R2);
      String intersection = longestCommonSubstring(R1, R2reverseComplemented);
      
      textReader1.readLine();
      textReader2.readLine();
      
      String qCheck1 = textReader1.readLine();
      String qCheck2 = textReader2.readLine();
      
      String stitched = R1.substring(0, R1.length() - intersection.length()) + intersection + R2reverseComplemented.substring(intersection.length(), R2reverseComplemented.length());
      String stitchedCut = stitched.substring(cut5End, stitched.length() - cut3End);
      if (!"".equals(intersection))
      {
        basePairMap.put(intersection + "R1", R1);
        
        basePairMap.put(intersection, stitchedCut);
        if (frequencyMap.containsKey(intersection)) {
          frequencyMap.put(intersection, Integer.valueOf(((Integer)frequencyMap.get(intersection)).intValue() + 1));
        } else {
          frequencyMap.put(intersection, Integer.valueOf(1));
        }
      }
    }
    Iterator iter = frequencyMap.keySet().iterator();
    while (iter.hasNext())
    {
      String intersection = (String)iter.next();
      String stitched = (String)basePairMap.get(intersection);
      
      out.write(stitched);
      out.write(((Integer)frequencyMap.get(intersection)).toString());
      
      out.endRecord();
    }
    out.close();
    
    return "tumor_cell_comparison.csv";
  }
  
  public static String createFile2(String file1, String file2, int cut5End, int cut3End, int export5End, int export3End, int keepMinFromStart, int numberOfReads)
    throws IOException
  {
    int totalFrequency = 0;
    
    HashMap<String, Integer> frequencyMap = new HashMap();
    HashMap<String, String> basePairMap = new HashMap();
    
    CsvWriter out = new CsvWriter("tumor_cell_comparison.csv");
    
    String[] title = new String[7];
    title[0] = "Stitched_Read";
    title[1] = "Frequency";
    title[2] = "5'end_Primer";
    title[3] = "3'end_Primer";
    title[4] = "Total_Reads";
    title[5] = "Total_Frequency";
    title[6] = "Survival_Rate";
    
    out.writeRecord(title);
    
    FileReader fileReader1 = new FileReader(file1);
    BufferedReader textReader1 = new BufferedReader(fileReader1);
    FileReader fileReader2 = new FileReader(file2);
    BufferedReader textReader2 = new BufferedReader(fileReader2);
    while (textReader1.ready())
    {
      String saveLocation1 = textReader1.readLine();
      String saveLocation2 = textReader2.readLine();
      
      String R1 = textReader1.readLine();
      String R2 = textReader2.readLine();
      
      textReader1.readLine();
      textReader2.readLine();
      
      String qCheck1 = textReader1.readLine();
      String qCheck2 = textReader2.readLine();
      
      String trimmedRead1 = trim(R1, qCheck1, keepMinFromStart);
      String trimmedRead2 = trim(R2, qCheck2, keepMinFromStart);
      
      String reverseComplementedRead2 = reverseCompliment(trimmedRead2);
      String overlap = longestCommonSubstring(trimmedRead1, reverseComplementedRead2);
      
      String stitched = trimmedRead1.substring(0, trimmedRead1.length() - overlap.length()) + overlap + reverseComplementedRead2.substring(overlap.length(), reverseComplementedRead2.length());
      String stitchedCut = stitched.substring(cut5End, stitched.length() - cut3End);
      if (!"".equals(overlap))
      {
        basePairMap.put(overlap + "R1", R1);
        
        basePairMap.put(overlap, stitchedCut);
        if (frequencyMap.containsKey(stitchedCut))
        {
          frequencyMap.put(stitchedCut, Integer.valueOf(((Integer)frequencyMap.get(stitchedCut)).intValue() + 1));
          totalFrequency++;
        }
        else
        {
          frequencyMap.put(stitchedCut, Integer.valueOf(1));
          totalFrequency++;
        }
      }
    }
    Iterator iter = frequencyMap.keySet().iterator();
    int count = 0;
    while (iter.hasNext())
    {
      String stitched = (String)iter.next();
      String stitchedWithoutBarcode = stitched.substring(6, stitched.length() - 6);
      
      out.write(stitched);
      out.write(((Integer)frequencyMap.get(stitched)).toString());
      out.write(stitched.substring(0, export5End));
      out.write(reverseCompliment(stitched.substring(stitched.length() - export3End, stitched.length())));
      if (count == 0)
      {
        out.write(Integer.toString(numberOfReads));
        out.write(Integer.toString(totalFrequency));
        out.write(Float.toString(totalFrequency / numberOfReads));
      }
      out.endRecord();
      count++;
    }
    out.close();
    
    return "tumor_cell_comparison.csv";
  }
  
  private static String trim(String R, String qCheck, int keepMin)
  {
    int i = 0;int j = 0;
    for (i = keepMin; i < qCheck.length() - 2; i++) {
      if ((qCheck.toCharArray()[i] < '5') && (qCheck.toCharArray()[(i + 1)] < '?') && (qCheck.toCharArray()[(i + 2)] < '5')) {
        break;
      }
    }
    for (j = i + 1; j >= keepMin; j--) {
      if ((qCheck.toCharArray()[j] >= '5') && (qCheck.toCharArray()[(j - 1)] >= '?') && (qCheck.toCharArray()[(j - 2)] >= '5')) {
        break;
      }
    }
    i = j;
    String result = R.substring(0, i + 1);
    return result;
  }
  
  private static String reverseCompliment(String seq)
  {
    String reverse = "";
    String reverseCompliment = "";
    for (int i = seq.length() - 1; i >= 0; i--) {
      reverse = reverse + seq.charAt(i);
    }
    for (int i = 0; i < seq.length(); i++) {
      switch (reverse.charAt(i))
      {
      case 'T': 
        reverseCompliment = reverseCompliment + 'A';
        break;
      case 'A': 
        reverseCompliment = reverseCompliment + 'T';
        break;
      case 'G': 
        reverseCompliment = reverseCompliment + 'C';
        break;
      case 'C': 
        reverseCompliment = reverseCompliment + 'G';
      }
    }
    return reverseCompliment;
  }
  
  private static String longestCommonSubstring(String S1, String S2)
  {
    int Start = 0;
    int Max = 0;
    for (int i = 0; i < S1.length(); i++) {
      for (int j = 0; j < S2.length(); j++)
      {
        int x = 0;
        while (S1.charAt(i + x) == S2.charAt(j + x))
        {
          x++;
          if (i + x < S1.length()) {
            if (j + x >= S2.length()) {
              break;
            }
          }
        }
        if (x > Max)
        {
          Max = x;
          Start = i;
        }
      }
    }
    String longest = S1.substring(Start, Start + Max);
    if (longest.equals(S1.substring(S1.length() - longest.length(), S1.length())))
    {
      if (longest.equals(S2.substring(0, longest.length()))) {
        return longest;
      }
      return "";
    }
    return "";
  }
}
