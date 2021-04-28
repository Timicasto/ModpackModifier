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

    public static class Item {
        public String id;
        public int meta;
        public String nbt;
        public boolean ignoreMeta;
        public boolean ignoreNbt;

        public Item(String id, int meta, String nbt, boolean ignoreMeta, boolean ignoreNbt) {
            this.id = id;
            this.ignoreMeta = ignoreMeta;
            this.ignoreNbt = ignoreNbt;
            this.meta = meta;
            this.nbt = nbt;
        }
    }
}
