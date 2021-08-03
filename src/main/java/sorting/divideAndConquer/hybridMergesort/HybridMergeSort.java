package sorting.divideAndConquer.hybridMergesort;


import sorting.AbstractSorting;
import sorting.divideAndConquer.MergeSort;

import java.util.ArrayList;
import java.util.List;

import static util.Util.swap;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;



	public void sort(T[] array, int leftIndex, int rightIndex) {
		this.MERGESORT_APPLICATIONS = 0;
		this.INSERTIONSORT_APPLICATIONS = 0;
		if(array != null){
			if (rightIndex - leftIndex + 1 <= SIZE_LIMIT) {
				insertionSort(array, leftIndex, rightIndex);
				INSERTIONSORT_APPLICATIONS += 1;
			} else {
				mergeSort(array, leftIndex, rightIndex);
			}


		}
	}
	public void insertionSort(T[] array, int leftIndex, int rightIndex){
		if (testaLimites1(array.length, leftIndex, rightIndex)) {
			for (int i = leftIndex + 1 ; i <= rightIndex ; i++){
				int j = i;

				while(j > leftIndex && (array[j].compareTo(array[j-1]) < 0)){
					swap(array, j, j-1);
					j--;
				}
			}
		}
	}
	public void mergeSort(T[] array, int leftIndex, int rightIndex){
		if(testaLimites(leftIndex, rightIndex, array.length)) {
			int middle = (leftIndex + rightIndex) / 2;
			//esquerda
			sort(array, leftIndex, middle);
			//direita
			sort(array, middle + 1, rightIndex);

			merge(array, leftIndex, middle, rightIndex);
		}
	}
	public static boolean testaLimites1(int tamanho, int esquerda, int direita){
		return (esquerda >= 0) && (direita < tamanho) && (esquerda < direita);
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
