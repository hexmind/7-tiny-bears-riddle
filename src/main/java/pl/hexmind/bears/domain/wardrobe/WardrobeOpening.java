package pl.hexmind.bears.domain.wardrobe;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

public class WardrobeOpening {

    private final Wardrobe wardrobe;

    public WardrobeOpening(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }

    public List<Hat> getHats(Integer limit) {
        List<Hat> hats = wardrobe.streamHats()
                .limit(limit)
                .collect(Collectors.toList());
        checkState(onlyKnownColors(hats, limit), "Wardrobe should use only %s first colors for hats", limit);
        return hats;
    }

    private boolean onlyKnownColors(List<Hat> hats, Integer limit) {
        return hats.stream()
                .map(hat -> hat.getColor().ordinal())
                .allMatch(color -> color < limit);
    }
}
