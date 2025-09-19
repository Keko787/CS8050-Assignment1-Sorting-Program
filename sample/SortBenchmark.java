/**
 * Experimental Evaluation Mode for Sorting Algorithms
 * Tests algorithms on large datasets without animation
 * @author Group-8
 */

import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;

public class SortBenchmark {
    // Test array sizes based on set constraints from the assignment
    private static final int[] TEST_SIZES = {1000, 10000, 100000};

    // Number of runs for averaging
    private static final int NUM_RUNS = 3;

    // Arrays for testing
    private int[] originalArray;
    private int[] workingArray;

    // Results storage by using BenchmarkResult class as data type
    private ArrayList<BenchmarkResult> results;

    ///////////////////////////////////////////////////////////////////////////////////

    // Inner small class to store benchmark results - default constructor
    private class BenchmarkResult {
        // init these attributes when object is made
        String algorithmName;
        int arraySize;
        double averageTime;
        double[] individualRuns;

        BenchmarkResult(String name, int size, double avg, double[] runs) {  // constructor for parameters
            // init these attributes if using this constructor
            this.algorithmName = name;
            this.arraySize = size;
            this.averageTime = avg;
            this.individualRuns = runs;
        }
    }
    // end of BenchmarkResults

    ///////////////////////////////////////////////////////////////////////////////////

    public SortBenchmark() { // constructor for SortBenchmark
        // make the results into an arrayList so we can use methods to easily append to the array
        results = new ArrayList<>();
    }

    ///////////////////////////////////////////////////////////////////////////////////


    // Generate random array of given size (1,000; 10,000; 100,000)
    private void generateRandomArray(int size) {
        Random rand = new Random();
        originalArray = new int[size];
        for (int i = 0; i < size; i++) {
            originalArray[i] = rand.nextInt(size * 10); // Random values 0 to size*10
        }
        workingArray = new int[size];
    }

    // Reset working array to original scrambled state
    private void resetArray() {
        // array copy is cool bc it allows the array to get reset by overwriting with the original nonfilled array
        System.arraycopy(originalArray, 0, workingArray, 0, originalArray.length);
    }

    ///////////////////////////////////////////////////////////////////////////////////


    // Benchmark a specific sorting algorithm using SortShow
    private BenchmarkResult benchmarkAlgorithm(String algorithmName, int arraySize) { // set Benchmark class as data type
                                                                                      // to make returning the results
                                                                                      // Object oriented to avoid reference issues
        double[] runTimes = new double[NUM_RUNS]; // create an array for runtimes for the sort alg. base on set constraints to run
        double totalTime = 0;  // init the total time of running the alg.

        System.out.printf("  Testing %s with %d elements...\n", algorithmName, arraySize);

        for (int run = 0; run < NUM_RUNS; run++) {
            resetArray(); // at the start of every run, reset array

            // Create a new copy of the array for this run to avoid reference issues
            int[] arrayCopy = new int[workingArray.length];
            System.arraycopy(workingArray, 0, arrayCopy, 0, workingArray.length);

            // Create a SortShow instance in benchmark mode with the array copy
            // to absolve ref error and turning off gui mode
            SortShow sorter = new SortShow(arrayCopy, true);

            long startTime = System.nanoTime(); // set the start time with nanoTime to precisely measure in seconds

            // Call the appropriate sorting method based on name
            switch (algorithmName) {
                case "Bubble Sort":
                    sorter.BubbleSort();
                    break;
                case "Selection Sort":
                    sorter.SelectionSort();
                    break;
                case "Insertion Sort":
                    sorter.InsertionSort();
                    break;
                case "Shell Sort":
                    sorter.ShellSort();
                    break;
                case "Merge Sort (Recursive)":
                    sorter.R_MergeSort();
                    break;
                case "Merge Sort (Iterative)":
                    sorter.I_MergeSort();
                    break;
                case "Quick Sort":
                    sorter.QuickSort();
                    break;
                case "Radix Sort":
                    sorter.RadixSort();
                    break;
            }

            long endTime = System.nanoTime(); // set the end time with nanoTime to precisely measure in seconds

            double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;  // get the elapsed time of the run
            runTimes[run] = elapsedSeconds;  // save it to the run time array
            totalTime += elapsedSeconds; // add the elapsed time to the total

        }

        // print avg time
        double averageTime = totalTime / NUM_RUNS;
        System.out.printf("    Average: %.6f seconds\n", averageTime);

        return new BenchmarkResult(algorithmName, arraySize, averageTime, runTimes);
    }

    ///////////////////////////////////////////////////////////////////////////////////

    // Run all benchmarks
    public void runAllBenchmarks() {
        System.out.println("=================================================");
        System.out.println("SORTING ALGORITHM BENCHMARK - EXPERIMENTAL MODE");
        System.out.println("=================================================\n");

        for (int size : TEST_SIZES) {
            System.out.printf("Testing with array size: %d\n", size);
            System.out.println("---------------------------------");

            generateRandomArray(size);

            // Test each sorting algorithm, then add the results of each benchmark to
            results.add(benchmarkAlgorithm("Bubble Sort", size));
            results.add(benchmarkAlgorithm("Selection Sort", size));
            results.add(benchmarkAlgorithm("Insertion Sort", size));
            results.add(benchmarkAlgorithm("Shell Sort", size));
            results.add(benchmarkAlgorithm("Merge Sort (Recursive)", size));
            results.add(benchmarkAlgorithm("Merge Sort (Iterative)", size));
            results.add(benchmarkAlgorithm("Quick Sort", size));
            results.add(benchmarkAlgorithm("Radix Sort", size));

            System.out.println();
        }

        printSummaryTable();
        printPerformanceAnalysis();
    }

    ///////////////////////////////////////////////////////////////////////////////////

    // Print formatted results table
    private void printSummaryTable() {
        System.out.println("=================================================");
        System.out.println("BENCHMARK RESULTS SUMMARY");
        System.out.println("=================================================");
        System.out.println("All times in seconds (average of " + NUM_RUNS + " runs)\n");

        // Print header of size
        System.out.printf("%-25s", "Algorithm");
        for (int size : TEST_SIZES) {
            System.out.printf("%15s", "n=" + size);
        }
        System.out.println("\n" + "-".repeat(70));

        // Group results by algorithm
        String[] algorithms = {
            "Bubble Sort", "Selection Sort", "Insertion Sort", "Shell Sort",
            "Merge Sort (Recursive)", "Merge Sort (Iterative)", "Quick Sort", "Radix Sort"
        };

        for (String algo : algorithms) { // for each string in the string array
            System.out.printf("%-25s", algo);
            for (int size : TEST_SIZES) { // for each size in the constraints
                // Find matching result
                for (BenchmarkResult result : results) { // for each result
                    if (result.algorithmName.equals(algo) && result.arraySize == size) {
                        System.out.printf("%15.6f", result.averageTime);
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    // Print performance analysis
    private void printPerformanceAnalysis() {
        System.out.println("\n=================================================");
        System.out.println("Performance Analysis:");
        System.out.println("-------------------------------------------------");

        // Find best performers for each size based on set constraint sizes
        for (int size : TEST_SIZES) {
            double bestTime = Double.MAX_VALUE;
            double worstTime = 0;
            String bestAlgo = "";
            String worstAlgo = "";

            for (BenchmarkResult result : results) {
                if (result.arraySize == size) {
                    if (result.averageTime < bestTime) {
                        bestTime = result.averageTime;
                        bestAlgo = result.algorithmName;
                    }
                    if (result.averageTime > worstTime) {
                        worstTime = result.averageTime;
                        worstAlgo = result.algorithmName;
                    }
                }
            }

            System.out.printf("For n=%d:\n", size);
            System.out.printf("  Best: %s (%.6f seconds)\n", bestAlgo, bestTime);
            System.out.printf("  Worst: %s (%.6f seconds)\n", worstAlgo, worstTime);
            System.out.printf("  Speedup: %.2fx faster\n\n", worstTime / bestTime);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////


    // Main method
    public static void main(String[] args) {
        SortBenchmark benchmark = new SortBenchmark();

        System.out.println("Starting benchmark suite...");
        System.out.println("This may take several minutes for large arrays.\n");

        long startTime = System.currentTimeMillis();
        benchmark.runAllBenchmarks();
        long endTime = System.currentTimeMillis();

        System.out.printf("\nTotal benchmark time: %.2f seconds\n",
                         (endTime - startTime) / 1000.0);
    }
}