package com.example.nurislam.rxjavahw;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observables.MathObservable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list  = new ArrayList<>();
        list.add("Kekich");
        task1(list).subscribe(System.out::println);

        task2((Observable<String>) (Observable.just("Dima", "Vasya", "Dima", "Roma", "Peter", "END", "Egor"))
                .subscribe(System.out::println));

        task3(Observable.just(12, 35, 3))
                .subscribe(System.out::println);

        task4(Observable.just(false), Observable.just(32, 45, 15), Observable.just(64, 210, 90))
                .subscribe(System.out::println, throwable -> {
                    System.out.println(throwable.getMessage());
                });

        task5(Observable.just(100, 17, 63), Observable.just(15, 89, 27))
                .subscribe(System.out::println);

        task6().subscribe(System.out::println);

    }

    @NonNull
    public Observable<Integer> task1(@NonNull List<String> list) {
        return Observable.from(list)
                .map(String::toLowerCase)
                .filter(s -> s.contains("r"))
                .flatMap(s -> Observable.just(s.length()));
    }

    @NonNull
    public Observable<String> task2(@NonNull Observable<String> observable) {
        return observable.compose(new MyTransformer<>())
                .takeWhile(value -> !value.equals("END"))
                .distinct();
    }

    @NonNull
    public Observable<Integer> task3(@NonNull Observable<Integer> observable) {
        return MathObservable.sumInteger(observable);
    }

    @NonNull
    public Observable<Integer> task4(@NonNull Observable<Boolean> flagObservable,
                                            @NonNull Observable<Integer> first, @NonNull Observable<Integer> second) {
        return flagObservable
                .flatMap(value -> {
                    if (value) {
                        return first;
                    } else {
                        return second;
                    }
                }).flatMap(value -> {
                    if (value < 99) {
                        return Observable.just(value);
                    } else {
                        throw new IllegalArgumentException("KEK LoL Validol");
                    }
                });
    }

    @NonNull
    public  Observable<Integer> task5(@NonNull Observable<Integer> first,
                                                     @NonNull Observable<Integer> second) {
        return Observable.zip(first, second, (value1, value2) -> {
            return gcd(value1, value2);
        }).compose(new MyTransformer<>());
    }

    private Integer gcd(Integer a, Integer b) {
        while ((a != 0) && (b != 0)) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }
        return a + b;
    }

    @NonNull
    public  Observable<BigInteger> task6() {
        return Observable.range(1, 100000)
                .map(integer -> integer * 2)
                .skip(40000)
                .skipLast(40000)
                .filter(integer -> integer % 3 == 0)
                .cache()
                .reduce(BigInteger.ONE, (bigInteger, integer) -> bigInteger.multiply(BigInteger.valueOf(integer)));
    }
}
