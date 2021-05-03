package com.wether.util;

public class Util {

	int[] merge(int[] arr1, int[] arr2) {
		
		int n1 = arr1.length;
		int n2 = arr2.length;
		
		int arr3[] = new int[n1+n2];
		int i = 0, j = 0, k = 0;
	     
        while (i<n1 && j <n2)
        {
            if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
     
        while (i < n1)
            arr3[k++] = arr1[i++];
     
        while (j < n2)
            arr3[k++] = arr2[j++];
        
		return arr3;
	}

	public static void main(String[] args) {
		int[] one = new int[] { 1, 3, 5, 7, 8 };
		int[] two = new int[] { 2, 4, 6, 9, 10 };

		Util util = new Util();

		int[] res = util.merge(one, two);

		if (res != null) {
			for (int i = 0; i < res.length; i++) {
				System.out.println(res[i]);
			}
		}else{
			System.out.println("Result is null");
		}
	}
}
