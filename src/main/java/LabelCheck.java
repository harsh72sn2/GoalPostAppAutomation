import java.util.*;

public class LabelCheck {
    public static void main(String args[])
    {
        List<String> uiLabels = Arrays.asList("LABEL02LABEL 09LABEL 07");
        List<String> labelsFromDatabase = Arrays.asList("LABEL 02", "LABEL 07", "LABEL 09");
        if(uiLabels.size()==labelsFromDatabase.size()) {
            String[] uiLabelArray = uiLabels.get(0).split(" ");
            String uilabelString = "";
            String dblabelString = "";
            for (int i = 0; i < uiLabelArray.length; i++) {
                System.out.println(uiLabelArray[i]);
                uilabelString = uilabelString + uiLabelArray[i];
            }
            for (int j = 0; j < labelsFromDatabase.size(); j++) {
                dblabelString = dblabelString + labelsFromDatabase.get(j).split(" ")[0] + labelsFromDatabase.get(j).split(" ")[1];
            }

            Set<Character> uilabelCharSet = new HashSet<Character>();
            Set<Character> dbLabelCharSet = new HashSet<Character>();
            for (int i = 0; i < uilabelString.length(); i++) {
                uilabelCharSet.add(uilabelString.charAt(i));
            }
            for (int j = 0; j < dblabelString.length(); j++) {
                dbLabelCharSet.add(dblabelString.charAt(j));
            }
            System.out.println(uilabelCharSet);
            System.out.println(dbLabelCharSet);
            if (uilabelCharSet.equals(dbLabelCharSet)) {
                System.out.println("zxdsa");
            }
        }

    }
}
