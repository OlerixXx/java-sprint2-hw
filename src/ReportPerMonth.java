import java.util.ArrayList;
import java.util.HashMap;

public class ReportPerMonth {
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<Boolean> expense = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();
    ArrayList<Integer> unitPrice = new ArrayList<>();
    String fileName;
    int monthNumber;
    ReportPerMonth(String name, int number) {
        fileName = name;
        monthNumber = number;

    }

    void readingReports() {
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        if (!lines.isEmpty()) {
            for (int i = 1; i < lines.size(); i++) {
                String[] lineValues = lines.get(i).split(",");
                itemName.add(lineValues[0]);
                expense.add(Boolean.parseBoolean(lineValues[1]));
                quantity.add(Integer.parseInt(lineValues[2]));
                unitPrice.add(Integer.parseInt(lineValues[3]));
            }
        } else {
            System.out.println("Файл " + fileName + " не найден!");
        }
    }

    int sumProfitPerMonth() {
        int profit = 0;
        for (int i = 0; i < expense.size(); i++) {
            if (!expense.get(i)) {
                profit += unitPrice.get(i) * quantity.get(i);
            }
        }
        return profit;
    }

    int sumSpendPerMonth() {
        int spend = 0;
        for (int i = 0; i < expense.size(); i++) {
            if (expense.get(i)) {
                spend += unitPrice.get(i) * quantity.get(i);
            }
        }
        return spend;
    }

    HashMap<String, Integer> maxProfitPerMonth() {
        HashMap <String, Integer> out = new HashMap<>();
        int maxProfit = 0;
        String nameProfit = "";
        for (int i = 0; i < expense.size(); i++) {
            if (!expense.get(i)) {
                int sum = quantity.get(i) * unitPrice.get(i);
                if (sum > maxProfit) {
                    maxProfit = sum;
                    nameProfit = itemName.get(i);
                }
            }
        }
        out.put(nameProfit, maxProfit);
        return out;
    }

    HashMap<String, Integer> maxSpendPerMonth() {
        HashMap <String, Integer> out = new HashMap<>();
        int maxProfit = 0;
        String nameProfit = "";
        for (int i = 0; i < expense.size(); i++) {
            if (expense.get(i)) {
                int sum = quantity.get(i) * unitPrice.get(i);
                if (sum > maxProfit) {
                    maxProfit = sum;
                    nameProfit = itemName.get(i);
                }
            }
        }
        out.put(nameProfit, maxProfit);
        return out;
    }
}
