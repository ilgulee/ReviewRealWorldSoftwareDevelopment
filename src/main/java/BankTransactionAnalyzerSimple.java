import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankTransactionAnalyzerSimple {

    public static void main(String[] args) {

        double total = readFileToStream(5);
        System.out.println("The total for all transactions is " + total);
    }

    private static double readFileToStream() {
        try (Stream<String> lines = Files.lines(Paths.get("src/main//java/bank_statement"))) {
            Stream<String> inOut =
                    lines.map(line -> line.split(",")).map(line -> line[1]).collect(Collectors.toList()).stream();
            return calculateTotalTransaction(inOut);
        } catch (IOException e) {
            return 0;
        }
    }

    private static double readFileToStream(Integer month) {
        try (Stream<String> lines = Files.lines(Paths.get("src/main//java/bank_statement"))) {
            Stream<String> inOut =
                    lines.map(line -> line.split(","))
                            .filter(strings -> isTheSameMonth(month, strings[0]))
                            .map(strings -> strings[1]).collect(Collectors.toList()).stream();
            return calculateTotalTransaction(inOut);
        } catch (IOException e) {
            return 0;
        }
    }

    private static Month parseLocalDate(String date) {
        DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, DATE_PATTERN).getMonth();
    }

    private static boolean isTheSameMonth(Integer month, String date) {
        Month inputMonth = Month.of(month);
        return inputMonth == parseLocalDate(date);
    }

    private static Double calculateTotalTransaction(Stream<String> inOut) {
        return inOut.mapToDouble(Double::valueOf).sum();
    }
}
