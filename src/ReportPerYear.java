import java.util.ArrayList;
import java.util.HashMap;

public class ReportPerYear {
    ArrayList<String> month = new ArrayList<>();
    ArrayList<Integer> amount = new ArrayList<>();
    ArrayList<Boolean> expense = new ArrayList<>();
    String fileName;
    ReportPerYear(String name) {
        fileName = name;
    }

    void readingReports() {
        FileReader fileReader = new FileReader();
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        if (!lines.isEmpty()) {
            for (int i = 1; i < lines.size(); i++) {
                String[] lineValues = lines.get(i).split(",");
                month.add(lineValues[0]);
                amount.add(Integer.parseInt(lineValues[1]));
                expense.add(Boolean.parseBoolean(lineValues[2]));
            }
        } else {
            System.out.println("Файл " + fileName + " не найден!");
        }
    }

    int amountProfitInYear(int monthNumber) {
        for (int i = 0; i < month.size(); i++) {
            int contain = Integer.parseInt(month.get(i));
            if (contain == monthNumber) {
                if (!expense.get(i)) {
                    return amount.get(i);
                }
            }
        }
        return 0;
    }

    int amountSpendInYear(int monthNumber) {
        for (int i = 0; i < month.size(); i++) {
            int contain = Integer.parseInt(month.get(i));
            if (contain == monthNumber) {
                if (expense.get(i)) {
                    return amount.get(i);
                }
            }
        }
        return 0;
    }

    int profitEveryMonth(int monthNumber) {
        int profit = 0;
        int spend = 0;
        for (int i = 0; i < month.size(); i++) {
            int contain = Integer.parseInt(month.get(i));
            if (contain == monthNumber) {
                if (!expense.get(i)) {
                    profit = amount.get(i);
                } else {
                    spend = amount.get(i);
                }
            }
        }
        return profit - spend;
    }
    int avgSpendInYear() {
        int maxMonths = month.size() / 2;
        int spend = 0;
        for (int i = 1; i <= maxMonths; i++) {
            for (int j = 0; j < month.size(); j++) {
                int contain = Integer.parseInt(month.get(j));
                if (contain == i) {
                    if (expense.get(j)) {
                        spend += amount.get(j);
                    }
                }
            }
        }
        return spend / maxMonths;
    }

    int avgProfitInYear() {
        int maxMonths = month.size() / 2;
        int profit = 0;
        for (int i = 1; i <= maxMonths; i++) {
            for (int j = 0; j < month.size(); j++) {
                int contain = Integer.parseInt(month.get(j));
                if (contain == i && !expense.get(j)) {
                    profit += amount.get(j);
                }
            }
        }
        return profit / maxMonths;
    }
}
