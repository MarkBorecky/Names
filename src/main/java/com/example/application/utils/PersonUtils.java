package com.example.application.utils;

import com.example.application.data.entity.Name;
import com.example.application.data.entity.Person;
import com.vaadin.flow.function.ValueProvider;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonUtils {
    public static long getNumberDistinctValues(List<Person> all, ValueProvider<Person, String> getter) {
        return all.stream()
                .map(getter)
                .filter(StringUtils::isNotEmpty)
                .distinct()
                .count();
    }

    public static List<Name> getDistinctOriginalNames(List<Person> all) {
        return all.stream()
                .map(Person::getName)
                .filter(n -> StringUtils.isNotEmpty(n.getMainName()))
                //.distinct(Name::getMainName)
                .filter(distinctByKey(n -> n.getOriginalName()))
                .collect(Collectors.toList());
    }

    public static List<Name> getDistinctMainNames(List<Person> all) {
        return all.stream()
                .map(Person::getName)
                .filter(n -> StringUtils.isNotEmpty(n.getMainName()))
                //.distinct(Name::getMainName)
                .filter(distinctByKey(n -> n.getMainName()))
                .collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static long getNumberDistinctValues2(List<Person> all, ValueProvider<Person, Integer> getter) {
        return all.stream()
                .map(getter)
                .filter(StringUtils::isNotEmpty)
                .distinct()
                .count();
    }
}
