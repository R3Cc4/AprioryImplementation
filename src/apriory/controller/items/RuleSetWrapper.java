package apriory.controller.items;

import java.util.Formatter;

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
    private double lift;
    private double conviction;

    public RuleSetWrapper(double support, double confidence) {
        this.support = support;
        this.confidence = confidence;
        this.leftSet = new RuleSet();
        this.rightSet = new RuleSet();
        lift = 0;
        conviction = 0;
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

    public double getLift() {
        this.lift = confidence / rightSet.getSupport();
        return lift;
    }

    public double getConviction() {

        return ((1 - rightSet.getSupport())/(1-confidence));

    }

    @Override
    public String toString() {

        String s = "";
        int counter = 0;

        for (String s1 : leftSet.getItems()) {
            if (counter == 0) s = "" + s1;
            else s = s + " & " + s1;
            counter++;
        }

        counter = 0;

        for (String s1 : rightSet.getItems()) {
            if (counter == 0) s = s + " --> " + s1;
            else s = s + " & " + s1;
            counter++;
        }

        s = s + "  (confidence: " + String.format("%4.3f", getConfidence()) + ")";
        s = s + "  (lift: " + String.format("%4.3f", getLift()) + ")";
        s = s + "  (conviction: " + String.format("%4.3f",getConviction()) + ")";
        return s;
    }
}
