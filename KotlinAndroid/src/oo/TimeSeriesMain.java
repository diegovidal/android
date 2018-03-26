package oo;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by diegovidal on 14/03/2018.
 */
public class TimeSeriesMain {

    public static void main(String[] args){

        // JavaTimrSeries used in Java
        JavaTimeSeries<Person> peopleData = new JavaTimeSeries<>();
        peopleData.add(new Person());
        peopleData.add(new Student());

        Collection<Student> students = Arrays.asList(new Student(), new Student());
//        peopleData.addAll(students); // no covariance (when used from Java!)
        peopleData.addAllCovatiant(students); // covariance via <? extends E>

        // TimeSeries from Kotlin in Java
        TimeSeries<Person> peopleDataKt = new TimeSeries<>();
        peopleDataKt.add(new Person());
        peopleDataKt.add(new Student());

        peopleDataKt.addAll(students); // covariance
    }
}
