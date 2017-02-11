package tumor;

import java.io.IOException;

public class main
{
  public static void main(String[] args)
    throws IOException
  {
    TumorInterface gui = new TumorInterface();
    gui.setTitle("Stitching Algorithm");
    gui.setVisible(true);
  }
}
