//Eclipse IDE used
//Skeleton Class that contains integration of HashMap and ArrayList and methods
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
public class App {
    //Random class
    private Random random = new Random();
    //indices to keep track of HashMap questions
    private ArrayList<String> indices = new ArrayList<String>();
    //Test bank to hold input questions and answers
    private HashMap<String, String> TestBank = new HashMap<String, String>(); //TestBank for incorrect answers and Indices for such likewise
    private HashMap<String, String> newTestBank = new HashMap<String, String>();
    private ArrayList<String> newIndices = new ArrayList<String>();

    public HashMap<String, String> getTestBank() { 
        return TestBank;
    }

    public ArrayList<String> getIndices() { 
        return indices;
    }

    public HashMap<String, String> getNewTestBank() { 
        return newTestBank;
    }

    public ArrayList<String> getNewIndices() { 
        return newIndices;
    }

    //This selects a random question from the TestBank 
    public String selectRandomKey(ArrayList<String> a) {
        return a.get(random.nextInt(a.size())); 
    }

    //Method for adding question and answer onto TestBank
    public void add(String inputKey, String inputValue, HashMap<String, String> h, ArrayList<String> a) {
        h.put(inputKey, inputValue);
        a.add(inputKey); 
    }
    
    //Method for removing question and answer from HashMap given the answer only
    public void remove(String inputValue, HashMap<String, String> h, ArrayList<String> a) {
        String temp = getKeyFromValue(inputValue); h.remove(temp); a.remove(a.indexOf(temp));
    }

    //This method is used in remove() to simply get the key from a given value
    public String getKeyFromValue(String inputValue) { 
        for (String s : getTestBank().keySet()) {
            if (getTestBank().get(s).equals(inputValue)) { 
                return s;
            } 
        }
        return null;
    }
}