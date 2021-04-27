package timicasto.modpackmodifier.tweak;

public class CMHelper {
    public static class Attributes {
        public String name;
        public boolean disabled;
        public int duration;

        public Attributes(String name, boolean disabled, int duration) {
            this.disabled = disabled;
            this.duration = duration;
            this.name = name;
        }
    }
}
