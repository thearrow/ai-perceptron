import java.util.ArrayList;

public class Example {

    ArrayList<Double> attributes;
    private String target;

    public Example(String str) {
        attributes = new ArrayList<Double>();
        String[] attrs = str.split(",");
        for (int i = 0; i < attrs.length - 1; i++) {
            this.addAttribute(attrs[i]);
        }
        target = attrs[attrs.length - 1];
    }

    public void addAttribute(String value) {
        Double atr = new Double(value);
        attributes.add(atr);
    }

    public Double getTarget() {
        return new Double(target);
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Double getAttribute(int a) {
        return attributes.get(a);
    }

    public ArrayList<Double> getAttributes() {
        return attributes;
    }

    public int numAttributes() {
        return attributes.size();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("(");
        for (Double a : attributes) {
            result.append(a);
            result.append(", ");
        }
        result.append("Target: ");
        result.append(target);
        result.append(")");

        return result.toString();
    }

}