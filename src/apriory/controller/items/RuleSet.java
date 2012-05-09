package apriory.controller.items;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 9.5.12
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class RuleSet extends ItemSetAncestor {

    private double confidence;

    public RuleSet() {
        super();

    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

}
