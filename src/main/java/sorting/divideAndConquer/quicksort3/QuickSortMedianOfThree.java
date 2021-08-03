package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array.length > 0 && leftIndex >= 0 && (rightIndex - leftIndex) >= 1
				&& rightIndex < array.length){

			//ordena, escolhe o pivo e joga o centeer para a penultima posição
			int medianaID = medianaDeTres(array, leftIndex, rightIndex);
			swap(array, medianaID, rightIndex-1);

			int indexPivot = particiona(array, leftIndex, rightIndex);
			sort(array, leftIndex, indexPivot-1);
			sort(array, indexPivot + 1, rightIndex);

		}
	}

	private int particiona(T[] array, int leftIndex, int rightIndex){

		T pivot = array[leftIndex];
		int i = leftIndex;

		for (int j = leftIndex + 1 ; j <= rightIndex ; j++){
			if(pivot.compareTo(array[j]) >= 0){
				i++;
				swap(array, i, j);
			}
		}
		swap(array, leftIndex, i);
		return i;
	}

	private int medianaDeTres(T[] arr, int esquerda, int direita){
		int medio = (esquerda + direita)/2;

		if (arr[esquerda].compareTo(arr[medio]) > 0) {
			swap(arr, esquerda, medio);
		}
		if (arr[medio].compareTo(arr[direita]) > 0) {
			swap(arr, medio, direita);
		}
		if (arr[esquerda].compareTo(arr[medio]) > 0) {
			swap(arr, esquerda, medio);
		}
		return medio;
	}
}
