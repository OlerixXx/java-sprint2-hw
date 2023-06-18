import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int countFilesMonth = 3;

        Scanner scanner = new Scanner(System.in);
        ReportPerMonth[] reportPerMonth = new ReportPerMonth[countFilesMonth];
        ReportPerYear[] reportPerYear = new ReportPerYear[1];

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                for (int i = 0; i < countFilesMonth; i++) {
                    String fileName = "m.20210" + (i + 1) + ".csv";
                    reportPerMonth[i] = new ReportPerMonth(fileName, i);
                    reportPerMonth[i].readingReports();
                }
                System.out.println("Все месячные отчёты были считаны.");
            } else if (command == 2) {
                String fileName = "y.2021.csv";
                reportPerYear[0] = new ReportPerYear(fileName);
                reportPerYear[0].readingReports();
                System.out.println("Все годовые отчёты были считаны.");
            } else if (command == 3) {
                if (checkMonthReport(reportPerMonth) && checkYearReport(reportPerYear)) {
                    for (ReportPerMonth monthReport : reportPerMonth) {
                        if (compareAmount(reportPerYear, monthReport)) {
                            System.out.println("Проверка месяца 0" + (monthReport.monthNumber + 1));
                        } else {
                            System.out.println("Обнаружено несоответствие в месяце: 0" + (monthReport.monthNumber + 1));
                        }
                    }
                    System.out.println("Успешное завершение операции");
                } else {
                    System.out.println("Перед тем как сверить отчёты, необходимо их считать.");
                }
            } else if (command == 4) {
                if (checkMonthReport(reportPerMonth)) {
                    for (ReportPerMonth monthReport : reportPerMonth) {
                        HashMap<String, Integer> maxProfitAndName = monthReport.maxProfitPerMonth();
                        HashMap<String, Integer> maxSpendAndName = monthReport.maxSpendPerMonth();
                        System.out.println("Месяц: 0" + (monthReport.monthNumber + 1));
                        String ProfitKey = "";
                        for (String keySet : maxProfitAndName.keySet()) { ProfitKey = keySet; }
                        System.out.println("Самый прибыльный товар: " + ProfitKey);
                        System.out.println("На сумму: " + maxProfitAndName.get(ProfitKey));
                        String SpendKey = "";
                        for (String keySet : maxSpendAndName.keySet()) { SpendKey = keySet; }
                        System.out.println("Самая большая трата: " + SpendKey);
                        System.out.println("На сумму: " + maxSpendAndName.get(SpendKey));
                        System.out.println(" ");
                    }
                } else {
                    System.out.println("Перед тем как вывести информацию о месячных отчётах, необходимо их считать.");
                }
            } else if (command == 5 ) {
                if (checkYearReport(reportPerYear)) {
                    int maxMonths = reportPerYear[0].month.size() / 2;
                    System.out.println("Год: 2021");
                    for (int i = 1; i <= maxMonths; i++) {
                        System.out.println("Прибыль за 0" + (i) + " месяц: " + reportPerYear[0].profitEveryMonth(i));
                    }
                    System.out.println("Средний расход за все имеющиеся операции в году: " + reportPerYear[0].avgSpendInYear());
                    System.out.println("Средний доход за все имеющиеся операции в году: " + reportPerYear[0].avgProfitInYear());
                } else {
                    System.out.println("Перед тем как вывести информацию о годовых отчётах, необходимо их считать.");
                }
            } else if (command == 10101) {
                System.out.println("Завершение работы...");
                return;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }
    public static void printMenu () {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("10101 - Последовательность символов для завершения работы программы");
    }

    public static boolean checkMonthReport (ReportPerMonth[] reportPerMonth) {
        boolean checkEveryMonth = false;
        for (ReportPerMonth perMonth : reportPerMonth) {
            if (perMonth == null) {
                return false;
            } else {
                checkEveryMonth = true;
            }
        }
        return checkEveryMonth;
    }

    public static boolean checkYearReport (ReportPerYear[] reportPerYear) {
        boolean checkEveryYear = false;
        for (ReportPerYear perYear : reportPerYear) {
            if (perYear == null) {
                return false;
            } else {
                checkEveryYear = true;
            }
        }
        return checkEveryYear;
    }

    public static boolean compareAmount (ReportPerYear[] reportPerYear, ReportPerMonth monthReport) {
        boolean profit = monthReport.sumProfitPerMonth() == reportPerYear[0].amountProfitInYear(monthReport.monthNumber);
        boolean spend = monthReport.sumSpendPerMonth() == reportPerYear[0].amountSpendInYear(monthReport.monthNumber);
        return profit == spend;
    }
}

