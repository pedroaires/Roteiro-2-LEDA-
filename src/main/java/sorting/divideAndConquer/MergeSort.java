package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(testaLimites(leftIndex, rightIndex, array.length)) {
			int middle = (leftIndex + rightIndex) / 2;
			//esquerda
			sort(array, leftIndex, middle);
			//direita
			sort(array, middle + 1, rightIndex);

			merge(array, leftIndex, middle, rightIndex);
		}
	}
	public void merge(T[] arr, int leftIndex, int middle, int rightIndex){
		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

		//usa apenas a memória necessária
		List<T> aux = new ArrayList<T>(rightIndex - leftIndex + 1);

		for (int c = leftIndex ; c <= rightIndex ; c++ ){
			//c - leftindex é o index relativo
			aux.add(c - leftIndex, arr[c]);
		}

		while((i <= middle) && (j <= rightIndex)){
			// (i - leftIndex) -> Ajustando a posição relativa do
			//arrayList que sempre começa em 0
			if(aux.get(i - leftIndex).compareTo(aux.get(j - leftIndex)) > 0){
				arr[k] = aux.get(j - leftIndex);
				j++;
				k++;
			}
			else{
				arr[k] = aux.get(i - leftIndex);
				i++;
				k++;
			}
		}
		while(i <= middle){
			arr[k] = aux.get(i - leftIndex);
			k++;
			i++;
		}
		while(j <= rightIndex){
			arr[k] = aux.get(j - leftIndex);
			k++;
			j++;
		}

	}
	public static boolean testaLimites(int esquerda, int direita, int tamanho){
		if(esquerda < 0 || direita >= tamanho || tamanho == 0 || esquerda >= direita || direita < 1){
			return false;
		}
		else{
			return true;
		}
	}
}
