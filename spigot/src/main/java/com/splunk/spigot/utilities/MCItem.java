package com.splunk.spigot.utilities;

public class MCItem {

    private int meta;
    private String name;
    private String text_type;
    private int type;

    public MCItem(int meta, String name, String text_type, int type) {
        this.meta = meta;
        this.name = name;
        this.text_type = text_type;
        this.type = type;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText_type() {
        return text_type;
    }

    public void setText_type(String text_type) {
        this.text_type = text_type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MCItem mcItem = (MCItem) o;

        if (meta != mcItem.meta) return false;
        if (type != mcItem.type) return false;
        return text_type.equals(mcItem.text_type);

    }

    @Override
    public int hashCode() {
        int result = meta;
        result = 31 * result + text_type.hashCode();
        result = 31 * result + type;
        return result;
    }
}
