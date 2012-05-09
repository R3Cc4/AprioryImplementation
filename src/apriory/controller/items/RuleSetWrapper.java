package apriory.controller.items;

/**
 * Created with IntelliJ IDEA.
 * User: Michal
 * Date: 9.5.12
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
public class RuleSetWrapper {

    private RuleSet leftSet;
    private RuleSet rightSet;
    private double support;
    private double confidence;

    public RuleSetWrapper(double support, double confidence) {
        this.support = support;
        this.confidence = confidence;
        this.leftSet = new RuleSet();
        this.rightSet = new RuleSet();
    }

    public RuleSet getLeftSet() {
        return leftSet;
    }

    public RuleSet getRightSet() {
        return rightSet;
    }

    public double getSupport() {
        return support;
    }

    public void setSupport(double support) {
        this.support = support;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
