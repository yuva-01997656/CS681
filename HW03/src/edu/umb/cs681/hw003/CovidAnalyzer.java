package edu.umb.cs681.hw003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CovidAnalyzer {
    public static void main(String[] args) {
        // Get the path of CSV file
        Path path = Paths.get("src", "covid_data.csv");

        // BufferReader to take input from user
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        // Get the list of lines from data file
        try (Stream<String> lines = Files.lines(path)) {
            // Get county name from user
            System.out.println("Enter the name of county you want to search, Enter 'exit' to exit");
            System.out.println("eg: Camden, Storey, etc ");

            String input_county = inp.readLine();

            if (input_county.equals("exit")) {
                System.exit(0);
            }

            // get state name from user.
            System.out.println("Enter the name of state you want to search, Enter 'exit' to exit");
            System.out.println("eg: New Jersey, Nevada, etc ");
            String input_state = inp.readLine();

            if (input_state.equals("exit")) {
                System.exit(0);
            }


            // Storing data in list matrix
            List<List<String>> data_matrix = lines.map(line -> {
                        return Stream.of(line.split(","))
                                .map(value -> value.substring(0, value.length()))
                                .collect(Collectors.toList());
                    })
                    .collect(Collectors.toList());

            // get state names from data matrix selected by user
            List<List<String>> state_data = data_matrix.stream().filter((i) ->
                    i.get(4).contains(input_state)).collect(Collectors.toList());

            // validating if state name is present
            if (state_data.size() == 0) {
                System.out.println("Incorrect state input.");
                return;
            }

            // get county names from data matrix selected by user
            List<List<String>> county_list = data_matrix.stream().filter((i) ->
                            i.get(4).contains(input_state)).collect(Collectors.toList()).
                    stream().filter((i) ->
                            i.get(5).contains(input_county)).collect(Collectors.toList());

            // validating if county name is present
            if (county_list.size() == 0) {
                System.out.println("Incorrect county input for state : " + input_state + ".");
                return;
            }

            List<String> county_data = county_list.get(0);

            System.out.printf("Number of deaths in %s, %s is %s.\n", input_county, input_state, county_data.get(7));
            String deathInState = data_matrix.stream().filter((i) ->
                            i.get(4).contains(input_state))
                    .map((i) -> i.get(7)).reduce("0", (subtotal, element) ->
                            String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));

            System.out.printf("Total number of deaths in %s is %s.\n", input_state, deathInState);
            System.out.printf("Average deaths in each county in %s is %s.\n", input_state, Integer.parseInt(deathInState) / state_data.size());
        } catch (IOException ex) {
            System.out.println("Error occurred" + ex.getMessage());
        }

    }
}
