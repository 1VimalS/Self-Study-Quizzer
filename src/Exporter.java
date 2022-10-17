//CSV File class
import java.io.FileNotFoundException; 
import javax.swing.JOptionPane; 
import java.io.PrintWriter;
public class Exporter {
    public Exporter() {
        final String FILENAME = "TestBankData.csv"; 
        writeToCSV(FILENAME);
    }

    private void writeToCSV(String FILENAME) { 
        try(PrintWriter pw = new PrintWriter(FILENAME)) {
            pw.write("Questions" + ",");
            pw.write("Answers" + ",\n");
            for (int i = 0; i < GUI.getOriginalIndices().size(); i++) {
                pw.write(GUI.getOriginalIndices().get(i) + ",");
                pw.write(GUI.getOriginalTest().get(GUI.getOriginalIndices().get(i)) + ",\n"); 
            }
            pw.write("Attempts" + ","); pw.write("Percentage Correct" + ",\n");
            for (int i = 0; i < GUI.getDataIndices().size(); i++) { 
                pw.write(GUI.getDataIndices().get(i).toString() + ",");
                pw.write(GUI.getData().get(GUI.getDataIndices().get(i)).toString() + ",\n"); 
            }
            pw.close(); 
        }
        catch(FileNotFoundException e) { 
            JOptionPane.showMessageDialog(null, "Unable to create/write file.");
        }
    } 
}