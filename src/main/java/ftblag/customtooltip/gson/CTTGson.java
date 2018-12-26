package ftblag.customtooltip.gson;

import java.util.List;

public class CTTGson {

    public static class ToolTips {
        public List<ToolTip> tooltips;

        public ToolTips(List<ToolTip> tooltips) {
            this.tooltips = tooltips;
        }
    }

    public static class ToolTip {
        public String item;
        public List<String> tooltip;

        public ToolTip(String item, List<String> tooltip) {
            this.item = item;
            this.tooltip = tooltip;
        }
    }
}
