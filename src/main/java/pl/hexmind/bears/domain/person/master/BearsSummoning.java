package pl.hexmind.bears.domain.person.master;

import pl.hexmind.bears.domain.person.bear.Bear;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class BearsSummoning {

    private int limit;

    public BearsSummoning(int limit) {
        this.limit = limit;
    }

    public List<Bear> summon() {
        return LongStream.iterate(0, i -> i + 1)
                .boxed()
                .limit(limit)
                .map(Bear::new)
                .collect(Collectors.toList());
    }
}
