package oo;

import java.util.*;

/**
 * Created by diegovidal on 14/03/2018.
 */
public class JavaTimeSeries<E> {

    private final Map<Date, E> date2Data = new HashMap<>();

    public void add(E element) {date2Data.put(new Date(), element); }

    public void addAll(Collection<E> elements) {

        for (E element: elements) {
            this.add(element);
        }
    }

    public void addAllCovatiant(Collection<? extends E> elements) {

        for (E element: elements) {
            this.add(element);
        }
    }

    public E getLatest(){

        Iterator<E> iterator = date2Data.values().iterator();
        if (!iterator.hasNext()){
            throw new NoSuchElementException("TimeSeries is empty, does not have latest element");
        } else {

            E last = iterator.next();
            while (iterator.hasNext()){
                last = iterator.next();
            }

            return last;
        }
    }
}
