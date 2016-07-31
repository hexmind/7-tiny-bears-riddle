package pl.hexmind.bears.domain.solution;

import pl.hexmind.bears.domain.Color;
import pl.hexmind.bears.domain.person.bear.Bear;
import pl.hexmind.bears.domain.person.bear.BearAnswer;
import pl.hexmind.bears.domain.wardrobe.Hat;

import java.util.List;
import java.util.stream.IntStream;

/*

                                                        iiii  lllllll
                                                       i::::i l:::::l
                                                        iiii  l:::::l
                                                              l:::::l
    ssssssssss   ppppp   ppppppppp      ooooooooooo   iiiiiii  l::::l     eeeeeeeeeeee    rrrrr   rrrrrrrrr
  ss::::::::::s  p::::ppp:::::::::p   oo:::::::::::oo i:::::i  l::::l   ee::::::::::::ee  r::::rrr:::::::::r
ss:::::::::::::s p:::::::::::::::::p o:::::::::::::::o i::::i  l::::l  e::::::eeeee:::::eer:::::::::::::::::r
s::::::ssss:::::spp::::::ppppp::::::po:::::ooooo:::::o i::::i  l::::l e::::::e     e:::::err::::::rrrrr::::::r
 s:::::s  ssssss  p:::::p     p:::::po::::o     o::::o i::::i  l::::l e:::::::eeeee::::::e r:::::r     r:::::r
   s::::::s       p:::::p     p:::::po::::o     o::::o i::::i  l::::l e:::::::::::::::::e  r:::::r     rrrrrrr
      s::::::s    p:::::p     p:::::po::::o     o::::o i::::i  l::::l e::::::eeeeeeeeeee   r:::::r
ssssss   s:::::s  p:::::p    p::::::po::::o     o::::o i::::i  l::::l e:::::::e            r:::::r
s:::::ssss::::::s p:::::ppppp:::::::po:::::ooooo:::::oi::::::il::::::le::::::::e           r:::::r
s::::::::::::::s  p::::::::::::::::p o:::::::::::::::oi::::::il::::::l e::::::::eeeeeeee   r:::::r
 s:::::::::::ss   p::::::::::::::pp   oo:::::::::::oo i::::::il::::::l  ee:::::::::::::e   r:::::r
  sssssssssss     p::::::pppppppp       ooooooooooo   iiiiiiiillllllll    eeeeeeeeeeeeee   rrrrrrr
                  p:::::p
                  p:::::p
                 p:::::::p
                 p:::::::p
                 p:::::::p
                 ppppppppp
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*/
public class ExistingSolver implements RiddleSolver {

    private final Bear bear;

    public ExistingSolver(Bear bear) {
        this.bear = bear;
    }

    @Override
    public BearAnswer solve(List<Hat> visibleHats) {
        int maxColors = 1 + visibleHats.size();
        int colorsSum = sumColors(visibleHats);
        Color guessed = IntStream.range(0, maxColors)
                .boxed()
                .filter(color -> (color + colorsSum) % maxColors == bear.getId())
                .map(Color::valueOf)
                .findFirst()
                .get();
        return bear.answer(guessed);
    }

    private int sumColors(List<Hat> visibleHats) {
        return visibleHats.stream()
                .map(Hat::getColor)
                .mapToInt(Color::ordinal)
                .sum();
    }

}
