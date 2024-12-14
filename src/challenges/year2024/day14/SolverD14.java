package challenges.year2024.day14;

import helper.Helper;

import java.io.IOException;
import java.util.List;

public class SolverD14 {
    public static void main(String[] args) throws IOException {
        List<String> input = Helper.readInput("year2024/d14.txt");
        List<String> debug = Helper.readInput("year2024/d14d.txt");

        RoboMapHelper roboMapHelper = new RoboMapHelper(input, 100, 101, 103);
        System.out.println(roboMapHelper.calc1());
    }
}
