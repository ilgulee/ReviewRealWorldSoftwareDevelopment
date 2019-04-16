import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankTransactionAnalyzerSimple {

    public static void main(String[] args) {

        double total = readFileToStream();
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

    private static Double calculateTotalTransaction(Stream<String> inOut) {
        return inOut.mapToDouble(Double::valueOf).sum();
    }
}
