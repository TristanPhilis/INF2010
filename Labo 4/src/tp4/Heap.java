package tp4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {
    private ArrayList<ValueType> data;
    private boolean isMin;


    public ArrayList<ValueType> getData() {
        return data;
    }

    // O(1)
    public Heap() {
        this(true);
    }


    // O(1): construction sans donnees initiales TODO
    public Heap(boolean isMin) {
        this(isMin, new ArrayList<>());
    }


    // O(n)
    public Heap(Collection<ValueType> data) {
        this(true, data);
    }


    // O(n): construction avec donnees initiales, allez voir le lien dans la description pour vous aider
    // TODO
    public Heap(boolean isMin, Collection<ValueType> data) {
        this.data = new ArrayList<>();
        this.isMin = isMin;
        if (data.size() != 0) {
            this.data.addAll(data);
            build();
        }
    }


    // O(1): on retourne le nombre d'elements dans la liste
    // TODO
    public int size() {
        return data.size();
    }


    // O(1): on compare deux elements en fonction du type de monceau
    // Returns true if the first element is in a wrong relation with the second one, implies the need for a swap
    // TODO
    private boolean compare(ValueType first, ValueType second) {
        if (isMin) {
            return first.compareTo(second) > 0;
        } else {
            return second.compareTo(first) > 0;
        }
    }


    // O(1): on donne l'indice du parent
    // TODO
    private int parentIdx(int idx) {
        return (idx - 1) / 2;
    }


    // O(1): on donne l'indice de l'enfant de gauche
    // TODO
    private int leftChildIdx(int idx) {
        return 2 * idx + 1;
    }


    // O(1): on donne l'indice de l'enfant de droite
    // TODO
    private int rightChildIdx(int idx) {
        return 2 * idx + 2;
    }


    // O(1): on echange deux elements dans le tableau
    // TODO
    private void swap(int firstIdx, int secondIdx) {
        if (isValidIndex(firstIdx) && isValidIndex(secondIdx)) {
            ValueType temp = data.get(firstIdx);
            data.set(firstIdx, data.get(secondIdx));
            data.set(secondIdx, temp);
        }
    }


    // O(1) : checks if a swap is needed with the left child
    private boolean leftSwapNeeded(int idx) {
        if (leftChildIdx(idx) < data.size()) {
            return compare(data.get(idx), data.get(leftChildIdx(idx)));
        }
        return false;
    }


    // O(1) : checks if a swap is needed with the right child
    private boolean rightSwapNeeded(int idx) {
        if (rightChildIdx(idx) < data.size()) {
            return compare(data.get(idx), data.get(rightChildIdx(idx)));
        }
        return false;
    }


    //O(1) : returns valid index value or not
    private boolean isValidIndex(int idx) {
        return (idx < data.size() && idx >= 0);
    }


    // O(log(n)): l'index courant represente le parent, on s'assure que le parent soit le min/max avec ses enfants
    // De facon visuelle, ceci ammene un noeud le plus haut possible dans l'arbre
    // Par exemple: si le min/max est une feuille, on appelera resursivement log(n) fois la methode pour monter le noeud
    // TODO
    private void heapify(int idx) {
        if (isValidIndex(idx)) {
            if (leftSwapNeeded(idx)) {
                swap(idx, leftChildIdx(idx));
            }
            if (rightSwapNeeded(idx)) {
                swap(idx, rightChildIdx(idx));
            }
            if (idx != 0) {//stops when we reach the root
                heapify(parentIdx(idx));
            }
        }
    }


    // O(log(n)): on ajoute un element et on preserve les proprietes du monceau
    // TODO
    public void insert(ValueType element) {
        if (element != null) {
            data.add(element);
            heapify(parentIdx(data.size() - 1));
        }
    }


    // O(n): on s'assure que tous les elements sont bien places dans le tableau,
    // allez voir le lien dans la description pour vous aider
    // TODO
    public void build() {
        for (int i = data.size() - 1; i >= 0; i--) {
            if (leftSwapNeeded(i)) {
                swap(i, leftChildIdx(i));
                siftDown(leftChildIdx(i));
            }
            if (rightSwapNeeded(i)) {
                swap(i, rightChildIdx(i));
                siftDown(rightChildIdx(i));
            }
        }
    }


    // O(log(n)): on retire le min ou le max et on preserve les proprietes du monceau
    // TODO
    public ValueType pop() {
        ValueType removed = null;
        if (data.size() > 0) {
            removed = data.remove(0);
            heapify(0);
        }
        return removed;
    }


    // O(1): on retourne sans retirer le plus petit ou plus grand element.
    // TODO
    public ValueType peek() {
        if (data.size() > 0) {
            return data.get(0);
        }
        return null;
    }


    private void siftDown(int idx) {
        int swappedIndex = -1;
        if (isValidIndex(rightChildIdx(idx))) {//both children exist since the right one exists
            if (data.get(leftChildIdx(idx)).compareTo(data.get(rightChildIdx(idx))) < 0) {
                if (isMin) {
                    if (leftSwapNeeded(idx)) {
                        swappedIndex = leftChildIdx(idx);
                    }
                } else {
                    if (rightSwapNeeded(idx)){
                        swappedIndex = rightChildIdx(idx);
                    }
                }
            } else {
                if (isMin) {
                    if (rightSwapNeeded(idx)) {
                        swappedIndex = rightChildIdx(idx);
                    }
                } else {
                    if (leftSwapNeeded(idx)) {
                        swappedIndex = leftChildIdx(idx);
                    }
                }
            }
            if(swappedIndex != -1) {
                swap(idx, swappedIndex);
                siftDown(swappedIndex);
            }

        } else if (isValidIndex(leftChildIdx(idx))) {//only the left exists
            if (leftSwapNeeded(idx)) {
                swap(idx, leftChildIdx(idx));
            }
        }

    }


    // O(nlog(n)): On applique l'algorithme Heap Sort, on s'attend a ce que le monceau soit vide a la fin.
    // TODO
    public List<ValueType> sort() {
        List<ValueType> sortedList = new ArrayList<>();

        if (data.size() > 0) {
            while(data.size() > 1) {
                swap(0, data.size() - 1);
                sortedList.add(data.remove(data.size() - 1));
                siftDown(0);
            }
            sortedList.add(data.remove(0));

            if (!isMin) {
                ValueType temp;
                for (int i = 0; i < sortedList.size()/2; i++) {
                    temp = sortedList.get(i);
                    sortedList.set(i, sortedList.get(sortedList.size() - 1 - i));
                    sortedList.set(sortedList.size() - 1 - i, temp);
                }
            }
        }

        return sortedList;
    }


    // Creation d'un iterateur seulement utilise dans les tests, permet de faire des boucles "for-each"
    // TODO
    @Override
    public Iterator<ValueType> iterator() {
        return data.iterator();
    }
}
