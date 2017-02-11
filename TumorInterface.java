package tumor;

import com.csvreader.CsvReader;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TumorInterface
  extends JFrame
{
  private String file1;
  private String file2;
  private int numberOfReads;
  private JButton jButton1;
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JTextField jTextField1;
  private JTextField jTextField2;
  private JTextField jTextField3;
  private JTextField jTextField4;
  private JTextField jTextField5;
  
  public TumorInterface()
  {
    initComponents();
  }
  
  private void initComponents()
  {
    this.jButton1 = new JButton();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jButton2 = new JButton();
    this.jLabel3 = new JLabel();
    this.jLabel4 = new JLabel();
    this.jLabel5 = new JLabel();
    this.jLabel6 = new JLabel();
    this.jTextField1 = new JTextField();
    this.jTextField2 = new JTextField();
    this.jTextField3 = new JTextField();
    this.jTextField4 = new JTextField();
    this.jLabel7 = new JLabel();
    this.jTextField5 = new JTextField();
    
    setDefaultCloseOperation(3);
    
    this.jButton1.setText("Choose Files");
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        TumorInterface.this.jButton1ActionPerformed(evt);
      }
    });
    this.jLabel1.setText("Select File1");
    
    this.jLabel2.setText("Select File 2");
    
    this.jButton2.setText("Run");
    this.jButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        TumorInterface.this.jButton2ActionPerformed(evt);
      }
    });
    this.jLabel3.setText("Base Pairs to Cut from 5' End");
    
    this.jLabel4.setText("Base Pairs to Cut from 3' End");
    
    this.jLabel5.setText("Base Pairs to Export from 5' End");
    
    this.jLabel6.setText("Base Pairs to Export from 3' End");
    
    this.jTextField1.setText("3");
    this.jTextField1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        TumorInterface.this.jTextField1ActionPerformed(evt);
      }
    });
    this.jTextField2.setText("3");
    
    this.jTextField3.setText("15");
    
    this.jTextField4.setText("15");
    
    this.jLabel7.setText("Minimum Base Pairs kept from 5' End");
    
    this.jTextField5.setText("50");
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addGap(51, 51, 51)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabel6)
      .addComponent(this.jLabel5)
      .addComponent(this.jLabel4)
      .addComponent(this.jLabel3))
      .addGap(46, 46, 46)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
      .addComponent(this.jTextField3)
      .addComponent(this.jTextField1)
      .addComponent(this.jTextField2, -1, 69, 32767))
      .addComponent(this.jTextField4, -2, 69, -2)))
      .addGroup(layout.createSequentialGroup()
      .addComponent(this.jLabel7)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jTextField5, -2, 69, -2)))
      .addGroup(layout.createSequentialGroup()
      .addComponent(this.jButton1)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jButton2, -2, 114, -2)
      .addGap(81, 81, 81)))
      .addGap(320, 320, 320))
      .addGroup(layout.createSequentialGroup()
      .addGap(57, 57, 57)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabel2, -1, -1, 32767)
      .addComponent(this.jLabel1, -1, -1, 32767))
      .addGap(521, 521, 521)));
    
    layout.setVerticalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addGap(24, 24, 24)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jButton1)
      .addComponent(this.jButton2))
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jLabel1, -2, 36, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jLabel2, -2, 39, -2)
      .addGap(39, 39, 39)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel3)
      .addComponent(this.jTextField1, -2, -1, -2))
      .addGap(18, 18, 18)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel4)
      .addComponent(this.jTextField2, -2, -1, -2))
      .addGap(18, 18, 18)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel5, -2, 15, -2)
      .addComponent(this.jTextField3, -2, -1, -2))
      .addGap(18, 18, 18)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel6)
      .addComponent(this.jTextField4, -2, 19, -2))
      .addGap(20, 20, 20)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel7)
      .addComponent(this.jTextField5, -2, -1, -2))
      .addContainerGap(35, 32767)));
    
    pack();
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setMultiSelectionEnabled(true);
    jFileChooser.setFileSelectionMode(0);
    jFileChooser.setCurrentDirectory(new File("/User"));
    int result = jFileChooser.showOpenDialog(new JFrame());
    if (result == 0)
    {
      File[] files = jFileChooser.getSelectedFiles();
      
      this.file1 = files[0].toString();
      this.file2 = files[1].toString();
      
      File f = new File(this.file1);
      File f2 = new File(this.file2);
      try
      {
        BufferedReader reader1 = new BufferedReader(new FileReader(this.file1));Throwable localThrowable3 = null;
        try
        {
          BufferedReader reader2 = new BufferedReader(new FileReader(this.file2));
          
          int lines1 = 0;
          while (reader1.readLine() != null) {
            lines1++;
          }
          this.numberOfReads = (lines1 / 4);
          System.out.println(lines1);
          
          int lines2 = 0;
          while (reader2.readLine() != null) {
            lines2++;
          }
          System.out.println(lines2);
          
          reader1.close();
          reader2.close();
        }
        catch (Throwable localThrowable1)
        {
          localThrowable3 = localThrowable1;throw localThrowable1;
        }
        finally
        {
          if (reader1 != null) {
            if (localThrowable3 != null) {
              try
              {
                reader1.close();
              }
              catch (Throwable localThrowable2)
              {
                localThrowable3.addSuppressed(localThrowable2);
              }
            } else {
              reader1.close();
            }
          }
        }
      }
      catch (FileNotFoundException ex)
      {
        Logger.getLogger(TumorInterface.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException ex)
      {
        Logger.getLogger(TumorInterface.class.getName()).log(Level.SEVERE, null, ex);
      }
      this.jLabel1.setText(this.file1);
      this.jLabel2.setText(this.file2);
    }
  }
  
  private void jButton2ActionPerformed(ActionEvent evt)
  {
    try
    {
      File outFile = new File(Tumor.createFile2(this.file1, this.file2, Integer.parseInt(this.jTextField1.getText()), Integer.parseInt(this.jTextField2.getText()), Integer.parseInt(this.jTextField3.getText()), Integer.parseInt(this.jTextField4.getText()), Integer.parseInt(this.jTextField5.getText()), this.numberOfReads));
      Desktop.getDesktop().open(outFile);
    }
    catch (IOException ex)
    {
      Logger.getLogger(TumorInterface.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void jTextField1ActionPerformed(ActionEvent evt) {}
  
  public static int countRows(CsvReader CsvFile)
    throws FileNotFoundException, IOException
  {
    int count = 0;
    while (CsvFile.readRecord()) {
      count++;
    }
    return count;
  }
}
