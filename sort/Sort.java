package com.dataStructures.sort;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		Sort sort = new Sort();
		int[] nums = new int[]{6587,6,354,6,-2546,7,-6,46,6,876,7,87,84,-54,35,854,-8,7,657,8,8,7,68,46,87,76,64,67,84,687,674,687687,467,678};
		sort.shellSort(nums);
		System.out.println(Arrays.toString(nums));
	}
	
	public void insertSort(int[] nums){
		for(int i=1; i<nums.length; i++){
			int j=i;
			while(j>0){
				if(nums[j]<nums[j-1])
					swap(nums, j, j-1);
				else
					break;
				j--;
			}
		}
	}
	
	public void selectSort(int[] nums){
		for(int i=0; i<nums.length; i++){
			int min = i;
			for(int j=i+1; j<nums.length; j++)
				if(nums[j]<nums[min])
					min = j;
			swap(nums, i, min);
		}
	}
	
	public void bubbleSort(int[] nums, int min, int max){
		for(int i=min; i<=max; i++)
			for(int j=max; j>i; j--)
				if(nums[j] < nums[j-1])
					swap(nums, j, j-1);
	}
	
	public void bucketSort(int[] nums, int bound){
		int[] positive = new int[bound];
		int[] negetive = new int[bound];
		for(int num : nums){
			if(num>=0)
				positive[num]++;
			else
				negetive[-num]++;
		}
		int position= 0;
		for(int i=bound-1; i>=0; i--)
			for(;negetive[i]!=0;negetive[i]--)
				nums[position++]=-i;
		for(int i=0; i<bound; i++)
			for(;positive[i]!=0;positive[i]--)
				nums[position++]=i;
	}
	
	public void shellSort(int[] nums){
		int[] increament = new int[]{7,5,3,1};
		for(int i=0; i<increament.length; i++)
			for(int j=0; j<increament[i]; j++)
				for(int k=1, g=k; j+k*increament[i]<nums.length; k++,g=k)
					for(int position=j+g*increament[i]; g>0 ;g--, position-=increament[i])
						if(nums[position] < nums[position-increament[i]])
							swap(nums, position, position-increament[i]);
	}
	
	
	public void quickSort(int[] nums , int min, int max){
		if(max-min<2){
			//bubbleSort(nums,min,max);
			return;
		}
		int middle = (max-min)/2+min;
		if(nums[min]>nums[max])
			swap(nums, min, max);
		if(nums[max]>nums[middle])
			swap(nums, max, middle);
		if(nums[min]>nums[max])
			swap(nums, min, max);
		int key = nums[max], i=min, j=max-1;
		while(i<=j){
			for(;nums[i]<key;i++);
			for(;nums[j]>key;j--);
			if(i<=j){
				swap(nums, i, j);
				i++;
				j--;
			}
		}
		swap(nums, i, max);
		quickSort(nums, min, i-1);
		quickSort(nums, i+1, max);
	}
	
    private void swap(int[] nums, int a, int b){
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
	

}
