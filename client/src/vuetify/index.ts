import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";
import * as styles from "vuetify/_styles.scss";
import * as icons from "vuetify/lib/iconsets/mdi-svg"
const vuetify = createVuetify( {
    theme: {
        defaultTheme: "dark",
    },

    components,
    directives,
    styles,
    icons
});

export { vuetify }