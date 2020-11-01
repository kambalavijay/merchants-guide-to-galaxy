

class ElementValue {
    private String romanCorr ;
    private boolean isMetal;
    private float value;

    public String getRomanCorr() {
        return romanCorr;
    }

    public void setRomanCorr(String romanCorr) {
        this.romanCorr = romanCorr;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setIsMetal(boolean isMetal){
        this.isMetal = isMetal;
    }

    public boolean getIsMetal() {
        return isMetal;
    }

    @Override
    public String toString() {
        return "ElementValue{" +
                "romanCorr='" + romanCorr + '\'' +
                ", isMetal=" + isMetal +
                ", value=" + value +
                '}';
    }
}
