package y2024.d9;

import helper.Helper;

import java.io.IOException;
import java.util.List;

public class SolverD9 {
    public static void main(String[] args) throws IOException {
        List<String> input = Helper.readInput("y2024/d9.txt");
        List<String> debug = Helper.readInput("y2024/d9d.txt");

        //main1(input.get(0));
        main2(input.get(0));

    }

    static void main1(String input) {
        Disk disk = new Disk(input);

        disk.compactDisk();
        long checksum = disk.calc1();
        System.out.println("Checksum: " + checksum);
    }

    private static void main2(String s) {
        Disk disk = new Disk(s);
        disk.compactDiskFully();
        long checkSum = disk.calc1();
        System.out.println(checkSum);
    }
}