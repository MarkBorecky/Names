package com.example.application.data.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NamesConverter{
    public static final Map<String, NameAbstraction> names = createMap();

    public static NameAbstraction getByAlternative(String name) {
        return Optional.ofNullable(names.get(name)).orElse(new NameWithoutAlternative(name));
    }

    private static Map<String, NameAbstraction> createMap() {
        return Arrays.stream(NameAlternatives.values())
                .flatMap(name -> makeMap(name).entrySet().stream())
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    private static HashMap<String, NameAlternatives> makeMap(NameAlternatives name) {
        return IntStream.range(0, name.alternatives.size())
                .collect(HashMap::new,
                        (m, i) -> m.put(name.alternatives.get(i), name),
                        Map::putAll);
    }
}
