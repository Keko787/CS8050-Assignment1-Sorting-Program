/**
 *
 * @author Ouda
 */

//importing the libraries that will be needed in this program

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Random;

//The class that has all the sorts in it
public class SortShow extends JPanel {
		// An array to hold the lines_lengths to be sorted
		public int[] lines_lengths;
		//The amount of lines needed
		public final int total_number_of_lines = 256;
		 // An array to holds the scrambled lines_lengths
		public int[] scramble_lines;
		//A temp Array that is used later for sorts
		public int[] tempArray;

		// Mode flag: true for GUI mode, false for benchmark mode
		private boolean guiMode = true;

		// Actual array size (for benchmark mode)
		private int array_size;

		//the default constructor for the SortShow class (GUI mode)
		public SortShow(){
			this.guiMode = true;  // use the gui mode as a flag to enable paintComponent
			this.array_size = total_number_of_lines;
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++)
				lines_lengths[i] =  i+5;

		}

		// Constructor for benchmark mode - accepts external array for benchmark mode (1,000; 10,000; 100,000)
		public SortShow(int[] array, boolean benchmarkMode) {
			this.guiMode = !benchmarkMode; // if benchmark mode, no gui mode
			this.lines_lengths = array; // set the max lines to array size
			this.tempArray = new int[array.length]; // initiates global temp array
			this.array_size = array.length; // set actual array size
		}

		// Helper method to get the actual array size - defunct
		private int getArraySize() {
			return guiMode ? total_number_of_lines : array_size;
		}
		

		//A method that scrambles the lines
		public void scramble_the_lines(){
			//A random generator
			Random num = new Random(); 
			//Randomly switching the lines
			for(int i = 0; i < total_number_of_lines; i++){
				//getting a random number using the nextInt method (a number between 0 to i + 1)
				int j = num.nextInt(i + 1); 
				//swapping The element at i and j 
				swap(i, j);
			}
			//assigning the size for the scramble_lines below
			scramble_lines = new int[total_number_of_lines];
			//copying the now scrambled lines_lengths array into the scramble_lines array 
			//to store for reuse for other sort methods
			//so that all sort methods will use the same scrambled lines for fair comparison 
			for (int i = 0; i < total_number_of_lines; i++)
			{
				scramble_lines[i] = lines_lengths[i];
			}
			//Drawing the now scrambled lines_lengths
			if (guiMode) {
				paintComponent(this.getGraphics());
			}
		}
		
		//Swapping method that swaps two elements in the lines_lengths array
		public void swap(int i, int j){
			//storing the i element in lines_lengths in temp
			int temp = lines_lengths[i];
			//giving i element in lines_lengths the value of j element in lines_lengths
			lines_lengths[i] = lines_lengths[j];
			//giving j element in lines_lengths the value of temp
			lines_lengths[j] = temp;
		}

    ///////////////////////////////////////////////////////////////////////////////////
        public void BubbleSort(){

            //getting the date and time when the selection sort starts
            Calendar start = Calendar.getInstance();

            int n = total_number_of_lines;

            for(int i = 0; i < n - 1; i++){ // for the length of the array
                for(int j = 0; j < n - i - 1; j++){ // for the legth of the array subtracting the index of parent loop
                    if(lines_lengths[j] > lines_lengths[j+1]){ // if current index is larger than the following element
                        swap(j, j+1); // swap the index with the following element
                        //redrawing the line_lengths
                        //paintComponent(this.getGraphics()); // DEBUG - DISABLE TO SEE FINISHED RESULTS FASTER
                    }
                    //redrawing the line_lengths
                    //paintComponent(this.getGraphics()); // DEBUG - DISABLE TO SEE FINISHED RESULTS FASTER
                }
                //redrawing the line_lengths
                if (guiMode) {
                    paintComponent(this.getGraphics()); // DEBUG - DISABLE TO SEE FINISHED RESULTS FASTER
                }
            }
            //redrawing the line_lengths
            if (guiMode) {
                paintComponent(this.getGraphics());
            }

            //getting the date and time when the bubble sort ends
            Calendar end = Calendar.getInstance();

            //getting the time it took for the bubble sort to execute
            //subtracting the end time with the start time
            SortGUI.bubbleTime = end.getTime().getTime() - start.getTime().getTime();
        }


    ///////////////////////////////////////////////////////////////////////////////////

    //this method gets the smallest element in the array of lines_lengths
        public int getIndexOfSmallest(int first, int last){

            //You need to complete this part.
            int smallestIndex = first;
            for(int i = first + 1; i <= last; i++){ // for the length of the array excluding the first element
                if(lines_lengths[i] < lines_lengths[smallestIndex]){ //if the current index is smaller than the set smallest index
                    smallestIndex = i; // set the smallestIndex to the current index
                }
            }

            return smallestIndex; //modify this line - MODIFIED to return the smallestIndex
        }

    //The selectionSort method
		public void SelectionSort(){
			//getting the date and time when the selection sort starts
			Calendar start = Calendar.getInstance();
			//Using the selection sort to lines_lengths sort the array
            int index = 0;
            int n = total_number_of_lines;

            for(; index < n - 1; index++) //for the length of the array
            {
                int indexOfSmallest = getIndexOfSmallest(index, n - 1); //getIndexofSmallest, finds the smallest index in the array
                swap(index, indexOfSmallest); //swap manipulates the line_lengths, swap the current index with the smallestIndex
                //redrawing the line_lengths
                if (guiMode) {
                    paintComponent(this.getGraphics());
                    //Make delay for 10ms
                    delay(10);
                }
            }


			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}

    ///////////////////////////////////////////////////////////////////////////////////


    public void InsertionSort(){
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        // iterative method

        // init variables
        int begin = 0;
        int last = total_number_of_lines;
        int unsortedIndex = begin + 1; // index of unsorted portion of array, assuming first element is sorted

        // loop on the length of the unsorted index to end, increment index
        for(; unsortedIndex < last; unsortedIndex++)
        {
            // init variables
            int elementToInsert = lines_lengths[unsortedIndex]; //saving element to inset later
            int compareIndex = unsortedIndex - 1; // comparison position left of unsorted index, going backwards in array

            // while the index is greater than the start
            // and if the elements in the compare index is greater than the elementToInsert
            while(compareIndex >= begin && lines_lengths[compareIndex] > elementToInsert)
            {
                // shift element's position to the right
                lines_lengths[compareIndex + 1] = lines_lengths[compareIndex]; // takes the value at comparison position and copies it over to the position on the right of comparison as it decreases
                compareIndex--; // decrement compareIndex
            }

            // After an element smaller than elementToInsert is found or compareIndex is at the start, the saved element is inserted
            lines_lengths[compareIndex + 1] = elementToInsert;

            // Redraw the lines after each insertion
            if (guiMode) {
                paintComponent(this.getGraphics());
                // Make delay for 1ms
                delay(1);
            }
        }


        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public void ShellSort(){
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        int first = 0;
        int last = total_number_of_lines - 1;
        int n = last - first + 1;
        int gap = n / 2;

        // every loop, halve the gap, until it is 0
        for(; gap > 0; gap = gap / 2)
        {
            // progress through array until reaches end of gap
            for(int begin = first; begin <= first + gap; begin++)
            {

                int unsortedIndex;
                int index;

                // start at the end of the gap and progress through the end of the array,
                // each loop jumps to the end each of gap, so (loop 0:0 + 4; loop 1: 4 + 4)
                // each time the parent loop increments, the more this loops
                for(unsortedIndex = begin + gap; unsortedIndex <= last; unsortedIndex = unsortedIndex + gap)
                {
                    // save the current element in the unsortedIndex
                    int currentUnsortedElement = lines_lengths[unsortedIndex];

                    // at the start of the current gap,
                    // progress backwards until reaching the begining of the loop
                    // (meaning currentUnsortedElement is the smallest element in the array)
                    // or until the element in the current index is larger than the currentUnsortedElement saved
                    for(index = unsortedIndex - gap;
                        (index >= begin) && (lines_lengths[index] > currentUnsortedElement);
                        index = index - gap)
                    {
                        // if the currentUnsortedElement is smaller than the element in the index (if loop activates),
                        // shift element on the index based on the gap spacing size
                        lines_lengths[index + gap] = lines_lengths[index];

                        // Ripple Effect: Since the element is saved and the first to be overwritten
                        // it is ok for it to do so, since the next iteration will cause the same thing to happen
                        // the next element gets overwritten, but its ok since it already copied itself to the next gap
                        // position. This causes a chain reaction where the element shifts forward, overwriting the copy
                        // of the previous shift
                    }
                    // the final overwrite happens after the loop is over and is overwritted by
                    // the saved element that is ready to be loaded in the correct position

                    // load the saved element into the (index+gap) where index stopped at
                    // very important distinct that it doesnt just replace the element
                    // where the nest loop shifted it because it only does so if its
                    // the smallest value in the array
                    lines_lengths[index + gap] = currentUnsortedElement;
                }

                //redrawing the line_lengths
                if (guiMode) {
                    paintComponent(this.getGraphics());
                    //Causing a delay for 10ms
                    delay(10);
                }

            }

//            //redrawing the line_lengths DEBUG
//            paintComponent(this.getGraphics());
//            //Causing a delay for 10ms
//            delay(10);
        }

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.shellTime = end.getTime().getTime() - start.getTime().getTime();

    }

    ///////////////////////////////////////////////////////////////////////////////////
		
		//recursive merge sort method
		public void R_MergeSort(){
			//getting the date and time when the recursive merge sort starts
			Calendar start = Calendar.getInstance();
			//assigning the size for the tempArray below
            tempArray = new int[total_number_of_lines];
            int first = 0;
            int last = total_number_of_lines;

            // Call the recursive merge sort with initial parameters
            R_MergeSort(first, last - 1);

//            //redrawing the line_lengths DEBUG
//            paintComponent(this.getGraphics());
//            //Causing a delay for 10ms
//            delay(10);

			//You need to complete this part.
            //Getting the data-time when the Recursive Merge sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the iterative merge sort to execute
			//subtracting the end time with the start time
	        SortGUI.rmergeTime = end.getTime().getTime() - start.getTime().getTime();
			
		}
		
		//recursive merge sort method
		public void R_MergeSort(int first, int last){
            if(first < last){

				//You need to complete this part.
                // Divide: init mid value between first and last
                int mid = (first + last) / 2;

                // Recur: Split the recursion merge to lower half of the array
                R_MergeSort(first, mid);
                // Recur: Split the recursion merge to upper half of the array
                R_MergeSort(mid + 1, last);

                // Conquer: Merge the Sorted Halves in first-mid and mid+1 - last
                R_Merge(first, mid, last);

                //redrawing the line_lengths
                if (guiMode) {
                    paintComponent(this.getGraphics());
                    //Causing a delay for 10ms
				    delay(10);
                }
			}
		}

		
		//recursive merge sort method
		public void R_Merge(int first, int mid, int last){

			//You need to complete this part.

            // Two adjacent sub-arrays
            int beginHalf1 = first;
            int endHalf1 = mid;
            int beginHalf2 = mid + 1;
            int endHalf2 = last;
            int index = beginHalf1;

            // While both subarrays are not empty
            while(beginHalf1 <= endHalf1 && beginHalf2 <= endHalf2){
                // compare an element in the lower subarray with an element in the upper subarray
                if(lines_lengths[beginHalf1] < lines_lengths[beginHalf2]){
                    // if the element in the lower half is bigger than the element in the
                    // upper half, assign the lower half element that index in the temp array
                    tempArray[index] = lines_lengths[beginHalf1];
                    // increase the index in the lower sub-array
                    beginHalf1++;
                }
                else{
                    // if the element in the lower sub-array is not larger than the element in the
                    // upper sub-array, then it the element in the upper array
                    // gets assigned to the index in the temp array
                    tempArray[index] = lines_lengths[beginHalf2];
                    // increase the index in the upper sub-array
                    beginHalf2++;
                }
                // increase index
                index++;
            }

            // Finish off the nonempty sub-array

            // Finish off the first sub-array, if necessary
            while(beginHalf1 <= endHalf1)
            {
                // Invariant: tempArray[beginHalf1..index-1] is in order
                tempArray[index] = lines_lengths[beginHalf1];
                beginHalf1++;
                index++;
            }

            // Finish off the second sub-array, if necessary
            while(beginHalf2 <= endHalf2)
            {
                // Invariant: tempArray[beginHalf1..index-1] is in order
                tempArray[index] = lines_lengths[beginHalf2];
                beginHalf2++;
                index++;
            }

            // Copy the result back into the original array
            for(index = first; index <= last;  index++)
            {
                lines_lengths[index] = tempArray[index];
            }
		}

	//////////////////////////////////////////////////////////////////////////////////////////
		
		//iterative merge sort method
		public void I_MergeSort()
		{
		//getting the date and time when the iterative merge sort starts
		Calendar start = Calendar.getInstance();
		//assigning the size for the tempArray below
		tempArray = new int[total_number_of_lines]; 
		//saving the value of total_number_of_lines
		int beginLeftovers = total_number_of_lines;

		
		for (int segmentLength = 1; segmentLength <= total_number_of_lines/2; segmentLength = 2*segmentLength)
		{
			beginLeftovers = I_MergeSegmentPairs(total_number_of_lines, segmentLength);
			int endSegment = beginLeftovers + segmentLength - 1;
			if (endSegment < total_number_of_lines - 1) 
			{
			I_Merge(beginLeftovers, endSegment, total_number_of_lines - 1);
			}
		} 

		// merge the sorted leftovers with the rest of the sorted array
		if (beginLeftovers < total_number_of_lines) {
			I_Merge(0, beginLeftovers-1, total_number_of_lines - 1);
		}
		//getting the date and time when the iterative merge sort ends
		Calendar end = Calendar.getInstance();
		//getting the time it took for the iterative merge sort to execute 
		//subtracting the end time with the start time
	    SortGUI.imergeTime = end.getTime().getTime() - start.getTime().getTime();
	} 

	// Merges segments pairs (certain length) within an array 
	public int I_MergeSegmentPairs(int l, int segmentLength)
	{
		//The length of the two merged segments 

		//You suppose  to complete this part (Given).
		int mergedPairLength = 2 * segmentLength;
		int numberOfPairs = l / mergedPairLength;

		int beginSegment1 = 0;
		for (int count = 1; count <= numberOfPairs; count++)
		{
			int endSegment1 = beginSegment1 + segmentLength - 1;

			int beginSegment2 = endSegment1 + 1;
			int endSegment2 = beginSegment2 + segmentLength - 1;
			I_Merge(beginSegment1, endSegment1, endSegment2);

			beginSegment1 = endSegment2 + 1;

			//redrawing the lines_lengths
			if (guiMode) {
				paintComponent(this.getGraphics());
				//Causing a delay for 10ms
				delay(10);
			}
		}
		// Returns index of last merged pair
		return beginSegment1;
		//return 1;//modify this line
	}

	public void I_Merge(int first, int mid, int last)
	{
		//You suppose  to complete this part (Given).
		// Two adjacent sub-arrays
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// While both sub-arrays are not empty, copy the
		// smaller item into the temporary array
		int index = beginHalf1; // Next available location in tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
		{
			// Invariant: tempArray[beginHalf1..index-1] is in order
			if (lines_lengths[beginHalf1] < lines_lengths[beginHalf2])
			{
				tempArray[index] = lines_lengths[beginHalf1];
				beginHalf1++;
			}
			else
			{
				tempArray[index] = lines_lengths[beginHalf2];
				beginHalf2++;
			}
		}
		//redrawing the lines_lengths
		//paintComponent(this.getGraphics());

		// Finish off the nonempty sub-array

		// Finish off the first sub-array, if necessary
		for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
			// Invariant: tempArray[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf1];

		// Finish off the second sub-array, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
			// Invariant: tempa[beginHalf1..index-1] is in order
			tempArray[index] = lines_lengths[beginHalf2];

		// Copy the result back into the original array
		for (index = first; index <= last; index++)
			lines_lengths[index] = tempArray[index];
	}

    ///////////////////////////////////////////////////////////////////////////////////

    // NOT DONE
    public void QuickSort(){
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        // Call the recursive quicksort with initial parameters
        QuickSort(0, total_number_of_lines - 1);

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();
    }

    public void QuickSort(int first, int last)
    {
        if(first < last) // if last is ahead of first on the array
        {
            // Get the chosen finalPivotIndex and sort the Partition in the array in respect
            // to the pivot using partition function, creating the sub arrays that are more sorted
            int pivotIndex = partition(first, last);

            // Split the array into two rec. calls for the sub arrays

            // Sort smaller sub-array
            QuickSort(first, pivotIndex - 1);

            // Sort larger sub-array
            QuickSort(pivotIndex + 1, last);
        }

    }

    public int partition(int low, int high)
    {
        int mid = (low + high) / 2;

        // EDGE CASE HANDLING, when size of array is <= 4
        // basically just swap the elements to directly sort them and pass the middle most value

        // Handle small size 2 partitions, prevents mid and low sharing a position, the condition in quickSort handles 1
        if (high - low <= 1) {
            if (lines_lengths[low] > lines_lengths[high]) {
                swap(low, high);
            }
            // For size 2, return the index of smaller element to avoid further partitioning on sorted elements
            int finalPivotIndex = low;
            return finalPivotIndex;
        }

        // Handle size 3 & 4 partitions specially,
        // for some reason the low-median-high approach doesnt properly handle size 4 sub-arrays
        if (high - low <= 3) {
            // For small partitions, just sort them directly
            for (int i = low; i < high; i++) { // for length of sub-array
                for (int j = i + 1; j <= high; j++) { // for the incremented length of sub-array
                    if (lines_lengths[i] > lines_lengths[j]) { //if the previous element is greater, swap the greater element
                        swap(i, j);
                    }
                }
            }

            //redrawing the lines_lengths
            if (guiMode) {
                paintComponent(this.getGraphics());
                //Causing a delay for 10ms
                delay(10);
            }

            // Return the middle element as pivot position to avoid further partitioning on sorted elements
            int finalPiotIndex = mid;
            return finalPiotIndex;
        }

        // in-function implementation of sortLowMiddleHigh
        // don't need a separate function that will only be used for one sorting alg.
        // also want to keep the 3-method max implementation
        // of sorting algorithms that has been set as the given standard

        // Swap the low, middle, high elements to match the position
        // of being the lower value, middle value, and higher value

        // make lines_lengths[first] <= lines_lengths[mid]
        if (lines_lengths[low] > lines_lengths[mid]) {
            swap(low, mid);
        }

        // make lines_lengths[mid] <= lines_lengths[last]
        if (lines_lengths[mid] > lines_lengths[high]) {
            swap(mid, high);
        }

        // make lines_lengths[first] <= lines_lengths[mid] again
        if (lines_lengths[low] > lines_lengths[mid]) {
            swap(low, mid);
        }
        // end of sortLowMiddleHigh

        // for a moment, mid is temporarily treated as pivot
        // since pivot needs to be < low and > high
        // swap pivot element (mid temporarily) with element in Next-To-Last position in array
        swap(mid, high - 1);
        // this is also done to move the pivotElement out of the way during the partitioning process

        // init pivot at Next-To-Last position - this is not the true pivot, just a reference point for final swap
        int pivotIndex = high - 1;
        int pivotElement = lines_lengths[pivotIndex];
        // This is the CHOSEN PIVOT ELEMENT, Saved until last swap to save the chosen element
        // The pivotIndex and pivotElement are saved and secured during the partitioning process

        // init pointers for partitioning
        int indexFromLeft = low + 1; // point to position after low position
        int indexFromRight = high - 2; // point to position before pivot position
        // partitioning starts at low+1 and high-2 since low and high are already sorted by median-of-three

        // init loop sentinel (end condition)
        boolean done = false;

        // Check if there's anything to partition
        if (indexFromLeft > indexFromRight) {
            done = true;
        }

        // loop until end condition is met (indexFromLeft and indexFromRight crosses each other)
        while(!done)
        {
            // Progress from the left (beginning) to the right (end)
            // to find the first element in array that is greater than pivot as well as prevent the idexes cross
            while(indexFromLeft <= indexFromRight && lines_lengths[indexFromLeft] < pivotElement)
            {
                indexFromLeft++;
            }

            // Progress from the right (end) to the left (beginning)
            // to find the first element in array that is less than pivot as well as prevent the idexes cross
            while(indexFromRight >= indexFromLeft && lines_lengths[indexFromRight] > pivotElement)
            {
                indexFromRight--;
            }
            // These loops guarantee that indexFromLeft >= pivot and indexFromRight <= pivot

            // once the indexFrom left and indexFromRight found their elements greater or less than the pivot Element
            // if the indexes have not cross each other in the array yet, swap their elements and move them
            if(indexFromLeft <= indexFromRight)
            {
                // Since the indexFromLeft found an element greater than the pivotElement
                // and the indexFromRight found an element less than the pivotElement
                // this means that a[indexFromRight] < pivot < a[indexFromLeft]
                // they can be swaped to sort the array

                // swap the elements between the two indexes
                swap(indexFromLeft, indexFromRight);

                // move indexes further along the array
                indexFromLeft++; // Progress from left to right by 1
                indexFromRight--; // Progress from right to left by 1


                // DEBUG - or for show
//                //redrawing the lines_lengths
//                paintComponent(this.getGraphics());
//                //Causing a delay for 10ms
//                delay(10);
            }
            // once if the indexes on either side cross each in the array
            else{
                // conclude the loop
                done = true;
            }
        }

        // perform the final sorting position of the partition
        // the reason why indexFromLeft is used is
        // due to the pivot element being at the highest position in the array it can be
        // so we need an element greater than the pivotElement to swap with
        // swap pivotElement with greater element in indexFromLeft
        swap(indexFromLeft, pivotIndex);

        //redrawing the lines_lengths
        if (guiMode) {
            paintComponent(this.getGraphics());
            //Causing a delay for 10ms
            delay(10);
        }

        // indexFromLeft is the final pivotElement position now since the pivotElement swapped
        // assign this as the FinalPivotIndex for clarity
        // asserting the fact that anything is left to the pivot is less and to right is greater
        int finalPivotIndex = indexFromLeft;

        // return the finalPivotIndex for recursive quickSort calls
        return finalPivotIndex;
    }

    ///////////////////////////////////////////////////////////////////////////////////

    // NOT DONE
    public void RadixSort()
    {
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        // setting the chosen base digit, AKA the radix
        // set it to base 10 - decimal
        int radix = 10;

        // Call the RadixSort with initial parameters
        RadixSort(radix);

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.radixTime = end.getTime().getTime() - start.getTime().getTime();
    }

    public void RadixSort(int radix)
    {
        // Find the element with max value to determine number of digits or buckets to use
        int maxValue = lines_lengths[0];
        for(int index = 1; index < total_number_of_lines - 1; index++)
        {
            if(lines_lengths[index] > maxValue)
            {
                maxValue = lines_lengths[index];
            }
        }

        // for the length of base digits in the maximum value,
        // (ex: 259 = base digit 3, 13 = base digit 2)
        // start from Least Significant Digit, progressing to Most Significant Digit
        // based on the chosen radix size (ex: base 10, base 16, base 2)
        for(int exponent = 1; maxValue / exponent > 0; exponent *= radix) // the exponent will be based on chosen radix and start at LSD
        {
            // Start the bucket sort with the base digit to sort it by
            BucketSort(exponent, radix);

            //redrawing the lines_lengths
            if (guiMode) {
                paintComponent(this.getGraphics());
                //Causing a delay for 10ms
                delay(10);
            }
        }
    }

    public void BucketSort(int exponent, int radix)
    {
        // Create buckets dynamically based on radix (ex: 2 for binary, 10 for decimal, 16 for hex)
        java.util.ArrayList<Integer>[] buckets = new java.util.ArrayList[radix]; //creating a fixed array of ArrayLists
        // esentially creating a fixed array full of dynamic arrays since buckets will be fixed for the rest of the loop

        // initiate an empty arrayList object for each position, each arrayList object initiated is a bucket to sort the elements into
        // This is to prevent null positions in the buckets array
        for (int bucketIndex = 0; bucketIndex < radix; bucketIndex++) { // for the length of the buckets array
            buckets[bucketIndex] = new java.util.ArrayList<Integer>(); // create ArrayList object in BucketIndex Position
        }

        // Distribute elements into buckets based on current digit
        for (int i = 0; i < total_number_of_lines; i++) { // for the length of the true array
            int digit = (lines_lengths[i] / exponent) % radix;
            // find/shift to the appropriate digit by dividing the element value by the exponent
            // then find the remainder with radix to extract the desired digit value
            // (ex: finding a value in the tens place)
            // (123 / (exponent = 10) = 12 (removes the digit we dont need while not sacrifcing the value we want)
            // (ex cont: 12 % (radix = 10) = 2) which finds the value in the tens place which was the goal

            buckets[digit].add(lines_lengths[i]);
            // based on the found digit, place the whole value (lines_lengths[ at position i])
            // into the bucket of the respective digit found
        }

        // Collect elements from buckets back into true array
        int index = 0;
        for (int i = 0; i < radix; i++) { // for each bucket
            for (int j = 0; j < buckets[i].size(); j++) { // for the length of each bucket
                lines_lengths[index++] = buckets[i].get(j);
                // add the element (position j) from the bucket (position i) into the true array
            }
        }
    }


    //////////////////////////////////////////////////////////////////////
		
		//This method resets the window to the scrambled lines display
		public void reset(){
			if(scramble_lines != null)
			{
				//copying the old scrambled lines into lines_lengths
				for (int i = 0; i < total_number_of_lines; i++)
				{
					lines_lengths[i] = scramble_lines[i] ;
				}
			//Drawing the now scrambled lines_lengths
			if (guiMode) {
				paintComponent(this.getGraphics());
			}
		}
			}
		
	
		//This method colours the lines and prints the lines
		public void paintComponent(Graphics g){
 			super.paintComponent(g);
			//A loop to assign a colour to each line
			for(int i = 0; i < total_number_of_lines; i++){
				//using eight colours for the lines
				if(i % 8 == 0){
					g.setColor(Color.green);
				} else if(i % 8 == 1){
					g.setColor(Color.blue);
				} else if(i % 8 == 2){
					g.setColor(Color.yellow);
				} else if(i%8 == 3){
					g.setColor(Color.red);
				} else if(i%8 == 4){
					g.setColor(Color.black);
				} else if(i%8 == 5){
					g.setColor(Color.orange);
				} else if(i%8 == 6){
					g.setColor(Color.magenta);
				} else
					g.setColor(Color.gray);
				
				//Drawing the lines using the x and y-components 
				g.drawLine(4*i + 25, 300, 4*i + 25, 300 - lines_lengths[i]);
			}
			
		}
		
		//A delay method that pauses the execution for the milliseconds time given as a parameter
		public void delay(int time){
			try{
	        	Thread.sleep(time);
	        }catch(InterruptedException ie){
	        	Thread.currentThread().interrupt();
	        }
		}
		
	}

