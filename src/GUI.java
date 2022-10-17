//This class is the main class that inherits from App and provides frontend construction as well as the algorithms used for recursive processes
import java.awt.Color;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; 
import javax.swing.JTextArea;

public class GUI extends App implements ActionListener {
    //assigned question
    private String practiceQuestion;
    //attempted answer
    private String practiceAnswer;
    //correct answer count
    private int correct = 0;
    //attempt count
    private int attempts = 1;
    //amount of questions in current bank
    private int arraySize;
    //hashmap for performance and csv
    private static HashMap<Integer, Double> data = new HashMap<Integer, Double>();
    //indices for data
    private static ArrayList<Integer> dataIndices = new ArrayList<Integer>(); //hashmap for original testbank
    private static HashMap<String, String> originalTest = new HashMap<String, String>();
    //indices for original
    private static ArrayList<String> originalIndices = new ArrayList<String>();

    public static HashMap<Integer, Double> getData() {
        return data; 
    }

    public static ArrayList<Integer> getDataIndices() {
        return dataIndices; 
    }

    public static HashMap<String, String> getOriginalTest() {
        return originalTest; 
    }

    public static ArrayList<String> getOriginalIndices() {
        return originalIndices;
    }

    //randomly selects question from bank
    public void assignQuestion(ArrayList<String> a) {
        practiceQuestion = selectRandomKey(a); 
    }

    //checks question and does more
    public void checkQuestion(HashMap<String, String> h, ArrayList<String> a) {
        //retrieve answer string from Area 
        practiceAnswer = answer.getText(); 
        answer.setText("");
        if (practiceAnswer.equals("")) {
            JOptionPane.showMessageDialog(null, "Please input an answer");
        }
        else {
            //if inputted answer = correct answer
            if (practiceAnswer.equalsIgnoreCase(h.get(practiceQuestion))) {
                correct++;
                JOptionPane.showMessageDialog(null, "Correct!"); 
                //remove question from bank 
                remove(h.get(practiceQuestion), h, a);
            } 
            else {
                JOptionPane.showMessageDialog(null, "Incorrect."); 
                //add question to incorrect bank 
                add(practiceQuestion, h.get(practiceQuestion), getNewTestBank(), getNewIndices());
                //remove question from bank
                remove(h.get(practiceQuestion), h, a);
            }
            //recursive loop - checks if there's still unanswered questions

        
        
    
            if (!h.isEmpty()) {
                assignQuestion(a); 
                frame.setVisible(false); 
                slide31();
            } else {
                //replace TestBank with the incorrect TestBank 
                getTestBank().putAll(getNewTestBank());
                for (int i = 0; i < getNewIndices().size(); i++) { 
                    getIndices().add(getNewIndices().get(i));
                }
                getNewTestBank().clear(); getNewIndices().clear();
                slide4();
                //add data values here - takes attempts and amount correct in each attempt
                data.put(attempts, (double)correct / arraySize * 100);
                dataIndices.add(attempts); 
            }
        }
    }
    //slide 1 -- add questions
    JFrame frame = new JFrame();
    JTextArea title = new JTextArea(); 
    JTextArea questionInput = new JTextArea(); 
    JTextArea answerInput = new JTextArea(); 
    JButton add = new JButton();
    JButton viewTest = new JButton();
    JButton done1 = new JButton();

    //slide 2 -- do you want to practice test 
    JLabel practiceTest = new JLabel(); 
    JButton yes2 = new JButton(); 
    JButton no2 = new JButton();

    //slide 3-1 -- assigned question and answer input 
    JTextArea question = new JTextArea(); 
    JTextArea answer = new JTextArea(); 
    JButton next31 = new JButton();

    //slide 3-2 -- thank you for using program 
    JLabel thanks = new JLabel(); 
    JButton done32 = new JButton();

    //slide 4 -- would u like to practice incorrect questions? 
    JTextArea incorrectPrompt = new JTextArea();
    private JButton incorrectOption = new JButton(); 
    private JButton keep = new JButton();
    private JButton done4 = new JButton();

    //slide 5 -- no more incorrect questions - CSV and performance 
    private JTextArea noMore = new JTextArea();
    private JButton viewPerformance = new JButton();
    private JButton exportToCSV = new JButton();
    private JButton done5 = new JButton();

    public GUI() { //main frame
        frame.setTitle("Self Study Practice System"); 
        frame.setSize(500, 500);
        frame.setLocation(500, 200); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.getContentPane().setBackground(new Color(120, 120, 120)); 
        frame.setLayout(null);
        frame.setResizable(false);

        //slide 1 -- add questions
        title.setBounds(100, 10, 300, 50);
        title.setBackground(new Color (50, 50, 50)); 
        title.setForeground(new Color(200, 200, 200)); 
        title.setBorder(BorderFactory.createBevelBorder(1)); 
        title.setLineWrap(true);
        title.setEditable(false);
        title.setText("Please add questions and answers to your Test Bank. \nClick Add for every question and answer");

        questionInput.setBounds(100, 100, 300, 100); 
        questionInput.setBackground(new Color (50, 50, 50)); 
        questionInput.setForeground(new Color(200, 200, 200)); 
        questionInput.setBorder(BorderFactory.createBevelBorder(1)); 
        questionInput.setLineWrap(true); 
        questionInput.setEditable(true); 
        questionInput.setText("Type question here");

        answerInput.setBounds(100, 250, 300, 100); 
        answerInput.setBackground(new Color (50, 50, 50)); 
        answerInput.setForeground(new Color(200, 200, 200)); 
        answerInput.setBorder(BorderFactory.createBevelBorder(1)); 
        answerInput.setLineWrap(true); answerInput.setEditable(true);
        answerInput.setText("Type answer here");

        add.setBounds(100, 400, 50, 50); 
        add.setBackground(new Color (50, 50, 50)); 
        add.setForeground(new Color(200, 200, 200));
        add.setBorder(BorderFactory.createBevelBorder(1)); 
        add.setText("Add");
        add.addActionListener(this);

        viewTest.setBounds(200, 400, 100, 50); 
        viewTest.setBackground(new Color (50, 50, 50)); 
        viewTest.setForeground(new Color(200, 200, 200)); 
        viewTest.setBorder(BorderFactory.createBevelBorder(1)); 
        viewTest.setText("View Test Bank"); 
        viewTest.addActionListener(this);

        done1.setBounds(350, 400, 50, 50); 
        done1.setBackground(new Color (50, 50, 50)); 
        done1.setForeground(new Color(200, 200, 200)); 
        done1.setBorder(BorderFactory.createBevelBorder(1)); 
        done1.setText("Done"); done1.addActionListener(this);

        //slide 2 -- do you want to practice test 
        practiceTest.setBounds(100, 100, 200, 20); 
        practiceTest.setBackground(new Color (50, 50, 50)); 
        practiceTest.setForeground(new Color(0, 0, 0)); 
        practiceTest.setBorder(BorderFactory.createBevelBorder(1)); 
        practiceTest.setText("Would you like to practice test?");

        yes2.setBounds(100, 400, 50, 50); 
        yes2.setBackground(new Color (50, 50, 50)); 
        yes2.setForeground(new Color(200, 200, 200)); 
        yes2.setBorder(BorderFactory.createBevelBorder(1)); 
        yes2.setText("Yes");
        yes2.addActionListener(this);

        no2.setBounds(300, 400, 50, 50); 
        no2.setBackground(new Color (50, 50, 50)); 
        no2.setForeground(new Color(200, 200, 200)); 
        no2.setBorder(BorderFactory.createBevelBorder(1)); 
        no2.setText("No");
        no2.addActionListener(this);

        //slide 3-1 -- assigned question and answer input 
        question.setBounds(100, 100, 300, 100); 
        question.setBackground(new Color (50, 50, 50)); 
        question.setForeground(new Color(200, 200, 200)); 
        question.setBorder(BorderFactory.createBevelBorder(1)); 
        question.setLineWrap(true); 
        question.setEditable(false);

        answer.setBounds(100, 250, 300, 100); 
        answer.setBackground(new Color (50, 50, 50));
        answer.setForeground(new Color(200, 200, 200)); 
        answer.setBorder(BorderFactory.createBevelBorder(1)); 
        answer.setLineWrap(true);
        answer.setEditable(true);
        answer.setText("Type answer here");

        next31.setBounds(250, 400, 50, 50); 
        next31.setBackground(new Color (50, 50, 50));
        next31.setForeground(new Color(200, 200, 200));
        next31.setBorder(BorderFactory.createBevelBorder(1)); 
        next31.setText("Next"); 
        next31.addActionListener(this);

        //slide 3-2 -- thank you for using program 
        thanks.setBounds(100, 100, 300, 100); 
        thanks.setBackground(new Color (50, 50, 50)); 
        thanks.setForeground(new Color(0, 0, 0)); 
        thanks.setBorder(BorderFactory.createBevelBorder(1));
        thanks.setText("Thank you for using program. Press Done to Exit");
        done32.setBounds(250, 400, 50, 50); 
        done32.setBackground(new Color (50, 50, 50));
        done32.setForeground(new Color(200, 200, 200));
        done32.setBorder(BorderFactory.createBevelBorder(1)); 
        done32.setText("Done"); 
        done32.addActionListener(this);

        //slide 4 -- would u like to practice incorrect questions? 
        incorrectPrompt.setBounds(100, 100, 300, 50); 
        incorrectPrompt.setBackground(new Color (50, 50, 50)); 
        incorrectPrompt.setForeground(new Color(200, 200, 200)); 
        incorrectPrompt.setBorder(BorderFactory.createBevelBorder(1)); 
        incorrectPrompt.setLineWrap(true); 
        incorrectPrompt.setEditable(false); 
        incorrectPrompt.setText("Would you like to practice incorrect questions" + "\n" + "or keep practicing original set?");

        incorrectOption.setBounds(150, 175, 200, 50); 
        incorrectOption.setBackground(new Color (50, 50, 50)); 
        incorrectOption.setForeground(new Color(200, 200, 200)); 
        incorrectOption.setBorder(BorderFactory.createBevelBorder(1)); 
        incorrectOption.setText("Practice incorrect questions"); 
        incorrectOption.addActionListener(this);

        keep.setBounds(150, 225, 200, 50); 
        keep.setBackground(new Color (50, 50, 50)); 
        keep.setForeground(new Color(200, 200, 200)); 
        keep.setBorder(BorderFactory.createBevelBorder(1)); 
        keep.setText("Keep practicing original set");
        keep.addActionListener(this);

        done4.setBounds(150, 350, 200, 50); 
        done4.setBackground(new Color (50, 50, 50)); 
        done4.setForeground(new Color(200, 200, 200)); 
        done4.setBorder(BorderFactory.createBevelBorder(1)); 
        done4.setText("Done"); 
        done4.addActionListener(this);

        //slide 5 -- no more incorrect questions - csv and performance 
        noMore.setBounds(100, 50, 300, 100); 
        noMore.setBackground(new Color (50, 50, 50)); 
        noMore.setForeground(new Color(200, 200, 200)); 
        noMore.setBorder(BorderFactory.createBevelBorder(1)); 
        noMore.setLineWrap(true);
        noMore.setEditable(false);
        noMore.setText("You're finished! Click View Performance to see how well you did on each attempt taken, and click"
        + " export to CSV \nfile to export your question and answer and performance data onto a CSV file");

        viewPerformance.setBounds(150, 175, 200, 50); 
        viewPerformance.setBackground(new Color (50, 50, 50)); 
        viewPerformance.setForeground(new Color(200, 200, 200)); 
        viewPerformance.setBorder(BorderFactory.createBevelBorder(1)); 
        viewPerformance.setText("View Performance"); 
        viewPerformance.addActionListener(this);

        exportToCSV.setBounds(150, 225, 200, 50); 
        exportToCSV.setBackground(new Color (50, 50, 50)); 
        exportToCSV.setForeground(new Color(200, 200, 200)); 
        exportToCSV.setBorder(BorderFactory.createBevelBorder(1)); 
        exportToCSV.setText("Export to CSV File"); 
        exportToCSV.addActionListener(this);

        done5.setBounds(150, 400, 200, 50); 
        done5.setBackground(new Color (50, 50, 50)); 
        done5.setForeground(new Color(200, 200, 200)); 
        done5.setBorder(BorderFactory.createBevelBorder(1)); 
        done5.setText("Done"); done5.addActionListener(this);

        //main frame frame.add(title);
        frame.add(questionInput); 
        frame.add(answerInput); 
        frame.add(add); 
        frame.add(viewTest); 
        frame.add(done1);
        frame.setVisible(true); 
    }

    public void slide2() { 
        frame.setVisible(false);
        frame.remove(title); 
        frame.remove(questionInput); 
        frame.remove(answerInput); 
        frame.remove(add); 
        frame.remove(viewTest); 
        frame.remove(done1); 
        frame.add(practiceTest); 
        frame.add(yes2); 
        frame.add(no2); 
        frame.setVisible(true);
    }

    public void slide31() { 
        frame.remove(incorrectPrompt); 
        frame.remove(incorrectOption); 
        frame.remove(keep); 
        frame.remove(done4); 
        question.setText(practiceQuestion); 
        frame.add(question); 
        frame.add(answer); 
        frame.add(next31);
    }

    public void slide32() { 
        frame.setVisible(false);
        frame.remove(practiceTest); 
        frame.remove(yes2); 
        frame.remove(no2); 
        frame.add(thanks); 
        frame.add(done32); 
        frame.setVisible(true);
    }

    public void slide4() { 
        frame.setVisible(false);
        frame.remove(question); 
        frame.remove(answer); 
        frame.remove(next31); 
        frame.add(incorrectPrompt); 
        frame.add(incorrectOption); 
        frame.add(keep); 
        frame.add(done4); 
        frame.setVisible(true);
    }

    public void slide5() { 
        frame.setVisible(false);
        frame.remove(incorrectPrompt); 
        frame.remove(incorrectOption); 
        frame.remove(keep); 
        frame.remove(done4); 
        frame.add(noMore); 
        frame.add(viewPerformance); 
        frame.add(exportToCSV); 
        frame.add(done5); 
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) { 
        Object e = event.getSource();

        //slide 1
        if (e == add) {
            frame.setVisible(false);
            String inputQuestion = questionInput.getText(); 
            String inputAnswer = answerInput.getText();
            if (inputQuestion.equals("")) {
                JOptionPane.showMessageDialog(null, "Please input a question");
            }
            else if (inputAnswer.equals("")) {
                JOptionPane.showMessageDialog(null, "Please input an answer");
            } 
            else {
                add(inputQuestion, inputAnswer, getTestBank(), getIndices());
                questionInput.setText("");
                answerInput.setText(""); 
            }
            frame.setVisible(true); 
        }
        if (e == viewTest) {
            String testView = "";
            for (String i : getIndices()) {
                testView = testView + "Question: " + i + " \nAnswer: "
                + getTestBank().get(i) + "\n"; 
            }
            JOptionPane.showMessageDialog(null, testView); 
        }
        if (e == done1) {
            if (getTestBank().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please input a question and answer onto the Test Bank.");
            } else {
                originalTest.putAll(getTestBank());
                for (int i = 0; i < getIndices().size(); i++) {
                    originalIndices.add(getIndices().get(i)); 
                }
                arraySize = getIndices().size();
                slide2();
            }
        }

    //slide 2 
    if (e == yes2) { 
        frame.setVisible(false); 
        frame.remove(practiceTest); 
        frame.remove(yes2); 
        frame.remove(no2); 
        assignQuestion(getIndices()); 
        slide31(); 
        frame.setVisible(true);
    }
    if (e == no2) {
        slide32();
    }

        
        
        


    //slide 3-1 
    if (e == next31) {
        frame.setVisible(false); 
        checkQuestion(getTestBank(), getIndices()); 
        frame.setVisible(true);
    }

    //slide 3-2 
    if (e == done32) {
        System.exit(0); 
    
    }
    //slide 4 
    if (e == incorrectOption) {
        if (!getTestBank().isEmpty()) {
            arraySize = getIndices().size(); 
            correct = 0;
            attempts++; 
            frame.setVisible(false); 
            assignQuestion(getIndices()); 
            slide31();
            frame.setVisible(true); 
        }
        else {
            JOptionPane.showMessageDialog(null, "No more incorrect questions!");
            slide5();
        }
    }
    if (e == keep) { 
        frame.setVisible(false);
        correct = 0;
        attempts++;
        getTestBank().putAll(originalTest); getIndices().clear();
        for (int i = 0; i < originalIndices.size(); i++) {
            getIndices().add(originalIndices.get(i)); 
        }
        arraySize = getIndices().size(); 
        assignQuestion(getIndices()); 
        slide31(); 
        frame.setVisible(true);
    }
    if (e == done4) {
        frame.setVisible(false); 
        slide5(); 
        frame.setVisible(true);
    }
    //slide 5
    if (e == viewPerformance) {
        javafx.application.Application.launch(Performance.class); 
    }
    if (e == exportToCSV) { 
        new Exporter();
        exportToCSV.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Exported to TestBankData.csv");
    }
    if (e == done5) {
        JOptionPane.showMessageDialog(null, "Thank you for using this software! Good luck in your studies");
        System.exit(0); 
    }
}
public static void main(String[] args) { 
    new GUI();
    } 
}
    
    
    
   
    
   
    