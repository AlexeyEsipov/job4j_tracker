package ru.job4j.stream;

public class CarBuilder {
    private String mark;
    private String model;
    private int net;
    private String typeEngine;
    private String bodyStyle;
    private boolean created;
    private String color;

    public static void main(String[] args) {
        CarBuilder user = new Builder().buildMark("Ford")
                .buildModel("Escort")
                .buildNet(1800)
                .buildTypeEngine("petrol")
                .buildBodyStyle("sedan")
                .buildCreated(true)
                .buildColor("red")
                .build();
        System.out.println(user);
    }

    @Override
    public String toString() {
        return "CarBuilder{"
                + "mark='" + mark + '\''
                + ", model='" + model + '\''
                + ", net=" + net
                + ", typeEngine='" + typeEngine + '\''
                + ", BodyStyle='" + bodyStyle + '\''
                + ", created=" + created
                + ", color='" + color + '\''
                + '}';
    }

    static class Builder {
        private String mark;
        private String model;
        private int net;
        private String typeEngine;
        private String bodyStyle;
        private boolean created;
        private String color;

        Builder buildMark(String mark) {
            this.mark = mark;
            return this;
        }

        Builder buildModel(String model) {
            this.model = model;
            return this;
        }

        Builder buildNet(int net) {
            this.net = net;
            return this;
        }

        Builder buildTypeEngine(String typeEngine) {
            this.typeEngine = typeEngine;
            return this;
        }

        Builder buildBodyStyle(String bodyStyle) {
            this.bodyStyle = bodyStyle;
            return this;
        }

        Builder buildCreated(boolean created) {
            this.created = created;
            return this;
        }

        Builder buildColor(String color) {
            this.color = color;
            return this;
        }

        CarBuilder build() {
            CarBuilder user = new CarBuilder();
            user.mark = mark;
            user.model = model;
            user.net = net;
            user.typeEngine = typeEngine;
            user.bodyStyle = bodyStyle;
            user.created = created;
            user.color = color;
            return user;
        }
    }
}
