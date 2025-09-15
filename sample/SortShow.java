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
		
		//the default constructor for the SortShow class
		public SortShow(){
			//assigning the size for the lines_lengths below
			lines_lengths = new int[total_number_of_lines];
			for(int i = 0; i < total_number_of_lines; i++) 
				lines_lengths[i] =  i+5;
			
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
			paintComponent(this.getGraphics());
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
                        //paintComponent(this.getGraphics()); // DEBUG- DISABLE TO SEE FINISHED RESULTS FASTER
                    }
                    //redrawing the line_lengths
                    //paintComponent(this.getGraphics()); // DEBUG- DISABLE TO SEE FINISHED RESULTS FASTER
                }
                //redrawing the line_lengths
                paintComponent(this.getGraphics()); // DEBUG- DISABLE TO SEE FINISHED RESULTS FASTER
            }
            //redrawing the line_lengths
            paintComponent(this.getGraphics());

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
                paintComponent(this.getGraphics());
                //Make delay for 10ms
                delay(10);
            }


			//getting the date and time when the selection sort ends
			Calendar end = Calendar.getInstance();
			//getting the time it took for the selection sort to execute
			//subtracting the end time with the start time
	        SortGUI.selectionTime = end.getTime().getTime() - start.getTime().getTime();
		}

    ///////////////////////////////////////////////////////////////////////////////////

    // NOT DONE
    public void InsertionSort(){
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.insertionTime = end.getTime().getTime() - start.getTime().getTime();
    }

    ///////////////////////////////////////////////////////////////////////////////////

    // NOT DONE
    public void ShellSort(){
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        int first = 0;
        int last = total_number_of_lines;
        int n = last - first + 1;
        int space = n / 2;

        for(; space > 0; space = space / 2){
         for(int begin = first; begin <= first + space; begin++){

         }
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
                paintComponent(this.getGraphics());
                //Causing a delay for 10ms
				delay(10);
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
			paintComponent(this.getGraphics());
			//Causing a delay for 10ms
			delay(10);
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

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.quickTime = end.getTime().getTime() - start.getTime().getTime();
    }


    ///////////////////////////////////////////////////////////////////////////////////

    // NOT DONE
    public void RadixSort(){
        //getting the date and time when the selection sort starts
        Calendar start = Calendar.getInstance();

        //getting the date and time when the selection sort ends
        Calendar end = Calendar.getInstance();
        //getting the time it took for the selection sort to execute
        //subtracting the end time with the start time
        SortGUI.radixTime = end.getTime().getTime() - start.getTime().getTime();
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
			paintComponent(this.getGraphics());
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

