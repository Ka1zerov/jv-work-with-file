package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        int buyAmount = 0;
        int supplyAmount = 0;
        for (int i = 0; i < dataFromFile.length; i += 2) {
            if (dataFromFile[i].equals("supply")) {
                supplyAmount += Integer.parseInt(dataFromFile[i + 1]);
            } else {
                buyAmount += Integer.parseInt(dataFromFile[i + 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount);
        writeToFile(toFileName, stringBuilder);
    }

    private String[] readFromFile (String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find the file", e);
        }
        return stringBuilder.toString().split(",");
    }

    private void writeToFile (String toFileName, StringBuilder stringBuilder) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
