package pl.hexmind.bears

import pl.hexmind.bears.domain.Color
import pl.hexmind.bears.domain.person.master.MasterOfBears
import pl.hexmind.bears.domain.wardrobe.SequentialWardrobe
import pl.hexmind.bears.domain.wardrobe.Wardrobe
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class RiddleSpec extends Specification {

    def "should anybody guess own color from colors #colors"() {
        given:
            MasterOfBears master = new MasterOfBears(7, wardrobe)
        when:
            new Riddle(master).start()
        then:
            noExceptionThrown()
        where:
            colors                | _
            [3, 4, 3, 0, 6, 0, 1] | _
            [6, 0, 4, 5, 1, 6, 3] | _
            [3]                   | _
            [0, 1]                | _
            [0, 6, 6, 6, 6, 6, 6] | _
            [5, 5, 5, 5, 5, 5, 5] | _
            (6..0) as List        | _
            (0..6) as List        | _
            wardrobe = new SequentialWardrobe(colors)
    }

    def "should #bears bears solve the riddle"() {
        given:
            List<Integer> colors = (0..bears) as List
            Wardrobe wardrobe = new SequentialWardrobe(colors)
            MasterOfBears master = new MasterOfBears(bears as int, wardrobe)
        when:
            new Riddle(master).start()
        then:
            noExceptionThrown()
        where:
            bears << [3, 10, maxColorValue()]
    }

    int maxColorValue() {
        return Color.values().last().ordinal()
    }
}
