package co.edu.uptc.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.lang.reflect.Array;

public class SimpleList<T> implements List<T> {

    private Node<T> head;

    public SimpleList() {
        head = null;
    }

    @Override
    public int size() {
        int total = 0;
        Node<T> auxNode = head;

        while (auxNode != null) {
            total++;
            auxNode = auxNode.getNext();
        }

        return total;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            Node<T> aux = head;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T num = (T) aux.getData();
                aux = aux.getNext();
                return num;
            }

        };
        return iterator;

    }

    @Override
    public Object[] toArray() {
        if (head == null) {
            throw new IllegalArgumentException("La lista está vacía.");
        }

        ArrayList<T> list = new ArrayList<T>();

        Node<T> aux = head;
        while (aux != null) {
            list.add(aux.getData());
            aux = aux.getNext();
        }

        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int size = 0;

        Node<T> aux = (Node<T>) head;
        while (aux != null) {
            size++;
            aux = aux.getNext();
        }

        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }

        aux = (Node<T>) head;
        int i = 0;
        while (aux != null) {
            a[i++] = aux.getData();
            aux = aux.getNext();
        }

        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        boolean exist = false;
        if (head == null) {
            head = new Node<T>(e);
            exist = true;
        } else {
            Node<T> aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(new Node<T>(e));
            exist = true;
        }
        return exist;
    }

    @Override
    public boolean remove(Object o) {
        boolean value = false;
        if (head.getData() == o) {
            head = head.getNext();
            value = true;
        }
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getNext().getData() == o) {
                aux.setNext(aux.getNext().getNext());
                value = true;
            }
            aux = aux.getNext();
        }
        return value;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (head == null) {
            throw new IllegalArgumentException("La lista está vacía");
        }

        int count = 0;
        Node<T> aux = head;

        while (aux != null) {
            aux = aux.getNext();
            count++;
        }

        if (c.size() > count) {
            throw new IndexOutOfBoundsException("El index es mayor a la lista.");
        }

        aux = head;
        count = 0;

        for (Object element : c) {
            while (aux != null) {
                if (element.equals(aux.getData())) {
                    count++;
                }
                aux = aux.getNext();
            }
            aux = head;
        }

        return count == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (T element : c) {
            add(element);
            modified = true;
        }

        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }
        if (isEmpty()) {
            return false;
        }
        if (index == 0) {
            Node<T> newHead = null;
            Node<T> lastNewNode = null;
    
            for (T element : c) {
                Node<T> newNode = new Node<>(element);
                if (newHead == null) {
                    newHead = newNode;
                } else {
                    lastNewNode.setNext(newNode);
                }
                lastNewNode = newNode;
            }
    
            if (lastNewNode != null) {
                lastNewNode.setNext(head);
            }
            head = newHead;
            return true;
        }
        int count = 0;
        Node<T> aux = head;

        while (aux != null && count < index - 1) {
            aux = aux.getNext();
            count++;
        }

        if (aux == null) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Node<T> nexNode = aux.getNext();
        Node<T> lastNode = null;

        for (T object : c) {
            Node<T> newNode = new Node<>(object);
            if (lastNode == null) {
                aux.setNext(newNode);
            } else {
                lastNode.setNext(newNode);
            }
            lastNode = newNode;
        }
        if (lastNode!= null) {
            lastNode.setNext(nexNode);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }
        boolean removed = false;

        while (head != null && c.contains(head.getData())) {
            head = head.getNext();
            removed = true;
        }
        Node<T> NodeAux = head;
        while (NodeAux != null && NodeAux.getNext() != null) {
            if (c.contains(NodeAux.getNext().getData())) {
                NodeAux.setNext(NodeAux.getNext().getNext());
                removed = true;
            } else {
                NodeAux = NodeAux.getNext();
            }
        }

        return removed;

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }

        Node<T> aux = head;
        int count = 0;
        T data = null;

        while (aux != null) {
            if (count == index) {
                data = aux.getData();
            }
            aux = aux.getNext();
            count++;
        }

        return data;
    }

    @Override
    public T set(int index, T element) {
        Node<T> auxNode = head;
        for (int i = 0; i < index; i++) {
            auxNode = auxNode.getNext();
        }
        T oldData = auxNode.getData();
        auxNode.setData(element);
        return oldData;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Índice negativo.");
        }

        if (head == null) {
            throw new IndexOutOfBoundsException("La lista está vacía.");
        }

        Node<T> aux = new Node<T>(element);

        if (index == 0) {
            aux.setNext(head);
            head = aux;
            return;
        }

        Node<T> current = head;
        int count = 0;

        while (current != null && count < index - 1) {
            current = current.getNext();
            count++;
        }

        aux.setNext(current.getNext());
        current.setNext(aux);
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }

        if (isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty.");
        }

        int count = 0;
        T data = null;
        Node<T> aux = head;
        while (aux != null && count < index) {
            if (count == index - 1) {
                data = aux.getNext().getData();
                aux.setNext(aux.getNext().getNext());
            }
            count++;
        }

        if (aux == null || aux.getNext() == null) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return data;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> auxNode = head;
        int i = 0;

        while (auxNode != null) {
            if (o != null && o.equals(auxNode.getData())) {
                return i;
            }
            auxNode = auxNode.getNext();
            i++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndexOf = -1;
        int index = 0;

        Node<T> aux = head;
        while (aux != null) {
            if (aux.getData().equals(o)) {
                lastIndexOf = index;
            }
            aux = aux.getNext();
            index++;
        }

        return lastIndexOf;
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }
}
