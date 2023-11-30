package AutomataLib;

public enum Reserved
{
    NULL();
    public final String label;

    Reserved() {
        this.label = "";
    }

    @Override
    public String toString() {
        return label;
    }
}
